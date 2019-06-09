import { Component, OnInit } from '@angular/core';
import { ExamService } from '../exam.service';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../../auth/auth.service';
import { ExamRegistrationDTO } from './exam-registration-dto.model';
import {MatSnackBar} from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material';
import { Subscription, Subscriber } from 'rxjs';

@Component({
  selector: 'app-exam-registration',
  templateUrl: './exam-registration.component.html',
  styleUrls: ['./exam-registration.component.css']
})
export class ExamRegistrationComponent implements OnInit {

  exams? : ExamRegistrationDTO[] = [];
  exam? : ExamRegistrationDTO;
  fetched = false;
  message = "";

  displayedColumns: string[] = ['i', 'subject', 'startTime', 'duration', 'examType','yearOfStudyYear', 'studyProgramName', 'register'] 
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

  getAvailableExams(username: string){
    this.examService.getStudentsAvailableExamsForRegistrations(username).subscribe(
      (data: []) => {
        if(data != null){
          for (let i = 0; i < data.length; i++) {
            let ex = new ExamRegistrationDTO();
            ex.examId = data[i][0];
            ex.subjectRealizationId = data[i][1];
            ex.subjectName = data[i][2];
            ex.startTime = data[i][3];
            ex.duration = data[i][4];
            ex.examType = data[i][5];
            ex.yearOfStudyYear = data[i][6];
            ex.studyProgramName = data[i][7];
            this.exams.push(ex);            
          }
        }
        this.dataSource.data = this.exams;
        this.fetched = true;        
      });
  }
  register(examId: string, subjectRealizationId: string){
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
       //TODO fix update changes in table after registering exam
        //this.dataSource._updateChangeSubscription();
      }, (err : any) => {
        this.snackbar.open('Error while registering exam', 'Ok', {
          duration: 3000,
          panelClass : ["error"]
        });
      }
    );
  }

  

}
