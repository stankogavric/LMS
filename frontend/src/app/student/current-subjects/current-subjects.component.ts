import { Component, OnInit } from '@angular/core';
import {Subject} from '../../subject/subject.model';
import {SubjectAttendanceService} from '../../subject/subject-attendance.service'


@Component({
  selector: 'app-current-subjects',
  templateUrl: './current-subjects.component.html',
  styleUrls: ['./current-subjects.component.css']
})
export class CurrentSubjectsComponent implements OnInit {

  panelOpenState = false;

  subjects: Subject[] = [];


  constructor(private subjectAttService: SubjectAttendanceService) { }

  ngOnInit() {
    //TODO fix id from logged student
    this.getCurrentSubjects(1);
  }

  getCurrentSubjects(id: number){
    this.subjectAttService.getCurrentSubjects(id).subscribe((data : Subject[]) => {
      this.subjects = data;
    });
  }

}
