import { Component, OnInit } from '@angular/core';
import {Subject} from '../../subject/subject.model';
import {SubjectService} from '../../subject/subject.service';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-current-subjects',
  templateUrl: './current-subjects.component.html',
  styleUrls: ['./current-subjects.component.css']
})
export class CurrentSubjectsComponent implements OnInit {

  panelOpenState = false;

  subjects: Subject[] = [];


  constructor(private subjectService: SubjectService, private authService: AuthService) {}

  ngOnInit() {
    let loggedUser = this.authService.getCurrentUser();
    if (loggedUser) {
      this.getCurrentSubjects(loggedUser);
    }
    else {
      console.log("unknown username");
    }
  }

  getCurrentSubjects(username: String){
    this.subjectService.getStudentsCurrentSubjects(username).subscribe((data : Subject[]) => {
      this.subjects = data;
      console.log(this.subjects);
    });
  }

}
