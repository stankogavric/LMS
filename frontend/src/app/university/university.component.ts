import { Component, OnInit } from '@angular/core';
import { University } from './university.model';
import { UniversityService } from './university.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-university',
  templateUrl: './university.component.html',
  styleUrls: ['./university.component.css']
})
export class UniversityComponent implements OnInit {

  private university: University;

  constructor(private universityService: UniversityService, private rout: ActivatedRoute) { }

  ngOnInit() {
    this.universityService.getAll().subscribe((data: University[])=>{
      this.university=data[0];});
  }

}
