import { Component, OnInit } from '@angular/core';
import { FacultyService } from '../faculty/faculty.service';
import { Faculty } from '../faculty/faculty.model';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent implements OnInit {

  title = "sQuadunum";
  faculties = [];

  constructor(private facultyService: FacultyService) { }

  ngOnInit() {
    this.facultyService.getAll().subscribe((data: Faculty[]) => {
      this.faculties = data;
    });
  }

}
