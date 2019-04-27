import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Router } from "@angular/router";

@Injectable({ providedIn: "root" })
export class AuthService {
  constructor(private http: HttpClient, private router: Router) {}

  login(username: string, password: string){
    this.http.post<{token: string}>("url", {username: username, password: password}).subscribe(response =>{
      if(response.token){
        localStorage.setItem('token', response.token);
        this.router.navigate(['/']);
      }
    });
  }

  logout(){
    localStorage.removeItem('token');
  }

}
