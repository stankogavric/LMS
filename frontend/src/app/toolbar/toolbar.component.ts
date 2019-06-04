import { Component, OnInit } from '@angular/core';
import { FacultyService } from '../faculty/faculty.service';
import { Faculty } from '../faculty/faculty.model';
import { AuthService } from '../auth/auth.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent implements OnInit {

  title = "sQuadunum";
  faculties = [];
  isLoggedIn = false;
  public loggedUserUsername: String;
  private loggedUserRoles: String[];
  public loggedUserType: String;

  private loggedInSubcription : Subscription;

  constructor(private facultyService: FacultyService, private authService: AuthService) { }

  ngOnInit() {
    this.isLoggedIn = this.authService.isLoggedIn();
    this.facultyService.getAll().subscribe((data: Faculty[]) => {
      this.faculties = data;
    });
    this.loggedInSubcription = this.authService.loggedInStatusChanged.subscribe(
      (status: boolean)=>{
        this.isLoggedIn = status;
        this.setUserForEditProfile(); // on log in
      }
    );
    this.setUserForEditProfile(); // when refresh page while the user is logged in
  }

  setUserForEditProfile(){
    this.loggedUserUsername = this.authService.getCurrentUser();
    this.loggedUserRoles = this.authService.getCurrentRoles();
    this.loggedUserRoles.forEach(role => {
      if(role == "ROLE_ADMINISTRATOR"){
        this.loggedUserType = "administrator";
      }
      else if(role == "ROLE_ADMINISTRATIVE_STAFF"){
        this.loggedUserType = "administrativestaff";
      }
      else if(role == "ROLE_TEACHER"){
        this.loggedUserType = "teacher";
      }
      else if(role == "ROLE_STUDENT"){
        this.loggedUserType = "student";
      }
    });
  }

  onLogout(){
    this.authService.logout();
  }
  
  ngOnDestroy(){
    this.loggedInSubcription.unsubscribe();
  }

}
