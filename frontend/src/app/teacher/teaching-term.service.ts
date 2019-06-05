import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TeachingTerm } from './teaching-term.model';

@Injectable({
  providedIn: 'root'
})
export class TeachingTermService {

  private teachingTermUrl = "http://localhost:8080/teachingterm";

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<TeachingTerm[]>(this.teachingTermUrl);
  }

  getAllByYearOfStudy(yearOfStudyId: number) {
    return this.http.get<TeachingTerm[]>(this.teachingTermUrl+`/yearOfStudy/${yearOfStudyId}`);
  }

  getOne(id: String) {
    return this.http.get<TeachingTerm>(this.teachingTermUrl+`/${id}`);
  }

  delete(id: String) {
    return this.http.delete(this.teachingTermUrl+`/${id}`);
  }

  add(teachingTerm:TeachingTerm) {
    return this.http.post(this.teachingTermUrl, teachingTerm);
  }

  update(id:string, teachingTerm:TeachingTerm) {
    return this.http.put(this.teachingTermUrl+`/${id}`, teachingTerm)
  }

}

