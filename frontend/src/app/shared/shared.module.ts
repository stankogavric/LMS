import { NgModule } from '@angular/core';

import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MaterialModule } from './material.module';
import { AppRoutingModule } from '../app-routing.module';

import {FlexLayoutModule} from "@angular/flex-layout";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import { AccountDataComponent } from '../account-data/account-data.component';
import { AdminSidenavComponent } from '../administrator/admin-sidenav/admin-sidenav.component';
import { PersonalDataComponent } from '../personal-data/personal-data.component';
import { AddressComponent } from '../address/address.component';
import { StudentSidenavComponent } from '../student/student-sidenav/student-sidenav.component';
import { SubjectsComponent } from '../subject/subjects/subjects.component';
import { SubjectComponent } from '../subject/subject.component';
import { TeacherSidenavComponent } from '../teacher/teacher-sidenav/teacher-sidenav.component';
import { RegisteredExamsComponent } from '../exam/registered-exams/registered-exams.component';
import { AdministrativeStaffSidenavComponent } from '../administrativeStaff/administrative-staff-sidenav/administrative-staff-sidenav.component';
import { ConfirmationDialogComponent } from './confirmation-dialog/confirmation-dialog.component';

@NgModule({
  declarations: [
    AdminSidenavComponent,
    StudentSidenavComponent,
    TeacherSidenavComponent,
    AdministrativeStaffSidenavComponent,
    AccountDataComponent,
    PersonalDataComponent,
    AddressComponent,
    SubjectsComponent,
    SubjectComponent,
    RegisteredExamsComponent,
    ConfirmationDialogComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FlexLayoutModule
  ],
  exports: [
    CommonModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    AdminSidenavComponent,
    StudentSidenavComponent,
    TeacherSidenavComponent,
    AdministrativeStaffSidenavComponent,
    AccountDataComponent,
    PersonalDataComponent,
    AddressComponent,
    SubjectComponent,
    SubjectsComponent,
    RegisteredExamsComponent,
    ConfirmationDialogComponent
  ],
  entryComponents: [ ConfirmationDialogComponent ]
})
export class SharedModule { }
