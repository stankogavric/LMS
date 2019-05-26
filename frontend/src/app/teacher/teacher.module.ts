import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared.module';

import { TeacherAddEditComponent } from './teacher-add-edit/teacher-add-edit.component';
import { TeachersComponent } from './teachers/teachers.component';
import { TeacherSubjectsComponent } from './teacher-subjects/teacher-subjects.component';

@NgModule({
  declarations: [
    TeacherAddEditComponent,
    TeachersComponent,
    TeacherSubjectsComponent
  ],
  imports: [
    SharedModule
  ]
})
export class TeacherModule { }
