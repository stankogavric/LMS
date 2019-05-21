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

@NgModule({
  declarations: [
    AdminSidenavComponent,
    StudentSidenavComponent,
    AccountDataComponent,
    PersonalDataComponent,
    AddressComponent,
    SubjectsComponent,
    SubjectComponent
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
    AccountDataComponent,
    PersonalDataComponent,
    AddressComponent,
    SubjectComponent,
    SubjectsComponent
  ]
})
export class SharedModule { }
