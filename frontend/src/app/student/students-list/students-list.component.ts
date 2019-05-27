import { Component, OnInit, ViewChild } from '@angular/core';
import { Student } from '../student.model';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material';
import { StudentService } from '../student.service';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-students-list',
  templateUrl: './students-list.component.html',
  styleUrls: ['./students-list.component.css']
})
export class StudentsListComponent implements OnInit {

  students : Student[] = [];
  subjectId: number;
  displayedColumns: string[] = ['no', 'firstName', 'lastName', 'personalNumber', 'email'];
  dataSource = new MatTableDataSource<Student>(this.students);
  loggedUser : string = this.authService.getCurrentUser();
  @ViewChild(MatPaginator) paginator: MatPaginator;


  constructor(private studentService: StudentService, private authService: AuthService) { }

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
  }

  getStudents(){
    this.studentService.getStudentsBySubjectId(this.subjectId, this.loggedUser).subscribe( (data: Student[]) => {
      this.students = data;
      this.dataSource.data = data;
    });

  }

}
