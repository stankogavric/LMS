import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { AdministrativeStaff } from '../administrative-staff';
import { AdministrativeStaffService } from '../administrative-staff.service';

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

  constructor(private ASService: AdministrativeStaffService) { }

  ngOnInit() {
  }

  onSave(){
    this.administrativeStaff = this.form.value;
    if(this.edit){
      this.ASService.update(this.id, this.administrativeStaff).subscribe();
    }else{
      this.ASService.add(this.administrativeStaff).subscribe();
    }
  }


}
