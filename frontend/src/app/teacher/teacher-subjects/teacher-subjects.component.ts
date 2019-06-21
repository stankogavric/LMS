import { Component, OnInit, ViewChild } from '@angular/core';
import {Subject} from '../../subject/subject.model';
import {SubjectService} from '../../subject/subject.service';
import { AuthService } from '../../auth/auth.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-teacher-subjects',
  templateUrl: './teacher-subjects.component.html',
  styleUrls: ['./teacher-subjects.component.css']
})
export class TeacherSubjectsComponent implements OnInit {

  subjects: Subject[] = [];
  displayedColumns: string[] = ['no', 'name', 'ects', 'mandatory', 'lecturesNum', 'exercisesNum', 'otherActivitesNum', 'researchPaper', 'otherClasses', 'grades', 'students'];
  dataSource = new MatTableDataSource<Subject>(this.subjects);
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private subjectService: SubjectService, private authService: AuthService) { }

  ngOnInit() {
    let loggedUser = this.authService.getCurrentUser();
    if (loggedUser) {
      this.getSubjects(loggedUser);
      this.dataSource.paginator = this.paginator;
    }
    else {
      console.log("unknown username");
    }
  }

  getSubjects(username: String){
    this.subjectService.getTeachersCurrentSubjects(username).subscribe((data : Subject[]) => {
      this.subjects = data;
      this.dataSource.data = data;
      console.log(this.subjects);
    });
  }

}
