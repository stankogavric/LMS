import { Component, OnInit } from '@angular/core';
import { Faculty } from 'src/app/faculty/faculty.model';
import { StudyProgram } from '../study-program.model';
import { Teacher } from 'src/app/teacher/teacher.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FacultyService } from 'src/app/faculty/faculty.service';
import { TeacherService } from 'src/app/teacher/teacher.service';
import { StudyProgramService } from '../study-program.service';

@Component({
  selector: 'app-study-program-add-edit',
  templateUrl: './study-program-add-edit.component.html',
  styleUrls: ['./study-program-add-edit.component.css']
})
export class StudyProgramAddEditComponent implements OnInit {

  public studyProgramAddEditForm : FormGroup;
  public studyProgram: StudyProgram = new StudyProgram();
  public faculties: Faculty[] = [];
  public headTeachers: Teacher[] = [];

  constructor(private fb: FormBuilder, private facultyService: FacultyService, private teacherService: TeacherService, private studyProgramService: StudyProgramService) { }

  ngOnInit() {
    this.studyProgramAddEditForm = this.fb.group({
      name: ['', {validators: [Validators.required]}],
      faculty: ['', {validators: [Validators.required]}],
      headTeacher: ['', {validators: [Validators.required]}],
      description: ['', {validators: [Validators.required]}]
    });

    this.getFaculties();
  }

  getFaculties(){
    this.facultyService.getAll().subscribe((data: Faculty[]) => {
      this.faculties = data;
    });
  }

  getTeachersByFaculty(facultyId: String){
    this.teacherService.getAllByFaculty(facultyId).subscribe(data => {
      this.headTeachers = data;
    })
  }

  save(){
    const s = this.studyProgramAddEditForm.value;
    this.studyProgram = s;
    this.studyProgramService.add(this.studyProgram).subscribe();
  }

}
