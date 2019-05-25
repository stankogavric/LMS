import { Component, OnInit } from '@angular/core';
import {Subject} from '../../subject/subject.model';
import {SubjectService} from '../../subject/subject.service';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-teacher-subjects',
  templateUrl: './teacher-subjects.component.html',
  styleUrls: ['./teacher-subjects.component.css']
})
export class TeacherSubjectsComponent implements OnInit {

  subjects: Subject[] = [];

  constructor(private subjectService: SubjectService, private authService: AuthService) { }

  ngOnInit() {
    let loggedUser = this.authService.getCurrentUser();
    if (loggedUser) {
      this.getSubjects(loggedUser);
    }
    else {
      console.log("unknown username");
    }
  }

  getSubjects(username: String){
    this.subjectService.getTeachersCurrentSubjects(username).subscribe((data : Subject[]) => {
      this.subjects = data;
    });
  }

}
