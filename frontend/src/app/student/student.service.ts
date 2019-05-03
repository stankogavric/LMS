import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Student } from './student.model';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private studentUrl = "http://localhost:8080/student";

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<Student[]>(this.studentUrl);
  }

  getOne(id: String) {
    return this.http.get<Student>(this.studentUrl+`/${id}`);
  }

  delete(id: String) {
    return this.http.delete(this.studentUrl+`/${id}`);
  }

  add(student:Student, image:File) {
    const postData = new FormData();
    postData.append("profileImage", image, image.name);
    postData.append("data", JSON.stringify(student));
    return this.http.post(this.studentUrl+'/register', postData);
  }

  update(id:string, student:Student) {
    return this.http.put(this.studentUrl+`/${id}`, student)
  }

}
