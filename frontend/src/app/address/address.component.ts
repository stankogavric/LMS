import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Address } from './address.model';

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.css']
})
export class AddressComponent implements OnInit {

  @Input() public parrentForm: FormGroup;
  public addressForm : FormGroup;

  constructor(private fb: FormBuilder) { }

  ngOnInit() {
    this.addressForm = this.fb.group({
      city: this.fb.group({
        name: ['', {validators: [Validators.required]}],
        country: this.fb.group({
          name: ['', {validators: [Validators.required]}],
        }),
      }),
      street: ['', {validators: [Validators.required]}],
      number: ['', {validators: [Validators.required]}],
    });

    // if(this.address){
    //   this.addressForm.patchValue({
    //     number: this.address.number,
    //     street: this.address.street,
    //   });
    //   if(this.address.city){
    //     this.addressForm.patchValue({
    //       country: this.address.city.country,
    //       city: this.address.city.name,
    //     });
    //   }
    // }

    this.parrentForm.addControl("address", this.addressForm);
  }

}
