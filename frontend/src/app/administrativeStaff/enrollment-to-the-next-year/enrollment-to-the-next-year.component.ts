import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatTableDataSource, MatDialog } from '@angular/material';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { YearOfStudy } from 'src/app/year-of-study/year-of-study.model';
import { YearOfStudyService } from 'src/app/year-of-study/year-of-study.service';
import { AdministrativeStaffService } from '../administrative-staff.service';
import { Student } from 'src/app/student/student.model';
import { ConfirmationDialogComponent } from 'src/app/shared/confirmation-dialog/confirmation-dialog.component';
import { SnackBarService } from 'src/app/shared/snack-bar.service';

@Component({
  selector: 'app-enrollment-to-the-next-year',
  templateUrl: './enrollment-to-the-next-year.component.html',
  styleUrls: ['./enrollment-to-the-next-year.component.css']
})
export class EnrollmentToTheNextYearComponent implements OnInit {

  public enrollmentToTheNextYearForm : FormGroup;
  public yearsOfStudies: YearOfStudy[] = [];
  students : Student[] = [];
  displayedColumns: string[] = ['no', 'firstName', 'lastName', 'personalNumber', 'profilePicturePath', 'country', 'city', 'street', 'streetNumber', 'email', 'username', 'actions'];
  dataSource = new MatTableDataSource<Student>(this.students);

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private fb: FormBuilder, private yearOfStudyService: YearOfStudyService, private administrativeStaffService: AdministrativeStaffService, public dialog: MatDialog, private snackBarService: SnackBarService) { }

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.enrollmentToTheNextYearForm = this.fb.group({
      yearOfStudy: ['', {validators: [Validators.required]}]
    });

    this.getYearsOfStudies();
  }

  getYearsOfStudies(){
    this.yearOfStudyService.getAll().subscribe(data => {
      this.yearsOfStudies = data;
    })
  }

  getStudentsForEnrollmentToTheNextYear(yearOfStudy: YearOfStudy){
    this.administrativeStaffService.getStudentsForEnrollmentToTheNextYear(yearOfStudy.id).subscribe((data: Student[]) => {
      this.students = data;
      this.dataSource.data = data;
    })
  }

  enrollmentToTheNextYear(student: Student){
    this.administrativeStaffService.enrollmentToTheNextYear(student, this.enrollmentToTheNextYearForm.get('yearOfStudy').value).subscribe(_ => {
      this.getStudentsForEnrollmentToTheNextYear(this.enrollmentToTheNextYearForm.get('yearOfStudy').value);
      this.snackBarService.openSnackBar("Successfully enrolled student", "X")
    })
  }

  openDialog(student: Student): void {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '250px',
      data: {title: "Enroll student", content: "Are you sure you want to enroll this student?"}
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.enrollmentToTheNextYear(student);
      };
    });
  }

}
