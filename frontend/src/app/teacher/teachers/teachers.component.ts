import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatTableDataSource, MatDialog } from '@angular/material';
import { Teacher } from '../teacher.model';
import { TeacherService } from '../teacher.service';
import { saveAs } from 'file-saver';
import { FileService } from 'src/app/file/file.service';
import { ConfirmationDialogComponent } from 'src/app/shared/confirmation-dialog/confirmation-dialog.component';
import { SnackBarService } from 'src/app/shared/snack-bar.service';

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

  constructor(private teacherService: TeacherService, private fileService: FileService, public dialog: MatDialog, private snackBarService: SnackBarService) {}

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.getAll();
  }

  getAll(){
    this.teacherService.getAll().subscribe((data: Teacher[]) => {
      this.teachers = data;
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
                data.accountData.username.toLowerCase().includes(filter) ||
                data.biography.toLowerCase().includes(filter);
      };
    });
  }

  delete(id: String){
    this.teacherService.delete(id).subscribe((data: any) => {
      this.getAll();
      this.snackBarService.openSnackBar("Successfully deleted teacher", "X")
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

  openDialog(id: String): void {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '250px',
      data: {title: "Delete teacher", content: "Are you sure you want to delete this teacher?"}
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
