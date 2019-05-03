import { Component, OnInit } from '@angular/core';
import {Subject} from '../../subject/subject.model';
import {SubjectAttendanceService} from '../../subject/subject-attendance.service'

@Component({
  selector: 'app-past-subjects',
  templateUrl: './past-subjects.component.html',
  styleUrls: ['./past-subjects.component.css']
})
export class PastSubjectsComponent implements OnInit {

  panelOpenState = false;

  subjects: Subject[] = [];
  avgGrade : number = 0; //TODO after backend is finished with all classes
  


  constructor(private subjectAttService: SubjectAttendanceService) { }

  ngOnInit() {
    //TODO fix id from logged student
    this.getPastSubjects(1);
    
  }

  getPastSubjects(id: number){
    this.subjectAttService.getPastSubjects(id).subscribe((data : Subject[]) => {
      this.subjects = data;
      this.getAvgGrade();
    });
  }

  getAvgGrade(){
    let sum = 0;
    let total = 0
    for (let i = 0; i < this.subjects.length; i++) {
      if (this.subjects[i][1] != null) {
        sum += this.subjects[i][1];
        total++;
      }
    };
    this.avgGrade = sum/total;
  }



}
