import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Exam } from './exam.model';
import { ExamType } from './exam-type.model';
import { ExamRealization } from './exam-realization.model';
import { ExamRegistrationDTO } from './exam-registration/exam-registration-dto.model';
import { StudentExamRegistrationDTO } from './enter-grade/student-exam-registration-dto.model';

@Injectable({
  providedIn: 'root'
})
export class ExamService {

  private examUrl = "http://localhost:8080/exam";
  private examReal = "http://localhost:8080/examrealization";

  getExamRealUrl(){
    return this.examReal;
  }
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

  getAvailableExamsForRegistration(username: string){
    return this.http.get<ExamRegistrationDTO[]>(this.examUrl + `/availableExams/${username}`);
  }

  registerExam(data: {}){
    return this.http.post(this.examReal, data);

  }

  getStudentExamRegistrationsBySubject(subjectId: string, teacherUsername: string){
    return this.http.get<StudentExamRegistrationDTO[]>(this.examReal + `/${teacherUsername}/grading/${subjectId}`)
  }

  submitGrades(studentGrades: StudentExamRegistrationDTO[], teacherUsername: string, subjectId: string){
    return this.http.post(this.examUrl + `/${teacherUsername}/${subjectId}/addGrades`, studentGrades);
  }



}
