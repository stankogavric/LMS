import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SubjectRealization } from './subject-realization.model';

@Injectable({
  providedIn: 'root'
})
export class SubjectRealizationService {

  private subjectRealizationUrl = "http://localhost:8080/subjectrealization";

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<SubjectRealization[]>(this.subjectRealizationUrl);
  }

  getAllByYearOfStudy(yearOfStudyId: number) {
    return this.http.get<SubjectRealization[]>(this.subjectRealizationUrl+`/yearOfStudy/${yearOfStudyId}`);
  }

  getOne(id: String) {
    return this.http.get<SubjectRealization>(this.subjectRealizationUrl+`/${id}`);
  }

  delete(id: String) {
    return this.http.delete(this.subjectRealizationUrl+`/${id}`);
  }

  add(subjectRealization:SubjectRealization) {
    return this.http.post(this.subjectRealizationUrl, subjectRealization);
  }

  update(id:string, subjectRealization:SubjectRealization) {
    return this.http.put(this.subjectRealizationUrl+`/${id}`, subjectRealization)
  }

}
