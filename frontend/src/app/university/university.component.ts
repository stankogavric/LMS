import { Component, OnInit } from '@angular/core';
import { University } from './university.model';
import { UniversityService } from './university.service';
import { ActivatedRoute } from '@angular/router';
import { Phone } from '../phone/phone.model';
import { Email } from '../email/email.model';

@Component({
  selector: 'app-university',
  templateUrl: './university.component.html',
  styleUrls: ['./university.component.css']
})
export class UniversityComponent implements OnInit {

  public university: University;

  constructor(private universityService: UniversityService, private rout: ActivatedRoute) { }

  ngOnInit() {
    this.universityService.getAll().subscribe((data: University[])=>{
      if(data.length<1){
        return;
      }
      this.university=data[0];
      this.universityService.getUniversityPhones(data[0].id).subscribe((data: Phone[])=>{
        this.university.phones=data;
      });
      this.universityService.getUniversityEmails(data[0].id).subscribe((data: Email[])=>{
        this.university.emails=data;
      });
    });
  }

}
