import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { University } from './university.model';
import { Phone } from '../phone/phone.model';
import { Email } from '../email/email.model';

@Injectable({
  providedIn: 'root'
})
export class UniversityService {

  private universityUrl = "http://localhost:8080/university";

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<University[]>(this.universityUrl);
  }

  getOne(id: String) {
    return this.http.get<University>(this.universityUrl+`/${id}`);
  }

  delete(id: String) {
    return this.http.delete(this.universityUrl+`/${id}`);
  }

  add(university:University) {
    return this.http.post(this.universityUrl, university);
  }

  update(id:string, university:University) {
    return this.http.put(this.universityUrl+`/${id}`, university)
  }

  getUniversityPhones(universityId: number) {
    return this.http.get<Phone[]>(this.universityUrl+`/phones/${universityId}`);
  }

  getUniversityEmails(universityId: number) {
    return this.http.get<Email[]>(this.universityUrl+`/emails/${universityId}`);
  }

}
