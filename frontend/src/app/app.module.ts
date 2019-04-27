import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FlexLayoutModule} from "@angular/flex-layout";

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialModule} from './material';

import { StudentComponent } from './student/student.component';
import { ToolbarComponent } from './toolbar/toolbar.component';
import {MatGridListModule} from '@angular/material/grid-list';
import { MatInputModule, MatCardModule, MatButtonModule, MatToolbarModule, MatExpansionModule, 
  MatProgressSpinnerModule, MatPaginatorModule
} from "@angular/material";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AddressComponent } from './address/address.component';
import { PasswordValidatorDirective } from './account-data/password-validator.directive';
import { PersonalDataComponent } from './personal-data/personal-data.component';
import { AccountDataComponent } from './account-data/account-data.component';
import { StudentEditComponent } from './student/student-edit/student-edit.component';
import { TeacherEditComponent } from './teacher/teacher-edit/teacher-edit.component';
import { AdministrativeStaffEditComponent } from './administrativeStaff/administrative-staff-edit/administrative-staff-edit.component';
import { HomeComponent } from './home/home.component';
import { UniversityComponent } from './university/university.component';
import { FacultyComponent } from './faculty/faculty.component';

@NgModule({
  declarations: [
    AppComponent,
    StudentComponent,
    ToolbarComponent,
    AddressComponent,
    PasswordValidatorDirective,
    PersonalDataComponent,
    AccountDataComponent,
    StudentEditComponent,
    TeacherEditComponent,
    AdministrativeStaffEditComponent,
    HomeComponent,
    UniversityComponent,
    FacultyComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    MaterialModule,
    MatInputModule,
    MatCardModule,
    MatButtonModule,
    MatToolbarModule,
    MatExpansionModule,
    MatProgressSpinnerModule,
    MatPaginatorModule,
    MatGridListModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
