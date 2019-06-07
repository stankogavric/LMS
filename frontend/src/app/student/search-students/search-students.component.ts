import { Component, OnInit, ViewChild } from '@angular/core';
import { Student } from '../student.model';
import { StudentService } from '../student.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material';
import { FormGroup, FormBuilder } from '@angular/forms';
import { FormErrorService } from '../../shared/formError.service';

@Component({
  selector: 'app-search-students',
  templateUrl: './search-students.component.html',
  styleUrls: ['./search-students.component.css']
})
export class SearchStudentsComponent implements OnInit {

  students: Student[];
  studentDetailed: Student;
  public searchForm : FormGroup;
  displayedColumns: string[] = ['no', 'firstName', 'lastName', 'email', 'detail'];
  dataSource = new MatTableDataSource<Student>(this.students);
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private studentService: StudentService, public formError: FormErrorService, private fb: FormBuilder) { }

  ngOnInit() {
    this.searchForm = this.fb.group({
      firstName: [""],
      lastName: [""],
      indexNum: [""],
      enrolment: [""],
      avgGrade: [""]
    });

  }

  search() {
    let query = {};
    query = this.searchForm.value;
    this.studentService.searchStudents(query)
      .subscribe((data: Student[]) => {
        this.students = data;
        this.dataSource.data = data;
      });
  }

}
