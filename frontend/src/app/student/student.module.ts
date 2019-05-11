import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared.module';

import { StudentComponent } from './student.component';
import { StudentAddEditComponent } from './student-add-edit/student-add-edit.component';
import { StudentsComponent } from './students/students.component';

@NgModule({
    declarations: [
        StudentComponent,
        StudentAddEditComponent,
        StudentsComponent
    ],
    imports: [
      SharedModule
    ]
  })
  export class StudentModule { }