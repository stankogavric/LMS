import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Faculty } from './faculty.model';
import { Phone } from '../phone/phone.model';
import { Email } from '../email/email.model';
import { StudyProgram } from '../study-program/study-program.model';

@Injectable({
  providedIn: 'root'
})
export class FacultyService {

  private facultyUrl = "http://localhost:8080/faculty";

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<Faculty[]>(this.facultyUrl);
  }

  getOne(id: String) {
    return this.http.get<Faculty>(this.facultyUrl+`/${id}`);
  }

  delete(id: String) {
    return this.http.delete(this.facultyUrl+`/${id}`);
  }

  add(faculty:Faculty) {
    return this.http.post(this.facultyUrl, faculty);
  }

  update(id:string, faculty:Faculty) {
    return this.http.put(this.facultyUrl+`/${id}`, faculty)
  }

  getStudyPrograms(facultyId: number) {
    return this.http.get<StudyProgram[]>(this.facultyUrl+`/studyPrograms/${facultyId}`);
  }

  getFacultyPhones(facultyId: number) {
    return this.http.get<Phone[]>(this.facultyUrl+`/phones/${facultyId}`);
  }

  getFacultyEmails(facultyId: number) {
    return this.http.get<Email[]>(this.facultyUrl+`/emails/${facultyId}`);
  }
}
