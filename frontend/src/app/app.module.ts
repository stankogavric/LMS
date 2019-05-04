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
  MatProgressSpinnerModule, MatPaginatorModule, MatSidenavModule, MatTableModule
} from "@angular/material";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AddressComponent } from './address/address.component';
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
import { PhoneComponent } from './phone/phone.component';
import { EmailComponent } from './email/email.component';
import { StudyProgramComponent } from './study-program/study-program.component';
import { YearOfStudyComponent } from './year-of-study/year-of-study.component';
import { SubjectComponent } from './subject/subject.component';
import { TopicComponent } from './topic/topic.component';
import { AdminSidenavComponent } from './admin-sidenav/admin-sidenav.component';
import { StudentsComponent } from './student/students/students.component';
import { TeachersComponent } from './teacher/teachers/teachers.component';
import { StudentSidenavComponent } from './student/student-sidenav/student-sidenav.component';
import { CurrentSubjectsComponent } from './student/current-subjects/current-subjects.component';
import { PastSubjectsComponent } from './student/past-subjects/past-subjects.component';
import { StudyProgramsComponent } from './study-program/study-programs/study-programs.component';

@NgModule({
  declarations: [
    AppComponent,
    StudentComponent,
    ToolbarComponent,
    AddressComponent,
    PersonalDataComponent,
    AccountDataComponent,
    UniversityComponent,
    FacultyComponent,
    HomeComponent,
    StudentAddEditComponent,
    TeacherAddEditComponent,
    AdministrativeStaffAddEditComponent,
    PhoneComponent,
    EmailComponent,
    StudyProgramComponent,
    YearOfStudyComponent,
    SubjectComponent,
    TopicComponent,
    AdminSidenavComponent,
    StudentsComponent,
    TeachersComponent,
    StudentSidenavComponent,
    CurrentSubjectsComponent,
    PastSubjectsComponent,
    StudyProgramsComponent
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
    MatSidenavModule,
    MatTableModule,
    MatGridListModule
  ],
  providers: [{
    provide: UrlSerializer,
    useClass: LowerCaseUrlSerializer
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
