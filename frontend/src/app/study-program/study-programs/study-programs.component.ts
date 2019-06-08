import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { StudyProgram } from '../study-program.model';
import { StudyProgramService } from '../study-program.service';

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

  constructor(private studyProgramService: StudyProgramService) {}

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.getAll();
  }

  getAll(){
    this.studyProgramService.getAll().subscribe((data: StudyProgram[]) => {
      this.studyPrograms = data;
      this.dataSource.data = data;
    });
  }

  delete(id: String){
    this.studyProgramService.delete(id).subscribe((data: any) => {
      this.getAll();
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

}
