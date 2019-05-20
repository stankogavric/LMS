import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, Router } from '@angular/router';
import { AuthService } from './auth.service';
import decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {

  constructor(private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const token = localStorage.getItem('token');
    var authorized = false;
    if (token) {
      const tokenPayload = decode(token);
      const expectedRoles = route.data.expectedRoles;
      if (new Date(tokenPayload.created + tokenPayload.exp) > new Date()) {
        tokenPayload.role.forEach(auth => {
          if (expectedRoles.includes(auth.authority)) {
            authorized = true;
          }
        });
      }
    }
    if(authorized){
      return true;
    }else{
      if(token){
        this.router.navigate(['/']);
      }else{
        this.router.navigate(['login']);
      }
      return false;
    }
  }

}
