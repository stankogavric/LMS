import { Injectable } from '@angular/core';
import {Subject} from './subject.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SubjectAttendanceService {

  private subjAttUrl = "http://localhost:8080/subjectattendance"

  constructor(private http: HttpClient) { }

  getPastSubjects(id: number){
    return this.http.get<Subject[]>(this.subjAttUrl+`/pastSubjects/${id}`);
  }

  getCurrentSubjects(id: number){
    return this.http.get<Subject[]>(this.subjAttUrl+`/subjects/${id}`);
  }

}