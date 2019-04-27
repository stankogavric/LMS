import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { AdministrativeStaff } from '../administrative-staff';
import { AdministrativeStaffService } from '../administrative-staff.service';

@Component({
  selector: 'app-administrative-staff-edit',
  templateUrl: './administrative-staff-edit.component.html',
  styleUrls: ['./administrative-staff-edit.component.css']
})
export class AdministrativeStaffEditComponent implements OnInit {

  private edit = false;
  public administrativeStaff = new AdministrativeStaff();
  public form = new FormGroup({});

  constructor(private ASService: AdministrativeStaffService) { }

  ngOnInit() {
  }

  onSave(){
    this.administrativeStaff = this.form.value;
    if(this.edit){
      // this.studentService.update(this.student.id, this.student).subscribe();
    }else{
      this.ASService.add(this.administrativeStaff).subscribe();
    }
  }


}
