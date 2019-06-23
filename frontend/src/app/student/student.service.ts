import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Student } from './student.model';
import { StudentDetails } from './studentDetails.model';
import { StudentDissertationDTO } from './student-dissertation/student-dissertation-dto.model';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  public studentUrl = "http://localhost:8080/student";
  private subjAttUrl = "http://localhost:8080/subjectattendance";
  private dissertationUrl = "http://localhost:8080/dissertation";

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<Student[]>(this.studentUrl);
  }

  getOne(id: String) {
    return this.http.get<Student>(this.studentUrl + `/${id}`);
  }

  getOneByUsername(username: String) {
    return this.http.get<Student>(this.studentUrl+`/username/${username}`);
  }

  delete(id: String) {
    return this.http.delete(this.studentUrl + `/${id}`);
  }

  add(student: Student, image: File) {
    const postData = new FormData();
    if(image) {
      postData.append("profileImage", image, image.name);
    }
    postData.append("data", JSON.stringify(student));
    return this.http.post(this.studentUrl + '/register', postData);
  }

  update(username:string, student:Student, image:File) {
    const postData = new FormData();
    if(image) {
      postData.append("profileImage", image, image.name);
    }
    postData.append("data", JSON.stringify(student));
    return this.http.put(this.studentUrl+`/${username}`, postData)
  }

  getStudentsBySubjectId(subjId: string, teacherUsername: string) {
    return this.http.get<Student[]>(this.subjAttUrl + `/teacher/${teacherUsername}/${subjId}/students`);
  }

  searchStudents(queryParams: {}) {
    return this.http.get<Student[]>(this.studentUrl + `/search/`, {
      params:queryParams
    });   

  }

  getStudentDetails(id: string){
    return this.http.get<StudentDetails>(this.studentUrl+`/details/${id}`);
  }

  getDissertations(id: string){
    return this.http.get<StudentDissertationDTO[]>(this.dissertationUrl + `/${id}`);
  }

}
