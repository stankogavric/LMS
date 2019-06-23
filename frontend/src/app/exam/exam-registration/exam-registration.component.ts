import { Component, OnInit } from '@angular/core';
import { ExamService } from '../exam.service';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../../auth/auth.service';
import { ExamRegistrationDTO } from './exam-registration-dto.model';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-exam-registration',
  templateUrl: './exam-registration.component.html',
  styleUrls: ['./exam-registration.component.css']
})
export class ExamRegistrationComponent implements OnInit {

  exams: ExamRegistrationDTO[] = [];
  exam: ExamRegistrationDTO = new ExamRegistrationDTO();
  fetched = false;
  message = "";

  displayedColumns: string[] = ['i', 'subject', 'startTime', 'duration', 'examType', 'yearOfStudyYear', 'studyProgramName', 'register']
  dataSource = new MatTableDataSource<ExamRegistrationDTO>(this.exams);
  constructor(private examService: ExamService, private route: ActivatedRoute, private authService: AuthService, private snackbar: MatSnackBar) { }

  ngOnInit() {
    let loggedUser = this.authService.getCurrentUser();
    if (loggedUser) {
      this.getAvailableExams(loggedUser);
    }
    else {
      console.log("unknown username");
    }
  }

  getAvailableExams(username: string) {
    this.examService.getAvailableExamsForRegistration(username).subscribe(
      (data: ExamRegistrationDTO[]) => {
        this.exams = data;
        this.dataSource.data = this.exams;
      });
  }

  register(examId: string, subjectRealizationId: string) {
    let data = {
      "examId": examId,
      "subjectRealId": subjectRealizationId,
      "studentUsername": this.authService.getCurrentUser()
    };
    this.examService.registerExam(data).subscribe(
      (res: Response) => {
        this.snackbar.open('Successfully registered exam', 'Ok', {
          duration: 3000,
          panelClass: ["success"]
        });
        this.getAvailableExams(this.authService.getCurrentUser());
      }, (err: any) => {
        this.snackbar.open('Error while registering exam', 'Ok', {
          duration: 3000,
          panelClass: ["error"]
        });
      }
    );
  }



}
