import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, Router } from '@angular/router';
import { AuthService } from './auth.service';
import decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {

  constructor(private auth: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const token = localStorage.getItem('token');
    if(token){
      const tokenPayload = decode(token);
      const expectedRoles = route.data.expectedRoles;
      if(new Date(tokenPayload.expiresIn) > new Date() && expectedRoles.includes(tokenPayload.role)){
        return true;
      }
    }
    this.router.navigate(['student']);
    return false;
  }
  
}
