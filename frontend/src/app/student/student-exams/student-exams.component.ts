import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ExamDTO } from '../../exam/examDTO.model';
import { ExamService } from '../../exam/exam.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-student-exams',
  templateUrl: './student-exams.component.html',
  styleUrls: ['./student-exams.component.css']
})
export class StudentExamsComponent implements OnInit {

  exams?: ExamDTO[] = [];
  exam?: ExamDTO = new ExamDTO();
  displayedColumns: string[] = ['subject', 'points', 'grade', 'date', 'studyProgramName', 'year'];
  dataSource = new MatTableDataSource<ExamDTO>(this.exams);
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private examService: ExamService, private route: ActivatedRoute) {
    this.dataSource = new MatTableDataSource();
  }

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.getExams();
  }

  getExams() {
    this.examService.getStudentsExams(this.route.snapshot.paramMap.get('id')).subscribe(
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
            this.exams.push(exam);
          };
        };
        this.dataSource.data = this.exams;
      }

    );

  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
