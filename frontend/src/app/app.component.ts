import { Component, OnInit, OnDestroy } from '@angular/core';
import { AuthService } from './auth/auth.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {

  opened: boolean = true;
  roles = [];
  private roleSubcription : Subscription;

  constructor(private authService: AuthService) {}

  ngOnInit() {
    this.roles = this.authService.getCurrentRoles();
    this.roleSubcription = this.authService.roleChanged.subscribe(
      (roles: [])=>{
        this.roles = roles;
      }
    );
  }

  ngOnDestroy(){
    this.roleSubcription.unsubscribe();
  }

}
