import { Component, OnInit } from '@angular/core';
import { Faculty } from 'src/app/faculty/faculty.model';
import { StudyProgram } from '../study-program.model';
import { Teacher } from 'src/app/teacher/teacher.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FacultyService } from 'src/app/faculty/faculty.service';
import { TeacherService } from 'src/app/teacher/teacher.service';
import { StudyProgramService } from '../study-program.service';
import { ActivatedRoute } from '@angular/router';
import { SnackBarService } from 'src/app/shared/snack-bar.service';

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
  private edit = false;
  private id: string;

  constructor(private fb: FormBuilder, private route: ActivatedRoute, private facultyService: FacultyService, private teacherService: TeacherService, private studyProgramService: StudyProgramService, private snackBarService: SnackBarService) { }

  ngOnInit() {
    this.studyProgramAddEditForm = this.fb.group({
      name: ['', {validators: [Validators.required]}],
      faculty: ['', {validators: [Validators.required]}],
      headTeacher: ['', {validators: [Validators.required]}],
      description: ['', {validators: [Validators.required]}]
    });

    if(this.route.snapshot.paramMap.get("id")){
      this.edit = true;
      this.id = this.route.snapshot.paramMap.get("id");
      this.studyProgramService.getOne(this.id).subscribe((data: StudyProgram) => {
        this.studyProgram = data;
        this.studyProgramAddEditForm.patchValue(this.studyProgram);
        this.getFaculties();
      })
    }
    else {
      this.getFaculties();
    }
  }

  getFaculties(){
    this.facultyService.getAll().subscribe((data: Faculty[]) => {
      this.faculties = data;
      if(this.edit){
        this.faculties.forEach(faculty => {
          if(JSON.stringify(faculty) == JSON.stringify(this.studyProgram.faculty)){
            this.studyProgramAddEditForm.patchValue({faculty : faculty});
            this.getTeachersByFaculty(faculty.id);
          }
        })
      }
    });
  }

  getTeachersByFaculty(facultyId: number){
    this.teacherService.getAllByFaculty(facultyId).subscribe(data => {
      this.headTeachers = data;
      this.headTeachers.forEach(headTeacher => {
        if(JSON.stringify(headTeacher) == JSON.stringify(this.studyProgram.headTeacher)){
          this.studyProgramAddEditForm.patchValue({headTeacher : headTeacher});
        }
      })
    });
  }

  save(){
    const s = this.studyProgramAddEditForm.value;
    this.studyProgram = s;
    if(this.edit){
      this.studyProgramService.update(this.id, this.studyProgram).subscribe(_ => {
        this.snackBarService.openSnackBar("Successfully edited study program", "X")
      });
    }
    else{
      this.studyProgramService.add(this.studyProgram).subscribe(_ => {
        this.studyProgramAddEditForm.reset();
        this.headTeachers = [];
        this.snackBarService.openSnackBar("Successfully added new study program", "X")
    });
    }
  }

}
