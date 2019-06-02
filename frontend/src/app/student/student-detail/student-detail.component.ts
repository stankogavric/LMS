import { Component, OnInit } from '@angular/core';
import { StudentDetails } from '../studentDetails.model';
import { ActivatedRoute } from '@angular/router';
import { StudentService } from '../student.service';

@Component({
  selector: 'app-student-detail',
  templateUrl: './student-detail.component.html',
  styleUrls: ['./student-detail.component.css']
})
export class StudentDetailComponent implements OnInit {

  student: StudentDetails;
  constructor(private studentService: StudentService, private route: ActivatedRoute) { }

  ngOnInit() {

    this.studentService.getStudentDetails(this.route.snapshot.paramMap.get('id')).subscribe(
       ( data: StudentDetails) => {
         this.student = data;
         console.log("ucitan student  ", data);
       }
     );
  }

}
