import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RoleGuard } from './auth/role.guard';
import { StudentEditComponent } from './student/student-edit/student-edit.component';

const routes: Routes = [
  { path: '', component: StudentEditComponent },
  { path: 'student', component: StudentEditComponent },
  { path: 'student/:id', component: StudentEditComponent },
  { path: 'admin', component: StudentEditComponent, canActivate: [RoleGuard], 
          data: { expectedRoles: ['admin', 'professor']}},
  {path: '**', redirectTo: '', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
