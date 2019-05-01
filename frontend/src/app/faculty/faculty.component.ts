import { Component, OnInit } from '@angular/core';
import { FacultyService } from './faculty.service';
import { ActivatedRoute } from '@angular/router';
import { Faculty } from './faculty.model';
import { Phone } from '../phone/phone.model';
import { Email } from '../email/email.model';
import { StudyProgram } from '../study-program/study-program.model';

@Component({
  selector: 'app-faculty',
  templateUrl: './faculty.component.html',
  styleUrls: ['./faculty.component.css']
})
export class FacultyComponent implements OnInit {

  public faculty: Faculty;

  constructor(private facultyService: FacultyService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(routeParams => {
      this.facultyService.getOne(routeParams.id).subscribe((data: Faculty)=>{
        this.faculty=data;
        this.facultyService.getStudyPrograms(data.id).subscribe((data: StudyProgram[])=>{
          this.faculty.studyPrograms=data;
        });
        this.facultyService.getFacultyPhones(data.id).subscribe((data: Phone[])=>{
          this.faculty.phones=data;
        });
        this.facultyService.getFacultyEmails(data.id).subscribe((data: Email[])=>{
          this.faculty.emails=data;
        });
      });
    });
  }

}
