import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatTableDataSource, MatDialog } from '@angular/material';
import { StudyProgram } from '../study-program.model';
import { StudyProgramService } from '../study-program.service';
import { ConfirmationDialogComponent } from 'src/app/shared/confirmation-dialog/confirmation-dialog.component';
import { SnackBarService } from 'src/app/shared/snack-bar.service';

@Component({
  selector: 'app-study-programs',
  templateUrl: './study-programs.component.html',
  styleUrls: ['./study-programs.component.css']
})
export class StudyProgramsComponent implements OnInit {

  studyPrograms : StudyProgram[] = [];
  studyProgram : StudyProgram = new StudyProgram();
  displayedColumns: string[] = ['no', 'name', 'description', 'headTeacher', 'faculty', 'university', 'actions'];
  dataSource = new MatTableDataSource<StudyProgram>(this.studyPrograms);

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private studyProgramService: StudyProgramService, public dialog: MatDialog, private snackBarService: SnackBarService) {}

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.getAll();
  }

  getAll(){
    this.studyProgramService.getAll().subscribe((data: StudyProgram[]) => {
      this.studyPrograms = data;
      this.dataSource.data = data;
      this.dataSource.filterPredicate = function(data, filter): boolean {
        return data.name.toLowerCase().includes(filter) || 
                data.description.toLowerCase().includes(filter) ||
                data.headTeacher.personalData.firstName.toLowerCase().includes(filter) ||
                data.headTeacher.personalData.lastName.toLowerCase().includes(filter) ||
                data.faculty.name.toLowerCase().includes(filter) ||
                data.faculty.university.name.toLowerCase().includes(filter);
      };
    });
  }

  delete(id: String){
    this.studyProgramService.delete(id).subscribe((data: any) => {
      this.getAll();
      this.snackBarService.openSnackBar("Successfully deleted study program", "X");
    });
  }

  add(){
    this.studyProgramService.add(this.studyProgram).subscribe((data: any) => {
      this.getAll();
    });
  }

  update(id: string, studyProgram: StudyProgram){
    this.studyProgramService.update(id, studyProgram).subscribe((data: any) => {
      this.getAll();
    });
  }

  openDialog(id: String): void {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '250px',
      data: {title: "Delete study program", content: "Are you sure you want to delete this study program?"}
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
