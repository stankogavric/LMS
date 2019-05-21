import { Component, OnInit } from '@angular/core';
import {Subject} from '../../subject/subject.model';
import {SubjectService} from '../../subject/subject.service';
import { AuthService} from '../../auth/auth.service';

@Component({
  selector: 'app-past-subjects',
  templateUrl: './past-subjects.component.html',
  styleUrls: ['./past-subjects.component.css']
})
export class PastSubjectsComponent implements OnInit {

  panelOpenState = false;


  subjects: Subject[] = [];
  avgGrade : number = 0; //TODO after backend is finished with all classes
  totalEcts : number = 0;
  
  constructor(private subjectService: SubjectService, private authService: AuthService) { }

  ngOnInit() {
    let loggedUser = this.authService.getCurrentUser();
    if (loggedUser) {
      this.getPastSubjects(loggedUser);
    }
    else {
      console.log("unknown username");
    }   
    
  }

  getPastSubjects(username: String){
    this.subjectService.getStudentsPastSubjects(username).subscribe((data : Subject[]) => {
      this.subjects = data;
      this.getAvgGrade();
      this.getTotalEcts();
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

  getTotalEcts() {
    let ects = 0;
    for (let i = 0; i < this.subjects.length; i++) {
      if(this.subjects[i].ects != null){
       ects+= this.subjects[i].ects;
      }
    }
    this.totalEcts = ects;
  }





}
