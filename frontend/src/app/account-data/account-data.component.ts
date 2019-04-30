import { Component, OnInit, Input } from '@angular/core';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { MustMatch } from '../validators/must-match-validator.directive';
@Component({
  selector: 'app-account-data',
  templateUrl: './account-data.component.html',
  styleUrls: ['./account-data.component.css']
})
export class AccountDataComponent implements OnInit {

  @Input() public parrentForm: FormGroup;
  public accountDataForm : FormGroup;

  constructor(private fb: FormBuilder) { }

  ngOnInit() {
    this.accountDataForm = this.fb.group({
      username: ['', {validators: [Validators.required, Validators.minLength(3)]}],
      email: ['', {validators: [Validators.required, Validators.email]}],
      password: ['', {validators: [Validators.required, Validators.minLength(8)]}],
      confirmPassword: [''],
    }, {
      validator: MustMatch('password', 'confirmPassword')
    });

    this.parrentForm.addControl("accountData", this.accountDataForm);
  }

}
