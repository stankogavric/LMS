import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SubjectRealization } from 'src/app/subject/subject-realization.model';
import { TeacherService } from 'src/app/teacher/teacher.service';
import { AuthService } from 'src/app/auth/auth.service';
import { TopicService } from '../topic.service';
import { Topic } from '../topic.model';
import { mimeTypeValidator } from 'src/app/validators/mime-type-validator.directive';
import { FormErrorService } from 'src/app/shared/formError.service';

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
  public iconPreview: string;

  constructor(private fb: FormBuilder, private teacherService: TeacherService, private topicService: TopicService, private authService: AuthService, public formError: FormErrorService) { }

  ngOnInit() {
    this.addEditTopicsForm = this.fb.group({
      subjectRealization: ['', {validators: [Validators.required]}],
      topic: ['', {validators: []}],
      week: ['', {validators: [Validators.required]}],
      icon: ['', {asyncValidators: [mimeTypeValidator]}],
    });

    this.getSubjectRealization();
  }

  add(){
    this.weeks.forEach(week => {
      week.topics.forEach(topic => {
        this.topicService.add(topic[0], topic[1]).subscribe();
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
    this.addEditTopicsForm.get('week').value.topics.push([new Topic(this.addEditTopicsForm.get('topic').value, this.addEditTopicsForm.get('week').value.weekNumber, this.addEditTopicsForm.get('subjectRealization').value.subject, null), this.addEditTopicsForm.get('icon').value, this.iconPreview]);
    delete this.iconPreview;
    this.addEditTopicsForm.patchValue({ icon: "" });
    this.addEditTopicsForm.get("icon").updateValueAndValidity();
  }

  addWeek(){
    this.weeks.push(new Week(this.weeks.length+1));
  }

  onImagePicked(event: Event) {
    const file = (event.target as HTMLInputElement).files[0];
    if(file){
      this.addEditTopicsForm.patchValue({ icon: file });
      this.addEditTopicsForm.get("icon").updateValueAndValidity();
      const reader = new FileReader();
      reader.onload = () => {
        this.iconPreview = reader.result as string;
      };
      reader.readAsDataURL(file);
    }
  }

  deleteTopic(week:Week, topic: [Topic, File, string]){
    this.weeks[this.weeks.indexOf(week)].topics.splice(this.weeks[this.weeks.indexOf(week)].topics.indexOf(topic), 1);
  }
  
}

class Week{
  weekNumber:number;
  topics: [Topic, File, string][];

  constructor(weekNumber:number){
    this.weekNumber = weekNumber;
    this.topics = [];
  }
}
