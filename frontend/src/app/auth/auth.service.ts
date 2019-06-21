import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Router } from "@angular/router";
import decode from 'jwt-decode';
import { Subject } from 'rxjs';
import { SnackBarService } from '../shared/snack-bar.service';

@Injectable({ providedIn: "root" })
export class AuthService {

  roleChanged = new Subject<any[]>();
  loggedInStatusChanged = new Subject<boolean>();

  constructor(private http: HttpClient, private router: Router, private snackBarService: SnackBarService) {}

  login(username: string, password: string){
    this.http.post<{token: string}>("http://localhost:8080/login", {username: username, password: password}).subscribe(response =>{
      if(response.token){
        localStorage.setItem('token', response.token);
        this.roleChanged.next(this.getCurrentRoles());
        this.router.navigate(['/']);
        this.loggedInStatusChanged.next(true);
        this.snackBarService.openSnackBar("Welcome " + this.getCurrentUser() + "!", "X")
      }
    });
  }

  logout(){
    this.roleChanged.next([]);
    localStorage.removeItem('token');
    this.router.navigate(['/']);
    this.loggedInStatusChanged.next(false);
  }

  getCurrentRoles(){
    const token = localStorage.getItem('token');
    var roles = []
    if(token){
      decode(token).role.forEach(role => {
        roles.push(role.authority);
      });
    }
    return roles;
  }

  getCurrentUser(){
    const token = localStorage.getItem('token');
    if(token){
      return decode(token).sub;
    }
    return null;
  }

  isLoggedIn(){
    if(localStorage.getItem('token')){
      return true;
    }
    return false;
  }

}
