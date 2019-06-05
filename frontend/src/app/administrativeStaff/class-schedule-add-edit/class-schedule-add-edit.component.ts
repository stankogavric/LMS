import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { YearOfStudyService } from 'src/app/year-of-study/year-of-study.service';
import { YearOfStudy } from 'src/app/year-of-study/year-of-study.model';
import { TeacherRealizationService } from 'src/app/teacher/teacher-realization.service';
import { TeacherRealization } from 'src/app/teacher/teacher-realization.model';
import { Classroom } from 'src/app/classroom/classroom.model';
import { ClassroomService } from 'src/app/classroom/classroom.service';
import { Time } from '@angular/common';
import { TeachingTerm } from 'src/app/teacher/teaching-term.model';
import { TeachingTermService } from 'src/app/teacher/teaching-term.service';

@Component({
  selector: 'app-class-schedule-add-edit',
  templateUrl: './class-schedule-add-edit.component.html',
  styleUrls: ['./class-schedule-add-edit.component.css']
})
export class ClassScheduleAddEditComponent implements OnInit {

  public classScheduleForm : FormGroup;
  public classSchedule = [[], [], [], [], [], [], []];
  public yearsOfStudies: YearOfStudy[] = [];
  public teacherRealizations: TeacherRealization[];
  public classrooms: Classroom[];
  public days: String[] = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'];

  constructor(private fb: FormBuilder, private teacherRealizationService: TeacherRealizationService, private yearOfStudyService: YearOfStudyService, private classroomService: ClassroomService, private teachingTermService: TeachingTermService) { }

  ngOnInit() {
    this.classScheduleForm = this.fb.group({
      yearOfStudy: ['', {validators: [Validators.required]}],
      teacherRealization: ['', {validators: [Validators.required]}],
      day: ['', {validators: [Validators.required]}],
      startTime: ['', {validators: [Validators.required]}],
      classroom: ['', {validators: [Validators.required]}]
    });

    this.getYearsOfStudies();
    this.getClassrooms();
  }

  getYearsOfStudies(){
    this.yearOfStudyService.getAll().subscribe(data => {
      this.yearsOfStudies = data;
    })
  }

  getTeacherRealizations(yearOfStudyId: number){
    this.teacherRealizationService.getAllByYearOfStudy(yearOfStudyId).subscribe(data => {
      this.teacherRealizations = data;
      this.getTeachingTerms(yearOfStudyId);
    });
  }

  getClassrooms(){
    this.classroomService.getAll().subscribe(data => {
      this.classrooms = data;
    });
  }

  getTeachingTerms(yearOfStudyId: number){
    this.classSchedule = [[], [], [], [], [], [], []];
    this.teachingTermService.getAllByYearOfStudy(yearOfStudyId).subscribe(data => {
      data.forEach(teachingTerm => {
        let teacherRealization: TeacherRealization;
        this.teacherRealizations.forEach(tr => { // or get teacher realization by subject realization id
          if(tr.subjectRealization.id == teachingTerm.subjectRealization.id){
            teacherRealization = tr;
            return false;
          }
        });
        this.classSchedule[teachingTerm.day].push({
          "teachingTermId": teachingTerm.id,
          "teacherRealization": teacherRealization,
          "day": teachingTerm.day,
          "startTime": new Date(teachingTerm.startTime),
          "endTime": new Date(teachingTerm.endTime),
          "classroom": teachingTerm.classroom
        });
      })
    })
  }

  add(){
    let valid = true;
    let endTimeT: Time = {
      hours: +this.classScheduleForm.get('startTime').value.substring(0,2), // '+' convert string to number
      minutes: +this.classScheduleForm.get('startTime').value.substring(3)
    };
    endTimeT.hours=(endTimeT.hours+this.classScheduleForm.get('teacherRealization').value.numberOfClasses);
    
    let startTimeT: Time = {
      hours: +this.classScheduleForm.get('startTime').value.substring(0,2), // '+' convert string to number
      minutes: +this.classScheduleForm.get('startTime').value.substring(3)
    };

    let startTimeD = new Date();
    startTimeD.setHours(startTimeT.hours);
    startTimeD.setMinutes(startTimeT.minutes);

    let endTimeD = new Date();
    endTimeD.setHours(endTimeT.hours);
    endTimeD.setMinutes(endTimeT.minutes);

    let startTime = startTimeD.getTime();
    let endTime = endTimeD.getTime();

    this.classSchedule.forEach(day => {
      day.forEach(item => {
        let itemStartTimeD = new Date();
        itemStartTimeD.setHours(item.startTime.getHours());
        itemStartTimeD.setMinutes(item.startTime.getMinutes());
        let itemEndTimeD = new Date();
        itemEndTimeD.setHours(item.endTime.getHours());
        itemEndTimeD.setMinutes(item.endTime.getMinutes());
        let itemStartTime = itemStartTimeD.getTime();
        let itemEndTime = itemEndTimeD.getTime();

        if(
          item.day == this.classScheduleForm.get('day').value
          &&(
          (startTime > itemStartTime
          &&
          startTime < itemEndTime)
          ||
          (endTime > itemStartTime
          &&
          endTime < itemEndTime)
          ||
          (startTime < itemStartTime
          &&
          endTime > itemEndTime)
          ||
          (startTime == itemStartTime
          &&
          endTime == itemEndTime))
        )
        {
          alert("Termin je zauzet");
          valid = false;
          return false;
        }
      })
    })


    if(valid){
      this.classSchedule[this.classScheduleForm.get('day').value].push(
        {
          //"yearOfStudy": this.classScheduleForm.get('yearOfStudy').value,
          "teacherRealization": this.classScheduleForm.get('teacherRealization').value,
          "day": this.classScheduleForm.get('day').value,
          "startTime": startTimeD,
          "endTime": endTimeD,
          "classroom": this.classScheduleForm.get('classroom').value
        }
      );
      this.classScheduleForm.patchValue({teacherRealization: ''});
    }
  }

  save(){
    this.classSchedule.forEach(day => {
      day.forEach(item => {
        let startTime = new Date();
        startTime.setHours(item.startTime.getHours());
        startTime.setMinutes(item.startTime.getMinutes());
        startTime.setSeconds(0);
        let endTime = new Date();
        endTime.setHours(item.endTime.getHours());
        endTime.setMinutes(item.endTime.getMinutes());
        endTime.setSeconds(0);
        let teachingTerm = new TeachingTerm(item.day, startTime, endTime, item.teacherRealization.subjectRealization, item.classroom);
        if(item.teachingTermId){ // already exists
          //this.teachingTermService.update(item.teachingTermId, teachingTerm).subscribe();
        }
        else{
          this.teachingTermService.add(teachingTerm).subscribe();
        }
      });
    });
    this.classScheduleForm.reset();
    this.classSchedule = [[], [], [], [], [], [], []];
    this.teacherRealizations = [];
  }

  delete(teachingTermId, item, day){
    if(teachingTermId){
      this.teachingTermService.delete(teachingTermId).subscribe();
    }
    day.splice(day.indexOf(item), 1);
  }

  drop(event: CdkDragDrop<string[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(event.previousContainer.data,
                        event.container.data,
                        event.previousIndex,
                        event.currentIndex);
    }
  }
}