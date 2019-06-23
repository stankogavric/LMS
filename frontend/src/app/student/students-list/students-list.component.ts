import { Component, OnInit, ViewChild } from '@angular/core';
import { Student } from '../student.model';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material';
import { StudentService } from '../student.service';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../auth/auth.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-students-list',
  templateUrl: './students-list.component.html',
  styleUrls: ['./students-list.component.css']
})
export class StudentsListComponent implements OnInit {

  students : Student[] = [];
  displayedColumns: string[] = ['no', 'firstName', 'lastName', 'personalNumber', 'email'];
  dataSource = new MatTableDataSource<Student>(this.students);
  loggedUser : string = this.authService.getCurrentUser();
  @ViewChild(MatPaginator) paginator: MatPaginator;


  constructor(private studentService: StudentService, private authService: AuthService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.getStudents();
  }

  getStudents(){
    this.studentService.getStudentsBySubjectId(this.route.snapshot.paramMap.get('id'), this.loggedUser).subscribe( (data: Student[]) => {
      this.students = data;
      this.dataSource.data = data;
    });

  }

}
