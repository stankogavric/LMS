import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Classroom } from './classroom.model';

@Injectable({
  providedIn: 'root'
})
export class ClassroomService {

  private classroomUrl = "http://localhost:8080/classroom";

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<Classroom[]>(this.classroomUrl);
  }

  getOne(id: String) {
    return this.http.get<Classroom>(this.classroomUrl+`/${id}`);
  }

  delete(id: String) {
    return this.http.delete(this.classroomUrl+`/${id}`);
  }

  add(classroom:Classroom) {
    return this.http.post(this.classroomUrl, classroom);
  }

  update(id:string, classroom:Classroom) {
    return this.http.put(this.classroomUrl+`/${id}`, classroom)
  }

}
