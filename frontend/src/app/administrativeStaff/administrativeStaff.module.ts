import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared.module';

import { AdministrativeStaffComponent } from './administrative-staff/administrative-staff.component';
import { AdministrativeStaffAddEditComponent } from './administrative-staff-add-edit/administrative-staff-add-edit.component';
import { ClassScheduleAddEditComponent } from './class-schedule-add-edit/class-schedule-add-edit.component';

@NgModule({
  declarations: [
    AdministrativeStaffComponent,
    AdministrativeStaffAddEditComponent,
    ClassScheduleAddEditComponent
  ],
  imports: [
    SharedModule
  ]
})
export class AdministrativeStaffModule { }
