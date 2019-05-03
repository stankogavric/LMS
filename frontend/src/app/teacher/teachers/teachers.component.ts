import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { Teacher } from '../teacher.model';
import { TeacherService } from '../teacher.service';

@Component({
  selector: 'app-teachers',
  templateUrl: './teachers.component.html',
  styleUrls: ['./teachers.component.css']
})
export class TeachersComponent implements OnInit {

  teachers : Teacher[] = [];
  teacher : Teacher = new Teacher();
  displayedColumns: string[] = ['no', 'firstName', 'lastName', 'personalNumber', 'profilePicturePath', 'country', 'city', 'street', 'streetNumber', 'email', 'username', 'biography', 'actions'];
  dataSource = new MatTableDataSource<Teacher>(this.teachers);

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private teacherService: TeacherService) {}

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.getAll();
  }

  getAll(){
    this.teacherService.getAll().subscribe((data: Teacher[]) => {
      this.teachers = data;
      this.dataSource.data = data;
    });
  }

  delete(id: String){
    this.teacherService.delete(id).subscribe((data: any) => {
      this.getAll();
    });
  }

  add(){
    this.teacherService.add(this.teacher).subscribe((data: any) => {
      this.getAll();
    });
  }

  update(id: string, teacher: Teacher){
    this.teacherService.update(id, teacher).subscribe((data: any) => {
      this.getAll();
    });
  }

}
