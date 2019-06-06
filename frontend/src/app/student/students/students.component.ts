import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { MatSort } from '@angular/material/sort';
import { Student } from '../student.model';
import { StudentService } from '../student.service';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})
export class StudentsComponent implements OnInit {

  students : Student[] = [];
  student : Student = new Student();
  displayedColumns: string[] = ['no', 'personalData.firstName', 'personalData.lastName', 'personalData.personalNumber', 'personalData.profilePicturePath', 'address.city.country.name', 'address.city.name', 'address.street', 'address.number', 'accountData.email', 'accountData.username', 'actions'];
  dataSource = new MatTableDataSource<Student>(this.students);

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private studentService: StudentService) {}

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
    this.studentService.getAll().subscribe((data: Student[]) => {
      this.students = data;
      this.dataSource.data = data;
    });
  }

  delete(id: string){
    this.studentService.delete(id).subscribe((data: any) => {
      this.getAll();
    });
  }

  update(id: string, student: Student, image: File){
    this.studentService.update(id, student, image).subscribe((data: any) => {
      this.getAll();
    });
  }

}
