import { Component, OnInit } from '@angular/core';
import { Administrator } from '../administrator.model';
import { AdministratorService } from '../administrator.service';
import { ActivatedRoute } from '@angular/router';
import { FormGroup } from '@angular/forms';
import { FormErrorService } from 'src/app/shared/formError.service';

@Component({
  selector: 'app-administrator-add-edit',
  templateUrl: './administrator-add-edit.component.html',
  styleUrls: ['./administrator-add-edit.component.css']
})
export class AdministratorAddEditComponent implements OnInit {

  private username: string;
  private edit = false;
  public administrator = new Administrator();
  public form = new FormGroup({});

  constructor(private AdminService: AdministratorService, private route: ActivatedRoute, public formErrorService: FormErrorService) { }

  ngOnInit() {
    this.form = new FormGroup({});
    if (this.route.snapshot.paramMap.get("username")) {
      this.edit = true;
      this.username = this.route.snapshot.paramMap.get("username");
      this.AdminService.getOneByUsername(this.username).subscribe((data: Administrator) => {
        this.administrator = data;
        this.form.patchValue(this.administrator);
      });
    }
  }

  onSave() {
    if (this.form.invalid) {
      this.formErrorService.markFormGroupTouched(this.form);
    } else {
      const admin = this.form.value;
      delete admin['accountData']['confirmPassword'];
      if (this.edit) {
        admin.accountData.id = this.administrator.accountData.id;
        this.administrator = admin;
        this.AdminService.update(this.username, this.administrator).subscribe();
      } else {
        this.administrator = admin;
        this.AdminService.add(this.administrator).subscribe(_ => {
          this.form.reset();
        });
      }
    }
  }

}
