import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Teacher } from './teacher.model';

@Injectable({
  providedIn: 'root'
})
export class TeacherService {

  private teacherUrl = "http://localhost:8080/teacher";

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<Teacher[]>(this.teacherUrl);
  }

  getOne(id: String) {
    return this.http.get<Teacher>(this.teacherUrl+`/${id}`);
  }

  delete(id: String) {
    return this.http.delete(this.teacherUrl+`/${id}`);
  }

  add(teacher:Teacher) {
    return this.http.post(this.teacherUrl, teacher);
  }

  update(id:string, teacher:Teacher) {
    return this.http.put(this.teacherUrl+`/${id}`, teacher)
  }

}