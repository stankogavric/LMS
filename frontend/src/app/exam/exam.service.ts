import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Exam } from './exam.model';
import { ExamType } from './exam-type.model';
import { ExamTopic } from '../exam-topic/exam-topic.model';
import { Observable } from 'rxjs';
import { ExamDTO } from './examDTO.model';

@Injectable({
  providedIn: 'root'
})
export class ExamService {

  private examUrl = "http://localhost:8080/exam";

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<Exam[]>(this.examUrl);
  }

  getOne(id: String) {
    return this.http.get<Exam>(this.examUrl+`/${id}`);
  }

  delete(id: String) {
    return this.http.delete(this.examUrl+`/${id}`);
  }

  add(exam:Exam) {
    return this.http.post(this.examUrl, exam);
  }

  update(id:string, exam:Exam) {
    return this.http.put(this.examUrl+`/${id}`, exam)
  }

  getExamTypes() {
    return this.http.get<ExamType[]>(this.examUrl+`/types`);
  }

  getStudentsExams(id: string){
    return this.http.get<[]>(this.examUrl+ `/${id}/exams`);
  }

}
