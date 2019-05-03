import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Teacher } from '../teacher.model';
import { TeacherService } from '../teacher.service';
import { ActivatedRoute } from '@angular/router';

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

  constructor(private teacherService: TeacherService, private route: ActivatedRoute) { }

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
    const teacher = this.form.value;
    delete teacher['accountData']['confirmPassword'];
    delete teacher['personalData']['profileImage'];
    this.teacher = teacher;
    if(this.edit){
      this.teacherService.update(this.id, this.teacher).subscribe();
    }else{
      this.teacherService.add(this.teacher, this.form.get('personalData').get('profileImage').value).subscribe();
    }
  }

}
