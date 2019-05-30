import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { AdministrativeStaff } from '../administrative-staff';
import { AdministrativeStaffService } from '../administrative-staff.service';
import { ActivatedRoute } from '@angular/router';
import { FormErrorService } from 'src/app/shared/formError.service';

@Component({
  selector: 'app-administrative-staff-add-edit',
  templateUrl: './administrative-staff-add-edit.component.html',
  styleUrls: ['./administrative-staff-add-edit.component.css']
})
export class AdministrativeStaffAddEditComponent implements OnInit {

  private username: string;
  private edit = false;
  public administrativeStaff = new AdministrativeStaff();
  public form = new FormGroup({});

  constructor(private ASService: AdministrativeStaffService, private route: ActivatedRoute, public formErrorService: FormErrorService) { }

  ngOnInit() {
    this.form = new FormGroup({});
    if (this.route.snapshot.paramMap.get("username")) {
      this.edit = true;
      this.username = this.route.snapshot.paramMap.get("username");
      this.ASService.getOneByUsername(this.username).subscribe((data: AdministrativeStaff) => {
        this.administrativeStaff = data;
        this.form.patchValue(this.administrativeStaff);
      });
    }
  }

  onSave() {
    if (this.form.invalid) {
      this.formErrorService.markFormGroupTouched(this.form);
    } else {
      const admStf = this.form.value;
      delete admStf['accountData']['confirmPassword'];
      delete admStf['personalData']['profileImage'];
      if (this.edit) {
        admStf.accountData.id = this.administrativeStaff.accountData.id;
        admStf.personalData.id = this.administrativeStaff.personalData.id;
        admStf.address.id = this.administrativeStaff.address.id;
        this.administrativeStaff = admStf;
        this.ASService.update(this.username, this.administrativeStaff, this.form.get('personalData').get('profileImage').value).subscribe();
      } else {
        this.administrativeStaff = admStf;
        this.ASService.add(this.administrativeStaff, this.form.get('personalData').get('profileImage').value).subscribe(_ => {
          this.form.reset();
        });
      }
    }
  }


}
