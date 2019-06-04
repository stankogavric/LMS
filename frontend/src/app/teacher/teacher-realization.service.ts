import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TeacherRealization } from './teacher-realization.model';

@Injectable({
  providedIn: 'root'
})
export class TeacherRealizationService {

  private teacherRealizationUrl = "http://localhost:8080/teacherrealization";

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<TeacherRealization[]>(this.teacherRealizationUrl);
  }

  getAllByYearOfStudy(yearOfStudyId: number) {
    return this.http.get<TeacherRealization[]>(this.teacherRealizationUrl+`/yearOfStudy/${yearOfStudyId}`);
  }

  getOne(id: String) {
    return this.http.get<TeacherRealization>(this.teacherRealizationUrl+`/${id}`);
  }

  delete(id: String) {
    return this.http.delete(this.teacherRealizationUrl+`/${id}`);
  }

  add(teacherRealization:TeacherRealization) {
    return this.http.post(this.teacherRealizationUrl, teacherRealization);
  }

  update(id:string, teacherRealization:TeacherRealization) {
    return this.http.put(this.teacherRealizationUrl+`/${id}`, teacherRealization)
  }

}

