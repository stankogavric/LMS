import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatTableDataSource, MatDialog } from '@angular/material';
import { Student } from '../student.model';
import { StudentService } from '../student.service';
import { saveAs } from 'file-saver';
import { FileService } from 'src/app/file/file.service';
import { ConfirmationDialogComponent } from 'src/app/shared/confirmation-dialog/confirmation-dialog.component';
import { SnackBarService } from 'src/app/shared/snack-bar.service';

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

  constructor(private studentService: StudentService, private fileService: FileService, public dialog: MatDialog, private snackBarService: SnackBarService) {}

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.getAll();
  }

  getAll(){
    this.studentService.getAll().subscribe((data: Student[]) => {
      this.students = data;
      this.dataSource.data = data;
      this.dataSource.filterPredicate = function(data, filter): boolean {
        return data.personalData.firstName.toLowerCase().includes(filter) ||
                data.personalData.lastName.toLowerCase().includes(filter) || 
                data.personalData.personalNumber.toLowerCase().includes(filter) ||
                data.personalData.profilePicturePath.toLowerCase().includes(filter) || 
                data.address.city.country.name.toLowerCase().includes(filter) ||
                data.address.city.name.toLowerCase().includes(filter) || 
                data.address.street.toLowerCase().includes(filter) ||
                data.address.number.toLowerCase().includes(filter) || 
                data.accountData.email.toLowerCase().includes(filter) ||
                data.accountData.username.toLowerCase().includes(filter);
      };
    });
  }

  delete(id: string){
    this.studentService.delete(id).subscribe((data: any) => {
      this.getAll();
      this.snackBarService.openSnackBar("Successfully deleted student", "X")
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

  openDialog(id: string): void {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '250px',
      data: {title: "Delete student", content: "Are you sure you want to delete this student?"}
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.delete(id);
      };
    });
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
