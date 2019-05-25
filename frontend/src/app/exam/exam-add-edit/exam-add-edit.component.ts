import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ExamService } from '../exam.service';
import { ExamType } from '../exam-type.model';
import { Exam } from '../exam.model';
import { SubjectRealization } from 'src/app/subject/subject-realization.model';
import { TeacherService } from 'src/app/teacher/teacher.service';
import { AuthService } from 'src/app/auth/auth.service';
import {CdkDragDrop, moveItemInArray} from '@angular/cdk/drag-drop';
import { ExamTopic } from 'src/app/exam-topic/exam-topic.model';
import { ExamTopicService } from '../exam-topic.service';

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
  public topics: String[] = [];

  constructor(private fb: FormBuilder, private examService: ExamService, private examTopicService: ExamTopicService, private teacherService: TeacherService, private authService: AuthService) { }

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
    this.examService.add(this.exam).subscribe(exam =>{
      this.topics.forEach(topic => {
        this.examTopicService.add(new ExamTopic(topic, exam)).subscribe();
      });
    });
  }

  getSubjectRealization(){
    let loggedUser = this.authService.getCurrentUser();
    this.teacherService.getSubjectRealization(loggedUser).subscribe(data => {
      this.subjectRealizations = data;
    });
  }

  addTopic(){
    this.topics.push(this.examAddEditForm.get('topic').value);
  }

  drop(event: CdkDragDrop<string[]>) {
    moveItemInArray(this.topics, event.previousIndex, event.currentIndex);
  }
  
}
