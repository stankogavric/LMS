import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { Student } from '../student.model';
import { StudentService } from '../student.service';
import { saveAs } from 'file-saver';
import { FileService } from 'src/app/file/file.service';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})
export class StudentsComponent implements OnInit {

  students : Student[] = [];
  student : Student = new Student();
  displayedColumns: string[] = ['no', 'firstName', 'lastName', 'personalNumber', 'profilePicturePath', 'country', 'city', 'street', 'streetNumber', 'email', 'username', 'actions'];
  dataSource = new MatTableDataSource<Student>(this.students);

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private studentService: StudentService, private fileService: FileService) {}

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
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

  exportDataToXML() {
    this.fileService.exportDataToXML({'fileUrl': this.studentService.studentUrl, 'fileName': 'students.xml'}).subscribe(data => {
      saveAs(new Blob([data], { type: 'application/xml' }), 'students.xml');
    });
  }
  
  exportDataToPDF() {
    this.fileService.exportDataToPDF({'fileUrl': this.studentService.studentUrl, 'fileName': 'students.pdf'}).subscribe(data => {
      saveAs(new Blob([data], { type: 'application/pdf' }), 'students.pdf');
    });
  }

}
