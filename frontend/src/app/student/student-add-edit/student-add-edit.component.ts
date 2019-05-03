import { Component, OnInit } from '@angular/core';
import { Student } from '../student.model';
import { FormGroup } from '@angular/forms';
import { StudentService } from '../student.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-student-add-edit',
  templateUrl: './student-add-edit.component.html',
  styleUrls: ['./student-add-edit.component.css']
})
export class StudentAddEditComponent implements OnInit {

  private edit = false;
  private id : string;
  public student: Student = new Student();
  public form : FormGroup;

  constructor(private studentService: StudentService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.form = new FormGroup({});
    if(this.route.snapshot.paramMap.get("id")){
      this.edit = true;
      this.id = this.route.snapshot.paramMap.get("id");
      this.studentService.getOne(this.id).subscribe((data: Student) => {
        this.student = data;
        this.form.patchValue(this.student);
      });
    }
  }

  onSave(){
    const std = this.form.value;
    delete std['accountData']['confirmPassword'];
    delete std['personalData']['profileImage'];
    this.student = std;
    if(this.edit){
      this.studentService.update(this.id, this.student).subscribe();
    }else{
      this.studentService.add(this.student, this.form.get('personalData').get('profileImage').value).subscribe();
    }
  }

}
