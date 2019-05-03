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

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'register/student', component: StudentAddEditComponent },
  { path: 'edit/student/:id', component: StudentAddEditComponent },
  { path: 'register/teacher', component: TeacherAddEditComponent },
  { path: 'edit/teacher/:id', component: TeacherAddEditComponent },
  { path: 'register/administrativestaff', component: AdministrativeStaffAddEditComponent },
  { path: 'edit/administrativestaff/:id', component: AdministrativeStaffAddEditComponent },
  { path: 'admin', component: StudentAddEditComponent, canActivate: [RoleGuard], 
          data: { expectedRoles: ['admin', 'professor']}},
  { path: 'university', component: UniversityComponent},
  { path: 'faculty/:id', component: FacultyComponent},
  { path: 'studyProgram/:id', component: StudyProgramComponent},
  { path: 'subject/:id', component: SubjectComponent},
  { path: 'teachers', component: TeachersComponent},
  { path: 'students', component: StudentsComponent},
  //{ path: 'students', component: StudentsComponent, outlet: "adminSidenav"},
  { path: '**', redirectTo: '', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
