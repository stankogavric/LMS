import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared.module';

import { StudentComponent } from './student.component';
import { StudentAddEditComponent } from './student-add-edit/student-add-edit.component';
import { StudentsComponent } from './students/students.component';
import { CurrentSubjectsComponent } from './current-subjects/current-subjects.component';
import { PastSubjectsComponent } from './past-subjects/past-subjects.component';

@NgModule({
  declarations: [
    StudentComponent,
    StudentAddEditComponent,
    StudentsComponent,
    CurrentSubjectsComponent,
    PastSubjectsComponent,
  ],
  imports: [
    SharedModule
  ]
})
export class StudentModule { }