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
import { HomeComponent } from './home/home.component';
import { UniversityComponent } from './university/university.component';
import { FacultyComponent } from './faculty/faculty.component';
import { StudentAddEditComponent } from './student/student-add-edit/student-add-edit.component';
import { TeacherAddEditComponent } from './teacher/teacher-add-edit/teacher-add-edit.component';
import { AdministrativeStaffAddEditComponent } from './administrativeStaff/administrative-staff-add-edit/administrative-staff-add-edit.component';
import { LowerCaseUrlSerializer } from './lower-case-url-serializer';
import { UrlSerializer } from '@angular/router';

@NgModule({
  declarations: [
    AppComponent,
    StudentComponent,
    ToolbarComponent,
    AddressComponent,
    PasswordValidatorDirective,
    PersonalDataComponent,
    AccountDataComponent,
    UniversityComponent,
    FacultyComponent,
    HomeComponent,
    StudentAddEditComponent,
    TeacherAddEditComponent,
    AdministrativeStaffAddEditComponent
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
  providers: [{
    provide: UrlSerializer,
    useClass: LowerCaseUrlSerializer
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
