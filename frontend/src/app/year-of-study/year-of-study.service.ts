import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Subject } from '../subject/subject.model';
import { YearOfStudy } from '../year-of-study/year-of-study.model';

@Injectable({
  providedIn: 'root'
})
export class YearOfStudyService {

  private yearOfStudyUrl = "http://localhost:8080/yearofstudy";

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<YearOfStudy[]>(this.yearOfStudyUrl);
  }

  getOne(id: String) {
    return this.http.get<YearOfStudy>(this.yearOfStudyUrl+`/${id}`);
  }

  delete(id: String) {
    return this.http.delete(this.yearOfStudyUrl+`/${id}`);
  }

  add(yearOfStudy:YearOfStudy) {
    return this.http.post(this.yearOfStudyUrl, yearOfStudy);
  }

  update(id:string, yearOfStudy:YearOfStudy) {
    return this.http.put(this.yearOfStudyUrl+`/${id}`, yearOfStudy)
  }

  getSubjects(yearOfStudyId: number) {
    return this.http.get<Subject[]>(this.yearOfStudyUrl+`/subjects/${yearOfStudyId}`);
  }

}