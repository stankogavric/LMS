import { Component, OnInit } from '@angular/core';
import { StudentService } from '../student.service';
import { ActivatedRoute } from '@angular/router';
import { StudentDissertationDTO } from './student-dissertation-dto.model';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-student-dissertation',
  templateUrl: './student-dissertation.component.html',
  styleUrls: ['./student-dissertation.component.css']
})
export class StudentDissertationComponent implements OnInit {

  dissertations? : StudentDissertationDTO[] = [];
  diss? : StudentDissertationDTO;
  displayedColumns: string[] = ['title', 'applicationDate', 'defenseDate', 'mentor', 'studyProgramName', 'fileUrl'];
  dataSource = new MatTableDataSource<StudentDissertationDTO>(this.dissertations);
  fetched = false;
  constructor(private studentService: StudentService, private route: ActivatedRoute) {
    this.dataSource = new MatTableDataSource();
   }

  ngOnInit() {
    this.getDissertations(this.route.snapshot.paramMap.get("id"));
    this.dataSource.data = this.dissertations;
  }

  getDissertations(id: string){
    this.studentService.getDissertations(id).subscribe(
      (data: StudentDissertationDTO[]) => {
        if (data != null){
          for (let i = 0; i < data.length; i++) {
            this.dissertations.push(new StudentDissertationDTO(data[i][0], data[i][1], data[i][2], data[i][3], data[i][4] + " " + data[i][5], data[i][6]));            
          };
          this.fetched = true;
        } 
      });
  }

}
