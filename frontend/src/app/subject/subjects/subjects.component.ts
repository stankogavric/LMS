import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { Subject } from '../subject.model';
import { SubjectService } from '../subject.service';

@Component({
  selector: 'app-subjects',
  templateUrl: './subjects.component.html',
  styleUrls: ['./subjects.component.css']
})
export class SubjectsComponent implements OnInit {

  subjects : Subject[] = [];
  subject : Subject = new Subject();
  displayedColumns: string[] = ['no', 'name', 'ects', 'mandatory', 'lecturesNum', 'exercisesNum', 'otherActivitesNum', 'researchPaper', 'otherClasses', 'actions'];
  dataSource = new MatTableDataSource<Subject>(this.subjects);

  @ViewChild(MatPaginator) paginator: MatPaginator;


  constructor(private subjectService: SubjectService) {}

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.getAll();
  }

  getAll(){
    this.subjectService.getAll().subscribe((data: Subject[]) => {
      this.subjects = data;
      this.dataSource.data = data;
    });
  }

  delete(id: String){
    this.subjectService.delete(id).subscribe((data: any) => {
      this.getAll();
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

}
