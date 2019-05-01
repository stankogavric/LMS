import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { StudyProgram } from './study-program.model';
import { StudyProgramService } from './study-program.service';
import { YearOfStudy } from '../year-of-study/year-of-study.model';
import { Topic } from '../topic/topic.model';
import { YearOfStudyService } from '../year-of-study/year-of-study.service';
import { Subject } from '../subject/subject.model';
import { SubjectService } from '../subject/subject.service';

@Component({
  selector: 'app-study-program',
  templateUrl: './study-program.component.html',
  styleUrls: ['./study-program.component.css']
})
export class StudyProgramComponent implements OnInit {

  panelOpenState = false;
  public studyProgram: StudyProgram;

  constructor(private studyProgramService: StudyProgramService, private yearOfStudyService: YearOfStudyService, private subjectService: SubjectService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(routeParams => {
      this.studyProgramService.getOne(routeParams.id).subscribe((data: StudyProgram)=>{
        this.studyProgram=data;
        this.studyProgramService.getYearsOfStudy(routeParams.id).subscribe((data: YearOfStudy[])=>{
          this.studyProgram.yearsOfStudy=data;
          for(let y of this.studyProgram.yearsOfStudy){
            this.yearOfStudyService.getSubjects(y.id).subscribe((data: Subject[])=>{
              y.subjects=data;
              for(let s of y.subjects){
                this.subjectService.getSyllabuses(s.id).subscribe((data: Topic[])=>{
                  s.syllabus=data;
                });
              }
            });
          }
        });
      });
    });
  }
}