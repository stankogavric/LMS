import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RoleGuard } from './auth/role.guard';
import { StudentEditComponent } from './student/student-edit/student-edit.component';
import { HomeComponent } from './home/home.component';
import { UniversityComponent } from './university/university.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'student', component: StudentEditComponent },
  { path: 'student/:id', component: StudentEditComponent },
  { path: 'admin', component: StudentEditComponent, canActivate: [RoleGuard], 
          data: { expectedRoles: ['admin', 'professor']}},
  { path: 'university', component: UniversityComponent},
  { path: '**', redirectTo: '', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
