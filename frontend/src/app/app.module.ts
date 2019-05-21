import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { ToolbarComponent } from './toolbar/toolbar.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { UniversityComponent } from './university/university.component';
import { FacultyComponent } from './faculty/faculty.component';
import { LowerCaseUrlSerializer } from './lower-case-url-serializer';
import { UrlSerializer } from '@angular/router';
import { PhoneComponent  } from './phone/phone.component';
import { EmailComponent } from './email/email.component';
import { StudyProgramComponent } from './study-program/study-program.component';
import { YearOfStudyComponent } from './year-of-study/year-of-study.component';
import { TopicComponent } from './topic/topic.component';
import { StudyProgramsComponent } from './study-program/study-programs/study-programs.component';
import { LoginComponent } from './login/login.component';
import { AuthInterceptor } from './auth/auth-interceptor';
import { AdministratorModule } from './administrator/administrator.module';
import { SharedModule } from './shared/shared.module';
import { MaterialModule } from './shared/material.module';
import { StudentModule } from './student/student.module';
import { AdministrativeStaffModule } from './administrativeStaff/administrativeStaff.module';
import { TeacherModule } from './teacher/teacher.module';
import { ExamComponent } from './exam/exam.component';
import { ExamAddEditComponent } from './exam/exam-add-edit/exam-add-edit.component';

@NgModule({
  declarations: [
    AppComponent,
    ToolbarComponent,
    UniversityComponent,
    FacultyComponent,
    HomeComponent,
    PhoneComponent,
    EmailComponent,
    StudyProgramComponent,
    YearOfStudyComponent,
    TopicComponent,
    StudyProgramsComponent,
    LoginComponent,
    ExamComponent,
    ExamAddEditComponent
  ],
  imports: [
    BrowserModule,
    AdministratorModule,
    TeacherModule,
    StudentModule,
    AdministrativeStaffModule,
    SharedModule,
    MaterialModule,
    AppRoutingModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
      {provide: UrlSerializer, useClass: LowerCaseUrlSerializer}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
