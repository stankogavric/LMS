import { Component, OnInit} from '@angular/core';
import { SubjectService } from '../../subject/subject.service';
import { AuthService } from '../../auth/auth.service';
import { ExamDTO } from '../../exam/examDTO.model';

@Component({
  selector: 'app-past-subjects',
  templateUrl: './past-subjects.component.html',
  styleUrls: ['./past-subjects.component.css']
})
export class PastSubjectsComponent implements OnInit {

  exams?: ExamDTO[] = [];
  avgGrade?: number;
  totalEcts?: number;
  public fetched: boolean = false;

  constructor(private subjectService: SubjectService, private authService: AuthService) { }

  ngOnInit() {
    let loggedUser = this.authService.getCurrentUser();
    if (loggedUser) {
      this.getPastSubjects(loggedUser);
    }
    else {
      console.log("unknown username");
    }

  }

  getPastSubjects(username: String) {
    this.subjectService.getStudentsPastSubjects(username).subscribe(
      (data: ExamDTO[]) => {
        if (data != null) {
          for (let i = 0; i < data.length; i++) {
            let exam = new ExamDTO();
            exam.date = data[i][5];
            exam.grade = data[i][0];
            exam.points = data[i][4];
            exam.studyProgramName = data[i][3];
            exam.subject = data[i][1];
            exam.year = data[i][2];
            exam.ects = data[i][6];
            this.exams.push(exam);
          };
          this.fetched = true;
        };
        this.getAvgGrade();
        this.getTotalEcts();
      });
  }

  getAvgGrade() {
    let sum = 0;
    let total = 0
    for (let i = 0; i < this.exams.length; i++) {
      if (this.exams[i].grade != null) {
        sum += this.exams[i].grade;
        total++;
      }
    };
    this.avgGrade = sum / total;
  }

  getTotalEcts() {
    let ects = 0;
    for (let i = 0; i < this.exams.length; i++) {
      if (this.exams[i].ects != null) {
        ects += this.exams[i].ects;
      }
    }
    this.totalEcts = ects;
  }

}
