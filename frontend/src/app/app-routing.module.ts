import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RoleGuard } from './auth/role.guard';
import { StudentAddEditComponent } from './student/student-add-edit/student-add-edit.component';
import { TeacherAddEditComponent } from './teacher/teacher-add-edit/teacher-add-edit.component';
import { AdministrativeStaffAddEditComponent } from './administrativeStaff/administrative-staff-add-edit/administrative-staff-add-edit.component';
import { HomeComponent } from './home/home.component';

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
  {path: '**', redirectTo: '', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
