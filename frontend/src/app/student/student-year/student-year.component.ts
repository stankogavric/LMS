import { Component, OnInit, Input } from '@angular/core';
import { StudentYearDTO } from './student-year-dto.model';

@Component({
  selector: 'app-student-year',
  templateUrl: './student-year.component.html',
  styleUrls: ['./student-year.component.css']
})
export class StudentYearComponent implements OnInit {

  panelOpenState = false;
  @Input() studentYear?: StudentYearDTO;
  constructor() { }

  ngOnInit() {
  }

}
