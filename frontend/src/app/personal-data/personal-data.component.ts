import { Component, OnInit, Input } from '@angular/core';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { mimeTypeValidator } from '../validators/mime-type-validator.directive';
import { FormErrorService } from '../shared/formError.service';

@Component({
  selector: 'app-personal-data',
  templateUrl: './personal-data.component.html',
  styleUrls: ['./personal-data.component.css']
})
export class PersonalDataComponent implements OnInit {

  @Input() public parrentForm: FormGroup;
  public personalDataForm : FormGroup;
  imagePreview: string;

  constructor(private fb: FormBuilder, public formError: FormErrorService) { }

  ngOnInit() {
    this.personalDataForm = this.fb.group({
      personalNumber: ['', {validators: [Validators.required, Validators.pattern('[0-9]{13}')]}],
      firstName: ['', {validators: [Validators.required, Validators.pattern('[a-zA-Z]{3,}')]}],
      lastName: ['', {validators: [Validators.required, Validators.pattern('[a-zA-Z]{3,}')]}],
      profilePicturePath: [''],
      profileImage: ['', {asyncValidators: [mimeTypeValidator]}],
    });
    this.parrentForm.addControl("personalData", this.personalDataForm);
  }

  onImagePicked(event: Event) {
    const file = (event.target as HTMLInputElement).files[0];
    if(file){
      this.personalDataForm.patchValue({ profileImage: file });
      this.personalDataForm.get("profileImage").updateValueAndValidity();
      const reader = new FileReader();
      reader.onload = () => {
        this.imagePreview = reader.result as string;;
      };
      reader.readAsDataURL(file);
    }
  }

}
