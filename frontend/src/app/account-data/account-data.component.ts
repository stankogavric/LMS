import { Component, OnInit, Input, Injectable } from '@angular/core';
import { Validators, FormBuilder, FormGroup, FormControl } from '@angular/forms';
import { MustMatch } from '../validators/must-match-validator.directive';
import { FormErrorService } from '../shared/formError.service';

@Component({
  selector: 'app-account-data',
  templateUrl: './account-data.component.html',
  styleUrls: ['./account-data.component.css']
})
export class AccountDataComponent implements OnInit {

  @Input() public parrentForm: FormGroup;
  public accountDataForm : FormGroup;

  constructor(private fb: FormBuilder,  public formError: FormErrorService) { }

  ngOnInit() {
    this.accountDataForm = this.fb.group({
      username: ['', {validators: [Validators.required, Validators.minLength(3)]}],
      email: ['', {validators: [Validators.required, Validators.email]}],
      password: ['', {validators: [Validators.required, Validators.minLength(8)]}],
      confirmPassword: [''],
    }, {
      validators: [MustMatch('password', 'confirmPassword')]
    });

    this.parrentForm.addControl("accountData", this.accountDataForm);
  }

}
