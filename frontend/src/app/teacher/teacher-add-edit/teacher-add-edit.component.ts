import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Teacher } from '../teacher.model';
import { TeacherService } from '../teacher.service';
import { ActivatedRoute } from '@angular/router';
import { FormErrorService } from 'src/app/shared/formError.service';

@Component({
  selector: 'app-teacher-add-edit',
  templateUrl: './teacher-add-edit.component.html',
  styleUrls: ['./teacher-add-edit.component.css']
})
export class TeacherAddEditComponent implements OnInit {
  private edit = false;
  private username: string;
  public form: FormGroup;
  public teacher: Teacher;

  constructor(private teacherService: TeacherService, private route: ActivatedRoute, public formErrorService: FormErrorService) { }

  ngOnInit() {

    this.form = new FormGroup({
      biography: new FormControl('')
    });
    if (this.route.snapshot.paramMap.get("username")) {
      this.edit = true;
      this.username = this.route.snapshot.paramMap.get("username");
      this.teacherService.getOneByUsername(this.username).subscribe((data: Teacher) => {
        this.teacher = data;
        this.form.patchValue(this.teacher);
      });
    }
  }

  onSave() {
    if (this.form.invalid) {
      this.formErrorService.markFormGroupTouched(this.form);
    } else {
      const tchr = this.form.value;
      delete tchr['accountData']['confirmPassword'];
      delete tchr['personalData']['profileImage'];
      if (this.edit) {
        tchr.accountData.id = this.teacher.accountData.id;
        tchr.personalData.id = this.teacher.personalData.id;
        tchr.address.id = this.teacher.address.id;
        this.teacher = tchr;
        this.teacherService.update(this.username, this.teacher, this.form.get('personalData').get('profileImage').value).subscribe();
      } else {
        this.teacher = tchr;
        this.teacherService.add(this.teacher, this.form.get('personalData').get('profileImage').value).subscribe(_ => {
          this.form.reset();
        });
      }
    }
  }

}
