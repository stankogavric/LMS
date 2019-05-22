import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SubjectRealization } from 'src/app/subject/subject-realization.model';
import { TeacherService } from 'src/app/teacher/teacher.service';
import { AuthService } from 'src/app/auth/auth.service';
import { TopicService } from '../topic.service';
import { Topic } from '../topic.model';

@Component({
  selector: 'app-add-edit-topics',
  templateUrl: './add-edit-topics.component.html',
  styleUrls: ['./add-edit-topics.component.css']
})
export class AddEditTopicsComponent implements OnInit {

  public addEditTopicsForm : FormGroup;
  public subjectRealizations: SubjectRealization[] = [];
  public topics: String[] = [];
  public weeks: Week[] = [];

  constructor(private fb: FormBuilder, private teacherService: TeacherService, private topicService: TopicService, private authService: AuthService) { }

  ngOnInit() {
    this.addEditTopicsForm = this.fb.group({
      subjectRealization: ['', {validators: [Validators.required]}],
      topic: ['', {validators: []}],
      week: ['', {validators: [Validators.required]}]
    });

    this.getSubjectRealization();
  }

  add(){
    this.weeks.forEach(week => {
      week.topics.forEach(topic => {
        this.topicService.add(new Topic(topic, week.weekNumber, this.addEditTopicsForm.get('subjectRealization').value.subject)).subscribe();
      })
    });
  }

  getSubjectRealization(){
    let loggedUser = this.authService.getCurrentUser();
    this.teacherService.getSubjectRealization(loggedUser).subscribe(data => {
      this.subjectRealizations = data;
    });
  }

  addTopic(){
    this.addEditTopicsForm.get('week').value.topics.push(this.addEditTopicsForm.get('topic').value);
  }

  addWeek(){
    this.weeks.push(new Week(this.weeks.length+1));
  }
  
}

class Week{
  weekNumber:number;
  topics: String[];

  constructor(weekNumber:number){
    this.weekNumber = weekNumber;
    this.topics = [];
  }
}
