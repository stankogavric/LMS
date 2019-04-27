import { Component, OnInit } from '@angular/core';
import { Student } from '../student.model';
import { FormGroup } from '@angular/forms';
import { StudentService } from '../student.service';
import { PersonalData } from 'src/app/personal-data/personal-data.model';
import { Country } from 'src/app/address/country.model';
import { City } from 'src/app/address/city.model';
import { Address } from 'src/app/address/address.model';
import { AccountData } from 'src/app/account-data/account-data.model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-student-edit',
  templateUrl: './student-edit.component.html',
  styleUrls: ['./student-edit.component.css']
})
export class StudentEditComponent implements OnInit {

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
    this.student = this.form.value;
    if(this.edit){
      this.studentService.update(this.id, this.student).subscribe();
    }else{
      this.studentService.add(this.student).subscribe();
    }
  }

}
