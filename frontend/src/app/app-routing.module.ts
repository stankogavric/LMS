import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RoleGuard } from './auth/role.guard';
import { StudentAddEditComponent } from './student/student-add-edit/student-add-edit.component';
import { TeacherAddEditComponent } from './teacher/teacher-add-edit/teacher-add-edit.component';
import { AdministrativeStaffAddEditComponent } from './administrativeStaff/administrative-staff-add-edit/administrative-staff-add-edit.component';
import { HomeComponent } from './home/home.component';
import { UniversityComponent } from './university/university.component';
import { FacultyComponent } from './faculty/faculty.component';
import { StudyProgramComponent } from './study-program/study-program.component';
import { SubjectComponent } from './subject/subject.component';
import { StudentsComponent } from './student/students/students.component';
import { TeachersComponent } from './teacher/teachers/teachers.component';
import { StudentComponent } from './student/student.component';
import { CurrentSubjectsComponent} from './student/current-subjects/current-subjects.component';
import { PastSubjectsComponent} from './student/past-subjects/past-subjects.component';
import { StudyProgramsComponent } from './study-program/study-programs/study-programs.component';
import { LoginComponent } from './login/login.component';
import { AdministratorsComponent } from './administrator/administrators/administrators.component';
import { AdministrativeStaffComponent } from './administrativeStaff/administrative-staff/administrative-staff.component';
import { AdministratorAddEditComponent } from './administrator/administrator-add-edit/administrator-add-edit.component';
import { SubjectsComponent } from './subject/subjects/subjects.component';
import { ExamAddEditComponent } from './exam/exam-add-edit/exam-add-edit.component';
import { AddEditTopicsComponent } from './topic/add-edit-topics/add-edit-topics.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },

  { path: 'students', component: StudentsComponent},
  { path: 'student/:id', component: StudentComponent},
  { path: 'edit/student/:id', component: StudentAddEditComponent },
  { path: 'register/student', component: StudentAddEditComponent, 
        canActivate: [RoleGuard], data: { expectedRoles: ['ROLE_ADMINISTRATOR', 'ROLE_ADMINISTRATIVE_STAFF']}},
        
  { path: 'teachers', component: TeachersComponent},
  { path: 'edit/teacher/:id', component: TeacherAddEditComponent },
  { path: 'register/teacher', component: TeacherAddEditComponent, 
        canActivate: [RoleGuard], data: { expectedRoles: ['ROLE_ADMINISTRATOR']}},


  { path: 'add/exam', component: ExamAddEditComponent },

  { path: 'add/topics', component: AddEditTopicsComponent },
        
  { path: 'administrators', component: AdministratorsComponent},
  { path: 'edit/administrator/:id', component: AdministratorAddEditComponent },
  { path: 'register/administrator', component: AdministratorAddEditComponent, 
        canActivate: [RoleGuard], data: { expectedRoles: ['ROLE_ADMINISTRATOR']}},

  { path: 'administrativeStaff', component: AdministrativeStaffComponent},
  { path: 'edit/administrativestaff/:id', component: AdministrativeStaffAddEditComponent },
  { path: 'register/administrativestaff', component: AdministrativeStaffAddEditComponent, canActivate: [RoleGuard], 
      data: { expectedRoles: ['ROLE_ADMINISTRATOR']}},

  { path: 'university', component: UniversityComponent},
  { path: 'faculty/:id', component: FacultyComponent},
  
  { path: 'studyPrograms', component: StudyProgramsComponent},
  { path: 'studyProgram/:id', component: StudyProgramComponent},

  { path: 'subject/:id', component: SubjectComponent},
  { path: 'currentSubjects', component: CurrentSubjectsComponent},
  { path: 'pastSubjects', component: PastSubjectsComponent},
  { path: 'studyPrograms', component: StudyProgramsComponent},
  { path: 'subjects', component: SubjectsComponent},
  //{ path: 'students', component: StudentsComponent, outlet: "adminSidenav"},
  { path: '**', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
