import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { MatSort } from '@angular/material/sort';
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
  displayedColumns: string[] = ['no', 'name', 'description', 'headTeacher.personalData.firstName', 'faculty.name', 'faculty.university.name', 'actions'];
  dataSource = new MatTableDataSource<StudyProgram>(this.studyPrograms);

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private studyProgramService: StudyProgramService) {}

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
