import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Router } from "@angular/router";
import decode from 'jwt-decode';
import { Subject } from 'rxjs';

@Injectable({ providedIn: "root" })
export class AuthService {

  roleChanged = new Subject<any[]>();

  constructor(private http: HttpClient, private router: Router) {}

  login(username: string, password: string){
    this.http.post<{token: string}>("http://localhost:8080/login", {username: username, password: password}).subscribe(response =>{
      if(response.token){
        localStorage.setItem('token', response.token);
        this.roleChanged.next(this.getCurrentRoles());
        this.router.navigate(['/']);
      }
    });
  }

  logout(){
    this.roleChanged.next([]);
    localStorage.removeItem('token');
    this.router.navigate(['/']);
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

}
