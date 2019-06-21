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
import { ConfirmationDialogComponent } from 'src/app/shared/confirmation-dialog/confirmation-dialog.component';
import { MatDialog } from '@angular/material';
import { SnackBarService } from 'src/app/shared/snack-bar.service';

@Component({
  selector: 'app-class-schedule-add-edit',
  templateUrl: './class-schedule-add-edit.component.html',
  styleUrls: ['./class-schedule-add-edit.component.css']
})
export class ClassScheduleAddEditComponent implements OnInit {

  public classScheduleForm : FormGroup;
  public classSchedule = [[], [], [], [], [], [], []];
  public yearsOfStudies: YearOfStudy[] = [];
  public teacherRealizations: TeacherRealization[] = [];
  public teacherRealizationsByYearOfStudy: TeacherRealization[] = [];
  public classrooms: Classroom[] = [];
  public teachingTerms: TeachingTerm[] = [];
  public days: String[] = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'];
  public selectedDay = 0;
  public selectedTeacherRealization: TeacherRealization;
  public selectedClassroom: Classroom;
  private selectedStartTime: Date;

  constructor(private fb: FormBuilder, private teacherRealizationService: TeacherRealizationService, private yearOfStudyService: YearOfStudyService, private classroomService: ClassroomService, private teachingTermService: TeachingTermService, public dialog: MatDialog, private snackBarService: SnackBarService) { }

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
    //this.getTeachingTerms();
    this.getTeacherRealizations();
  }

  getYearsOfStudies(){
    this.yearOfStudyService.getAll().subscribe(data => {
      this.yearsOfStudies = data;
    })
  }

  getTeacherRealizationsByYearOfStudy(id: number){
    this.teacherRealizationService.getAllByYearOfStudy(id).subscribe(data => {
      this.teacherRealizationsByYearOfStudy = data;
      this.getTeachingTermsByYearOfStudy(id);
    });
  }

  getTeacherRealizations(){
    this.teacherRealizationService.getAll().subscribe(data => {
      this.teacherRealizations = data;
    });
  }

  getClassrooms(){
    this.classroomService.getAll().subscribe(data => {
      this.classrooms = data;
    });
  }

  getTeachingTerms(){
    this.teachingTermService.getAll().subscribe(data => {
      this.teachingTerms = data;
    });
  }

  getTeachingTermsByYearOfStudy(id: number){
    this.classSchedule = [[], [], [], [], [], [], []];
    this.teachingTermService.getAll().subscribe(data => {
      this.teachingTerms = data;
      data.forEach(teachingTerm => {
        let teacherRealization: TeacherRealization;
        this.teacherRealizations.forEach(tr => { // or get teacher realization by subject realization id
          if(tr.subjectRealization.id == teachingTerm.subjectRealization.id && tr.subjectRealization.yearOfStudy.id == id){
            teacherRealization = tr;
            return false;
          }
        });
        if(teachingTerm.subjectRealization.yearOfStudy.id == this.classScheduleForm.get('yearOfStudy').value.id){
          this.classSchedule[teachingTerm.day].push({
            "teachingTermId": teachingTerm.id,
            "teacherRealization": teacherRealization,
            "day": teachingTerm.day,
            "startTime": new Date(teachingTerm.startTime),
            "endTime": new Date(teachingTerm.endTime),
            "classroom": teachingTerm.classroom
          });
        }
      })
    });
  }

  check():Boolean{
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
    startTimeD.setSeconds(0);

    let endTimeD = new Date();
    endTimeD.setHours(endTimeT.hours);
    endTimeD.setMinutes(endTimeT.minutes);
    endTimeD.setSeconds(0);

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
          &&
          (
            (startTime > itemStartTime && startTime < itemEndTime)
            ||
            (endTime > itemStartTime && endTime < itemEndTime)
            ||
            (startTime < itemStartTime && endTime > itemEndTime)
            ||
            (startTime == itemStartTime && endTime == itemEndTime)
          )
        )
        {
          this.snackBarService.openSnackBar("This teaching term is busy", "X")
          valid = false;
          return false;
        }
      })
    })

    let item = {
      //"yearOfStudy": this.classScheduleForm.get('yearOfStudy').value,
      "teacherRealization": this.classScheduleForm.get('teacherRealization').value,
      "day": this.classScheduleForm.get('day').value,
      "startTime": startTimeD,
      "endTime": endTimeD,
      "classroom": this.classScheduleForm.get('classroom').value
    }

    /*
    this.teacherRealizations.forEach(teacherRealization => {
      if(
        teacherRealization.teacher.id == item.teacherRealization.teacher.id
        &&
        (
          item.day == this.classScheduleForm.get('day').value
          &&
          (
            (startTime > itemStartTime && startTime < itemEndTime)
            ||
            (endTime > itemStartTime && endTime < itemEndTime)
            ||
            (startTime < itemStartTime && endTime > itemEndTime)
            ||
            (startTime == itemStartTime && endTime == itemEndTime)
          )
        )
      )
      {
        alert("Termin je zauzet");
        valid = false;
        return false;
      }
    })
    */
    this.teachingTerms.forEach(teachingTerm => {
      if(teachingTerm.subjectRealization.yearOfStudy.id != this.classScheduleForm.get('yearOfStudy').value.id){
        let startTime = new Date(teachingTerm.startTime).getTime();
        let endTime = new Date(teachingTerm.endTime).getTime();
        
        let itemStartTimeD = new Date(teachingTerm.startTime);
        itemStartTimeD.setHours(item.startTime.getHours());
        itemStartTimeD.setMinutes(item.startTime.getMinutes());
        let itemEndTimeD = new Date(teachingTerm.endTime);
        itemEndTimeD.setHours(item.endTime.getHours());
        itemEndTimeD.setMinutes(item.endTime.getMinutes());
        let itemStartTime = itemStartTimeD.getTime();
        let itemEndTime = itemEndTimeD.getTime();

        let teacherFree = true;
        this.teacherRealizations.forEach(teacherRealization => {
          if(
              teacherRealization.teacher.id == item.teacherRealization.teacher.id
              &&
              teacherRealization.subjectRealization.id == teachingTerm.subjectRealization.id
            ){
              teacherFree = false;
          }
        })

        if(
          (
            teachingTerm.subjectRealization.yearOfStudy.id == item.teacherRealization.subjectRealization.yearOfStudy.id
            ||
            teachingTerm.classroom.id == item.classroom.id
            ||
            !teacherFree
          )
          &&
          (
            teachingTerm.day == this.classScheduleForm.get('day').value
            &&
            (
              (startTime > itemStartTime && startTime < itemEndTime)
              ||
              (endTime > itemStartTime && endTime < itemEndTime)
              ||
              (startTime < itemStartTime && endTime > itemEndTime)
              ||
              (startTime == itemStartTime && endTime == itemEndTime)
            )
          )
        )
        {
          this.snackBarService.openSnackBar("This teaching term is busy", "X")
          valid = false;
          return false;
        }
      }
    })
    return valid;
  }

  add(){
    //let valid = true;
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
    startTimeD.setSeconds(0);

    let endTimeD = new Date();
    endTimeD.setHours(endTimeT.hours);
    endTimeD.setMinutes(endTimeT.minutes);
    endTimeD.setSeconds(0);
/*
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
          &&
          (
            (startTime > itemStartTime && startTime < itemEndTime)
            ||
            (endTime > itemStartTime && endTime < itemEndTime)
            ||
            (startTime < itemStartTime && endTime > itemEndTime)
            ||
            (startTime == itemStartTime && endTime == itemEndTime)
          )
        )
        {
          alert("Termin je zauzet");
          valid = false;
          return false;
        }
      })
    })

    let item = {
      //"yearOfStudy": this.classScheduleForm.get('yearOfStudy').value,
      "teacherRealization": this.classScheduleForm.get('teacherRealization').value,
      "day": this.classScheduleForm.get('day').value,
      "startTime": startTimeD,
      "endTime": endTimeD,
      "classroom": this.classScheduleForm.get('classroom').value
    }

    
    this.teacherRealizations.forEach(teacherRealization => {
      if(
        teacherRealization.teacher.id == item.teacherRealization.teacher.id
        &&
        (
          item.day == this.classScheduleForm.get('day').value
          &&
          (
            (startTime > itemStartTime && startTime < itemEndTime)
            ||
            (endTime > itemStartTime && endTime < itemEndTime)
            ||
            (startTime < itemStartTime && endTime > itemEndTime)
            ||
            (startTime == itemStartTime && endTime == itemEndTime)
          )
        )
      )
      {
        alert("Termin je zauzet");
        valid = false;
        return false;
      }
    })
    
    this.teachingTerms.forEach(teachingTerm => {
      let startTime = new Date(teachingTerm.startTime).getTime();
      let endTime = new Date(teachingTerm.endTime).getTime();
      
      let itemStartTimeD = new Date(teachingTerm.startTime);
      itemStartTimeD.setHours(item.startTime.getHours());
      itemStartTimeD.setMinutes(item.startTime.getMinutes());
      let itemEndTimeD = new Date(teachingTerm.endTime);
      itemEndTimeD.setHours(item.endTime.getHours());
      itemEndTimeD.setMinutes(item.endTime.getMinutes());
      let itemStartTime = itemStartTimeD.getTime();
      let itemEndTime = itemEndTimeD.getTime();

      let teacherFree = true;
      this.teacherRealizations.forEach(teacherRealization => {
        if(
            teacherRealization.teacher.id == item.teacherRealization.teacher.id
            &&
            teacherRealization.subjectRealization.id == teachingTerm.subjectRealization.id
          ){
            teacherFree = false;
        }
      })

      if(
        (
          teachingTerm.subjectRealization.yearOfStudy.id == item.teacherRealization.subjectRealization.yearOfStudy.id
          ||
          teachingTerm.classroom.id == item.classroom.id
          ||
          !teacherFree
        )
        &&
        (
          teachingTerm.day == this.classScheduleForm.get('day').value
          &&
          (
            (startTime > itemStartTime && startTime < itemEndTime)
            ||
            (endTime > itemStartTime && endTime < itemEndTime)
            ||
            (startTime < itemStartTime && endTime > itemEndTime)
            ||
            (startTime == itemStartTime && endTime == itemEndTime)
          )
        )
      )
      {
        alert("Termin je zauzet");
        valid = false;
        return false;
      }
    })
*/
    if(this.check()){
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
          this.teachingTermService.update(item.teachingTermId, teachingTerm).subscribe();
        }
        else{
          this.teachingTermService.add(teachingTerm).subscribe();
        }
      });
    });
    this.classScheduleForm.reset();
    this.classSchedule = [[], [], [], [], [], [], []];
    this.teacherRealizationsByYearOfStudy = [];
    this.snackBarService.openSnackBar("Successfully saved class schedule", "X");
  }

  delete(teachingTermId, item, day){
    if(teachingTermId){
      this.teachingTermService.delete(teachingTermId).subscribe();
    }
    day.splice(day.indexOf(item), 1);
    this.snackBarService.openSnackBar("Successfully deleted teaching term", "X");
  }

  drop(event: CdkDragDrop<string[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      this.selectedTeacherRealization = event.previousContainer.data[event.previousIndex]['teacherRealization'];
      this.classScheduleForm.patchValue({teacherRealization: this.selectedTeacherRealization});
      this.selectedDay = +event.container.id;
      this.classScheduleForm.patchValue({day: this.selectedDay});
      this.selectedStartTime = event.previousContainer.data[event.previousIndex]['startTime'];
      this.classScheduleForm.patchValue({startTime: (this.selectedStartTime.getHours()>=10 ? this.selectedStartTime.getHours() : '0'+this.selectedStartTime.getHours()) +':'+ (this.selectedStartTime.getMinutes()>=10 ? this.selectedStartTime.getMinutes() : '0'+this.selectedStartTime.getMinutes())});
      this.selectedClassroom = event.previousContainer.data[event.previousIndex]['classroom'];
      this.classScheduleForm.patchValue({classroom: this.selectedClassroom});
      if(this.check()){
        event.previousContainer.data[event.previousIndex]['day'] = this.selectedDay;
        transferArrayItem(event.previousContainer.data,
                          event.container.data,
                          event.previousIndex,
                          event.currentIndex);
      }
    }
  }

  openDialog(teachingTermId, item, day): void {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '250px',
      data: {title: "Delete teaching term", content: "Are you sure you want to delete this teaching term?"}
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.delete(teachingTermId, item, day);
      };
    });
  }
}