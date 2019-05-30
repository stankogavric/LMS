import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Student } from './student.model';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private studentUrl = "http://localhost:8080/student";
  private subjAttUrl = "http://localhost:8080/subjectattendance"

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
    if(image) {
      postData.append("profileImage", image, image.name);
    }
    postData.append("data", JSON.stringify(student));
    return this.http.post(this.studentUrl+'/register', postData);
  }

  update(id:string, student:Student) {
    return this.http.put(this.studentUrl+`/${id}`, student)
  }
  
  getStudentsBySubjectId(subjId: number, teacherUsername: string) {
    return this.http.get<Student[]>(this.subjAttUrl+`/teacher/${teacherUsername}/${subjId}/students`);
  }
}
