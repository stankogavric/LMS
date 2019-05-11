import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared.module';

import { AdministrativeStaffComponent } from './administrative-staff/administrative-staff.component';
import { AdministrativeStaffAddEditComponent } from './administrative-staff-add-edit/administrative-staff-add-edit.component';

@NgModule({
  declarations: [
    AdministrativeStaffComponent,
    AdministrativeStaffAddEditComponent
  ],
  imports: [
    SharedModule
  ]
})
export class AdministrativeStaffModule { }
