import { Component, OnInit, Input } from '@angular/core';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { PersonalData } from './personal-data.model';

@Component({
  selector: 'app-personal-data',
  templateUrl: './personal-data.component.html',
  styleUrls: ['./personal-data.component.css']
})
export class PersonalDataComponent implements OnInit {

  @Input() public parrentForm: FormGroup;
  public personalDataForm : FormGroup;

  constructor(private fb: FormBuilder) { }

  ngOnInit() {
    this.personalDataForm = this.fb.group({
      personalNumber: ['', {validators: [Validators.required, Validators.pattern('[0-9]{13}')]}],
      name: ['', {validators: [Validators.required, Validators.pattern('[a-zA-Z]{3,}')]}],
      lastName: ['', {validators: [Validators.required, Validators.pattern('[a-zA-Z]{3,}')]}],
      profilePicturePath: ['', {validators: [Validators.required, Validators.pattern('[a-zA-Z]{3,}')]}],
    });
    this.parrentForm.addControl("personalData", this.personalDataForm);
  }
}
