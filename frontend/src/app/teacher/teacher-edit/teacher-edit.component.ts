import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Teacher } from '../teacher.model';
import { TeacherService } from '../teacher.service';

@Component({
  selector: 'app-teacher-edit',
  templateUrl: './teacher-edit.component.html',
  styleUrls: ['./teacher-edit.component.css']
})
export class TeacherEditComponent implements OnInit {
  private edit = false;

  public form : FormGroup;
  public teacher: Teacher;

  constructor(private teacherService: TeacherService) { }

  ngOnInit() {
    
    this.form = new FormGroup({
      biography: new FormControl('')
    });

  }

  onSave(){
    this.teacher = this.form.value;
    if(this.edit){
      // this.studentService.update(this.student.id, this.student).subscribe();
    }else{
      this.teacherService.add(this.teacher).subscribe();
    }
  }

}
