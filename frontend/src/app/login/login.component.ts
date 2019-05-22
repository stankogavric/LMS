import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { NgForm } from '@angular/forms';
import { FormErrorService } from '../shared/formError.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authService : AuthService, public readonly formError: FormErrorService) { }

  ngOnInit() {
  }

  onLogin(form: NgForm){
    if (form.invalid) {
      return;
    }
    this.authService.login(form.value.username, form.value.password);
  }

}
