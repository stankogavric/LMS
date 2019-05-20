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
      }
    );
  }

  onLogout(){
    this.authService.logout();
  }
  
  ngOnDestroy(){
    this.loggedInSubcription.unsubscribe();
  }

}
