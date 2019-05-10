import { Component, OnInit } from '@angular/core';
import { Administrator } from '../administrator.model';
import { AdministratorService } from '../administrator.service';
import { ActivatedRoute } from '@angular/router';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-administrator-add-edit',
  templateUrl: './administrator-add-edit.component.html',
  styleUrls: ['./administrator-add-edit.component.css']
})
export class AdministratorAddEditComponent implements OnInit {

  private id : string;
  private edit = false;
  public administrator = new Administrator();
  public form = new FormGroup({});

  constructor(private AdminService: AdministratorService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.form = new FormGroup({});
    if(this.route.snapshot.paramMap.get("id")){
      this.edit = true;
      this.id = this.route.snapshot.paramMap.get("id");
      this.AdminService.getOne(this.id).subscribe((data: Administrator) => {
        this.administrator = data;
        this.form.patchValue(this.administrator);
      });
    }
  }

  onSave(){
    const admin = this.form.value;
    delete admin['accountData']['confirmPassword'];
    this.administrator = admin;

    if(this.edit){
      this.AdminService.update(this.id, this.administrator).subscribe();
    }else{
      this.AdminService.add(this.administrator).subscribe();
    }
  }

}
