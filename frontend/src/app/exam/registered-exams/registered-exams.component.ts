import { Component, OnInit, ViewChild } from '@angular/core';
import { RegisteredExamDTO } from '../registered-exam-dto-model';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ExamService } from '../exam.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-registered-exams',
  templateUrl: './registered-exams.component.html',
  styleUrls: ['./registered-exams.component.css']
})
export class RegisteredExamsComponent implements OnInit {

  exams: RegisteredExamDTO[] = [];
  exam: RegisteredExamDTO;
  displayedColumns: string[] = ['registrationNum', 'subject', 'startTime', 'endTime', 'examType'];
  dataSource = new MatTableDataSource<RegisteredExamDTO>(this.exams);
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private examService: ExamService, private route: ActivatedRoute) {   
  }

  ngOnInit() {
    this.getRegisteredExams(this.route.snapshot.paramMap.get('id'));
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  getRegisteredExams(id: string){
    this.examService.getStudentsRegisteredExams(id).subscribe(
      (data: []) => {
        if (data != null) {
          for (let i = 0; i < data.length; i++) {
            this.exams.push(new RegisteredExamDTO(data[i][0], data[i][1], data[i][2], data[i][3], data[i][4]));            
          };
        }
        this.dataSource.data = this.exams;
      }
    );
  }

}
