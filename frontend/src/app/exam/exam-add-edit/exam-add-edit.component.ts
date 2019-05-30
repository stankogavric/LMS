import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ExamService } from '../exam.service';
import { ExamType } from '../exam-type.model';
import { Exam } from '../exam.model';
import { SubjectRealization } from 'src/app/subject/subject-realization.model';
import { TeacherService } from 'src/app/teacher/teacher.service';
import { AuthService } from 'src/app/auth/auth.service';
import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import { ExamTopic } from 'src/app/exam-topic/exam-topic.model';

@Component({
  selector: 'app-exam-add-edit',
  templateUrl: './exam-add-edit.component.html',
  styleUrls: ['./exam-add-edit.component.css']
})
export class ExamAddEditComponent implements OnInit {

  public examAddEditForm : FormGroup;
  public exam: Exam = new Exam();
  public examTypes: ExamType[] = [];
  public subjectRealizations: SubjectRealization[] = [];
  public topics: ExamTopic[] = [];

  constructor(private fb: FormBuilder, private examService: ExamService, private teacherService: TeacherService, private authService: AuthService) { }

  ngOnInit() {
    this.examAddEditForm = this.fb.group({
      points: ['', {validators: [Validators.required]}],
      examType: ['', {validators: [Validators.required]}],
      subjectRealization: ['', {validators: [Validators.required]}],
      topic: ['', {validators: []}],
      durationInMinutes: ['', {validators: [Validators.required]}]
    });

    this.getSubjectRealization();
    this.getExamTypes();
  }

  getExamTypes(){
    this.examService.getExamTypes().subscribe((data: ExamType[]) => {
      this.examTypes = data;
    });
  }

  add(){
    const e = this.examAddEditForm.value;
    delete e.topic;
    this.exam = e;
    this.exam.syllabus = this.topics;


    /*
    this.exam.syllabus = [];
    this.topics.forEach(topic => {
      this.exam.syllabus.push(new ExamTopic(topic, null));
    })*/
    this.examService.add(this.exam).subscribe(/*exam =>{
      this.topics.forEach(topic => {
        this.examTopicService.add(new ExamTopic(topic, exam)).subscribe();
      });
  }*/);
  }

  getSubjectRealization(){
    let loggedUser = this.authService.getCurrentUser();
    this.teacherService.getSubjectRealization(loggedUser).subscribe(data => {
      this.subjectRealizations = data;
    });
  }

  addTopic(){
    this.topics.push(new ExamTopic(this.examAddEditForm.get('topic').value, null));
  }

  deleteTopic(topic: ExamTopic){
    this.topics.splice(this.topics.indexOf(topic), 1);
  }

  drop(event: CdkDragDrop<string[]>) {
    moveItemInArray(this.topics, event.previousIndex, event.currentIndex);
  }
  
}
