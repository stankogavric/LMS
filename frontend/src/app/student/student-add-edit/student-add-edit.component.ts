import { Component, OnInit } from '@angular/core';
import { Student } from '../student.model';
import { FormGroup } from '@angular/forms';
import { StudentService } from '../student.service';
import { ActivatedRoute } from '@angular/router';
import { FormErrorService } from 'src/app/shared/formError.service';

@Component({
  selector: 'app-student-add-edit',
  templateUrl: './student-add-edit.component.html',
  styleUrls: ['./student-add-edit.component.css']
})
export class StudentAddEditComponent implements OnInit {

  private edit = false;
  private username : string;
  public student: Student = new Student();
  public form: FormGroup;

  constructor(private studentService: StudentService, private route: ActivatedRoute, public formErrorService: FormErrorService) { }

  ngOnInit() {
    this.form = new FormGroup({});
    if(this.route.snapshot.paramMap.get("username")){
      this.edit = true;
      this.username = this.route.snapshot.paramMap.get("username");
      this.studentService.getOneByUsername(this.username).subscribe((data: Student) => {
        this.student = data;
        // this.form.get('personalData').get('profilePicturePath').patchValue(this.student.personalData.profilePicturePath);
        this.form.patchValue(this.student);
      });
    }
  }

  onSave() {
    if (this.form.invalid) {
      this.formErrorService.markFormGroupTouched(this.form);
    } else {
      const std = this.form.value;
      delete std['accountData']['confirmPassword'];
      delete std['personalData']['profileImage'];
      if (this.edit) {
        std.accountData.id = this.student.accountData.id;
        std.personalData.id = this.student.personalData.id;
        std.address.id = this.student.address.id;
        this.student = std;
        this.studentService.update(this.username, this.student, this.form.get('personalData').get('profileImage').value).subscribe();
      } else {
        this.student = std;
        this.studentService.add(this.student, this.form.get('personalData').get('profileImage').value).subscribe(_ => {
          this.form.reset();
        });
      }
    }
  }

}
