import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { MatSort } from '@angular/material/sort';
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
  displayedColumns: string[] = ['no', 'personalData.firstName', 'personalData.lastName', 'personalData.personalNumber', 'personalData.profilePicturePath', 'address.city.country.name', 'address.city.name', 'address.street', 'address.number', 'accountData.email', 'accountData.username', 'biography', 'actions'];
  dataSource = new MatTableDataSource<Teacher>(this.teachers);

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private teacherService: TeacherService) {}

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sortingDataAccessor = (item, property) => {
      if (property.includes('.')) return property.split('.').reduce((o,i)=>o[i], item)
        return item[property];
    };
    this.dataSource.sort = this.sort;
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

  update(id: string, teacher: Teacher, image: File){
    this.teacherService.update(id, teacher, image).subscribe((data: any) => {
      this.getAll();
    });
  }

}
