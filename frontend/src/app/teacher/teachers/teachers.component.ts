import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { Teacher } from '../teacher.model';
import { TeacherService } from '../teacher.service';
import { saveAs } from 'file-saver';
import { FileService } from 'src/app/file/file.service';

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

  constructor(private teacherService: TeacherService, private fileService: FileService) {}

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

  update(id: string, teacher: Teacher, image: File){
    this.teacherService.update(id, teacher, image).subscribe((data: any) => {
      this.getAll();
    });
  }

  exportDataToXML() {
    this.fileService.exportDataToXML({'fileUrl': this.teacherService.teacherUrl, 'fileName': 'teachers.xml'}).subscribe(data => {
      saveAs(new Blob([data], { type: 'application/xml' }), 'teachers.xml');
    });
  }
  
  exportDataToPDF() {
    this.fileService.exportDataToPDF({'fileUrl': this.teacherService.teacherUrl, 'fileName': 'teachers.pdf'}).subscribe(data => {
      saveAs(new Blob([data], { type: 'application/pdf' }), 'teachers.pdf');
    });
  }

}
