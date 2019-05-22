import { Component, OnInit, Input } from '@angular/core';
import { Subject } from './subject.model';

@Component({
  selector: 'app-subject',
  templateUrl: './subject.component.html',
  styleUrls: ['./subject.component.css']
})
export class SubjectComponent implements OnInit {

  @Input() subject: Subject;

  constructor() { }

  ngOnInit() {
  }

}
