import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatTableDataSource, MatDialog } from '@angular/material';
import { Subject } from '../subject.model';
import { SubjectService } from '../subject.service';
import { ConfirmationDialogComponent } from 'src/app/shared/confirmation-dialog/confirmation-dialog.component';
import { SnackBarService } from 'src/app/shared/snack-bar.service';

@Component({
  selector: 'app-subjects',
  templateUrl: './subjects.component.html',
  styleUrls: ['./subjects.component.css']
})
export class SubjectsComponent implements OnInit {

  subjects : Subject[] = [];
  subject : Subject = new Subject();
  displayedColumns: string[] = ['no', 'name', 'ects', 'mandatory', 'lecturesNum', 'exercisesNum', 'otherActivitiesNum', 'researchPaper', 'otherClasses', 'actions'];
  dataSource = new MatTableDataSource<Subject>(this.subjects);

  @ViewChild(MatPaginator) paginator: MatPaginator;


  constructor(private subjectService: SubjectService, public dialog: MatDialog, private snackBarService: SnackBarService) {}

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.getAll();
  }

  getAll(){
    this.subjectService.getAll().subscribe((data: Subject[]) => {
      this.subjects = data;
      this.dataSource.data = data;
      this.dataSource.filterPredicate = function(data, filter): boolean {
        let mandatory: string = "yes";
        if(!data.mandatory){
          mandatory = "no"
        }
        return data.name.toLowerCase().includes(filter) || 
                data.ects.toString().includes(filter) ||
                mandatory.includes(filter) ||
                data.lecturesNum.toString().includes(filter) ||
                data.exercisesNum.toString().includes(filter) ||
                data.otherActivitiesNum.toString().includes(filter) ||
                data.researchPaper.toString().includes(filter) ||
                data.otherClasses.toString().includes(filter);
      };
    });
  }

  delete(id: String){
    this.subjectService.delete(id).subscribe((data: any) => {
      this.getAll();
      this.snackBarService.openSnackBar("Successfully deleted subject", "X")
    });
  }

  add(){
    this.subjectService.add(this.subject).subscribe((data: any) => {
      this.getAll();
    });
  }

  update(id: string, subject: Subject){
    this.subjectService.update(id, subject).subscribe((data: any) => {
      this.getAll();
    });
  }

  openDialog(id: String): void {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '250px',
      data: {title: "Delete subject", content: "Are you sure you want to delete this subject?"}
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
