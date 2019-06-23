import { Component, OnInit } from '@angular/core';
import { StudentDetails } from '../studentDetails.model';
import { ActivatedRoute } from '@angular/router';
import { StudentService } from '../student.service';
import { ExamDTO } from '../../exam/examDTO.model';
import { ExamService } from '../../exam/exam.service';
import { FileService } from '../../file/file.service';

@Component({
  selector: 'app-student-detail',
  templateUrl: './student-detail.component.html',
  styleUrls: ['./student-detail.component.css']
})
export class StudentDetailComponent implements OnInit {

  student?: StudentDetails = new StudentDetails();
  exams?: ExamDTO[] = [];
  totalEcts?: number;
  avgGrade?: number;
  constructor(private studentService: StudentService, private route: ActivatedRoute, private examService: ExamService, private fileService: FileService) { }

  ngOnInit() {
    this.studentService.getStudentDetails(this.route.snapshot.paramMap.get('id')).subscribe(
      (data: StudentDetails) => {
        this.student = data;
        this.student.profilePicturePath = this.fileService.fileUrl + this.student.profilePicturePath;
      }
    );
    this.getStudentExams(this.route.snapshot.paramMap.get('id'));
  }

  getStudentExams(studentId: string) {
    this.examService.getStudentsExams(studentId).subscribe(
      (data: ExamDTO[]) => {
        if (data != null) {
          this.exams = data;
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
    };
    this.totalEcts = ects;
  }

}
