import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared.module';

import { StudentComponent } from './student.component';
import { StudentAddEditComponent } from './student-add-edit/student-add-edit.component';
import { StudentsComponent } from './students/students.component';
import { CurrentSubjectsComponent } from './current-subjects/current-subjects.component';
import { PastSubjectsComponent } from './past-subjects/past-subjects.component';
import { StudentsListComponent } from './students-list/students-list.component';
import { SearchStudentsComponent } from './search-students/search-students.component';
import { StudentDetailComponent } from './student-detail/student-detail.component';
import { StudentExamsComponent } from './student-exams/student-exams.component';
import { StudentYearComponent } from './student-year/student-year.component';
import { StudentDissertationComponent } from './student-dissertation/student-dissertation.component';


@NgModule({
  declarations: [
    StudentComponent,
    StudentAddEditComponent,
    StudentsComponent,
    CurrentSubjectsComponent,
    PastSubjectsComponent,
    StudentsListComponent,
    SearchStudentsComponent,
    StudentDetailComponent,
    StudentExamsComponent,
    StudentYearComponent,
    StudentDissertationComponent
  ],
  imports: [
    SharedModule
  ]
})
export class StudentModule { }