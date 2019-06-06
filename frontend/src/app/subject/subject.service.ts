import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Subject } from './subject.model';
import { Topic } from '../topic/topic.model';
import { ExamDTO } from '../exam/examDTO.model';

@Injectable({
  providedIn: 'root'
})
export class SubjectService {

  private subjectUrl = "http://localhost:8080/subject";
  private teacherRealUrl = "http://localhost:8080/teacherrealization"
  private subjAttUrl = "http://localhost:8080/subjectattendance"


  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<Subject[]>(this.subjectUrl);
  }

  getOne(id: String) {
    return this.http.get<Subject>(this.subjectUrl+`/${id}`);
  }

  delete(id: String) {
    return this.http.delete(this.subjectUrl+`/${id}`);
  }

  add(subject:Subject) {
    return this.http.post(this.subjectUrl, subject);
  }

  update(id:string, subject:Subject) {
    return this.http.put(this.subjectUrl+`/${id}`, subject)
  }

  getSyllabuses(subjectId: number) {
    return this.http.get<Topic[]>(this.subjectUrl+`/syllabuses/${subjectId}`);
  }

  getTeachersCurrentSubjects(username: String){
    return this.http.get<Subject[]>(this.teacherRealUrl+`/${username}/currentSubjects`);
  }

  getStudentsPastSubjects(username : String){
    return this.http.get<ExamDTO[]>(this.subjAttUrl+`/pastSubjects/${username}`);
  }

  getStudentsCurrentSubjects(username: String){
    return this.http.get<Subject[]>(this.subjAttUrl+`/subjects/${username}`);
  }

}
