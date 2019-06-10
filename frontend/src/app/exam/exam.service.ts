import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Exam } from './exam.model';
import { ExamType } from './exam-type.model';
import { ExamRealization } from './exam-realization.model';

@Injectable({
  providedIn: 'root'
})
export class ExamService {

  private examUrl = "http://localhost:8080/exam";
  private examReal = "http://localhost:8080/examrealization";

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

  getStudentsRegisteredExams(id: string){
    return this.http.get<[]>(this.examReal+ `/${id}`);
  }

  getStudentsAvailableExamsForRegistrations(username: string){
    return this.http.get<[]>(this.examUrl + `/availableExams/${username}`);
  }

  registerExam(data: {}){
    console.log(data);
    return this.http.post(this.examReal, data);

  }



}
