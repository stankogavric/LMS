import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { AdministrativeStaff } from '../administrative-staff';
import { AdministrativeStaffService } from '../administrative-staff.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-administrative-staff-add-edit',
  templateUrl: './administrative-staff-add-edit.component.html',
  styleUrls: ['./administrative-staff-add-edit.component.css']
})
export class AdministrativeStaffAddEditComponent implements OnInit {

  private id : string;
  private edit = false;
  public administrativeStaff = new AdministrativeStaff();
  public form = new FormGroup({});

  constructor(private ASService: AdministrativeStaffService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.form = new FormGroup({});
    if(this.route.snapshot.paramMap.get("id")){
      this.edit = true;
      this.id = this.route.snapshot.paramMap.get("id");
      this.ASService.getOne(this.id).subscribe((data: AdministrativeStaff) => {
        this.administrativeStaff = data;
        this.form.patchValue(this.administrativeStaff);
      });
    }
  }

  onSave(){
    const admStf = this.form.value;
    delete admStf['accountData']['confirmPassword'];
    delete admStf['personalData']['profileImage'];
    this.administrativeStaff = admStf;

    if(this.edit){
      this.ASService.update(this.id, this.administrativeStaff).subscribe();
    }else{
      this.ASService.add(this.administrativeStaff, this.form.get('personalData').get('profileImage').value).subscribe();
    }
  }


}
