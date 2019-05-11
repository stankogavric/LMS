import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared.module';

import { AdministratorsComponent } from './administrators/administrators.component';
import { AdministratorAddEditComponent } from './administrator-add-edit/administrator-add-edit.component';
import { AdministratorComponent } from './administrator.component';

@NgModule({
  declarations: [
    AdministratorsComponent,
    AdministratorAddEditComponent,
    AdministratorComponent,
  ],
  imports: [
    SharedModule
  ]
})
export class AdministratorModule { }
