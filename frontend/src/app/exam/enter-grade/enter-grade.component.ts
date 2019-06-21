import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../../auth/auth.service';
import { ExamService } from '../exam.service';
import { StudentExamRegistrationDTO } from './student-exam-registration-dto.model';
import { MatTableDataSource, MatTable } from '@angular/material/table';
import { MatSnackBar } from '@angular/material/snack-bar';
declare var jsPDF: any;

@Component({
  selector: 'app-enter-grade',
  templateUrl: './enter-grade.component.html',
  styleUrls: ['./enter-grade.component.css']
})
export class EnterGradeComponent implements OnInit {

  @ViewChild(MatTable) table: MatTable<StudentExamRegistrationDTO>;
  registeredStudents: StudentExamRegistrationDTO[] = [];
  student: StudentExamRegistrationDTO;
  displayColumns: string[] = ['no', 'firstName', 'lastName', 'indexNum', 'test1', 'test2', 'attendance', 'finalExam', 'total', 'grade', 'save']
  dataSource = new MatTableDataSource<StudentExamRegistrationDTO>();
  constructor(private route: ActivatedRoute, private authService: AuthService, private examService: ExamService, private snackbar: MatSnackBar) { }

  ngOnInit() {
    this.getRegistrations();

  }

  getRegistrations() {
    this.examService.getStudentExamRegistrationsBySubject(this.route.snapshot.paramMap.get('id'), this.authService.getCurrentUser())
      .subscribe(
        (data: StudentExamRegistrationDTO[]) => {
          this.dataSource.data = data;
        }
      );
  }

  onSave(i: number) {
    this.registeredStudents.push(this.dataSource.data[i]);
    this.dataSource.data.splice(i, 1);
    this.table.renderRows();

  }

  submitGrades() {
    this.examService.submitGrades(this.registeredStudents, this.authService.getCurrentUser(), this.route.snapshot.paramMap.get('id')).subscribe(
      (res: Response) => {
        this.snackbar.open('Successfully submited grades', 'Ok', {
          duration: 3000,
          panelClass: ["success"]
        });
      }, (err: any) => {
        this.snackbar.open('Error submitting grades', 'Ok', {
          duration: 3000,
          panelClass: ["error"]
        });
      }
    );
  }

  toPDF() {
    let doc = new jsPDF('l', 'pt'); 
    //jpt;
    let cols = [["First name", "Last Name", "Index", "Test 1", "Test 2", "Attendance", "Final exam", "Grade"]];
    let rows = [];
    this.registeredStudents.forEach(element => {
      var temp = [element.firstName, element.lastName, element.indexNum, element.test1, element.test2, element.attendance, element.finalExam, element.grade];
      rows.push(temp);
    });
    doc.autoTable({head: cols, body: rows});
    doc.save('results.pdf');
  }
}
