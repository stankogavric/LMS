import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Teacher } from './teacher.model';
import { SubjectRealization } from '../subject/subject-realization.model';

@Injectable({
  providedIn: 'root'
})
export class TeacherService {

  public teacherUrl = "http://localhost:8080/teacher";

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<Teacher[]>(this.teacherUrl);
  }

  getAllByFaculty(facultyId: number) {
    return this.http.get<Teacher[]>(this.teacherUrl+`/faculty/${facultyId}`);
  }

  getOne(id: String) {
    return this.http.get<Teacher>(this.teacherUrl+`/${id}`);
  }

  getOneByUsername(username: String) {
    return this.http.get<Teacher>(this.teacherUrl+`/username/${username}`);
  }

  delete(id: String) {
    return this.http.delete(this.teacherUrl+`/${id}`);
  }

  add(teacher:Teacher, image:File) {
    const postData = new FormData();
    if(image) {
      postData.append("profileImage", image, image.name);
    }
    postData.append("data", JSON.stringify(teacher));
    return this.http.post(this.teacherUrl+'/register', postData);
  }

  update(username:string, teacher:Teacher, image:File) {
    const postData = new FormData();
    if(image) {
      postData.append("profileImage", image, image.name);
    }
    postData.append("data", JSON.stringify(teacher));
    return this.http.put(this.teacherUrl+`/${username}`, postData)
  }

  getSubjectRealization(username: String){
    return this.http.get<SubjectRealization[]>(this.teacherUrl+`/${username}`+"/subjectRealizations");
  }

}
