import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared.module';

import { TeacherAddEditComponent } from './teacher-add-edit/teacher-add-edit.component';
import { TeachersComponent } from './teachers/teachers.component';

@NgModule({
  declarations: [
    TeacherAddEditComponent,
    TeachersComponent
  ],
  imports: [
    SharedModule
  ]
})
export class TeacherModule { }
