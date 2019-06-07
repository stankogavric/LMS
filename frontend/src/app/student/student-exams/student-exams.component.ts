import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ExamDTO } from '../../exam/examDTO.model';

@Component({
  selector: 'app-student-exams',
  templateUrl: './student-exams.component.html',
  styleUrls: ['./student-exams.component.css']
})
export class StudentExamsComponent implements OnInit {

  @Input() exams: ExamDTO[] = [];
  exam?: ExamDTO = new ExamDTO();
  avgGrade?: number;
  totalEcts?: number;
  displayedColumns: string[] = ['subject', 'points', 'grade', 'date', 'studyProgramName', 'year', 'ects'];
  dataSource = new MatTableDataSource<ExamDTO>(this.exams);
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor() {
    this.dataSource = new MatTableDataSource();
  }

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.dataSource.data = this.exams;
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
