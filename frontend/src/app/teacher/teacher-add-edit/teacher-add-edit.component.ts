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
  private id : string;
  public form : FormGroup;
  public teacher: Teacher;

  constructor(private teacherService: TeacherService, private route: ActivatedRoute, public formErrorService: FormErrorService) { }

  ngOnInit() {
    
    this.form = new FormGroup({
      biography: new FormControl('')
    });
    if(this.route.snapshot.paramMap.get("id")){
      this.edit = true;
      this.id = this.route.snapshot.paramMap.get("id");
      this.teacherService.getOne(this.id).subscribe((data: Teacher) => {
        this.teacher = data;
        this.form.patchValue(this.teacher);
      });
    }
  }

  onSave(){
    if(this.form.invalid){
      this.formErrorService.markFormGroupTouched(this.form);
    }else{
      const tchr = this.form.value;
      delete tchr['accountData']['confirmPassword'];
      delete tchr['personalData']['profileImage'];

      tchr.accountData.id = this.teacher.accountData.id;
      tchr.personalData.id = this.teacher.personalData.id;
      tchr.address.id = this.teacher.address.id;
      this.teacher = tchr;

      if(this.edit){
        this.teacherService.update(this.id, this.teacher).subscribe();
      }else{
        this.teacherService.add(this.teacher, this.form.get('personalData').get('profileImage').value).subscribe();
      }
    }
  }

}
