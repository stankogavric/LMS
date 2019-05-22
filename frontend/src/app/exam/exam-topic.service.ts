import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ExamTopic } from '../exam-topic/exam-topic.model';

@Injectable({
  providedIn: 'root'
})
export class ExamTopicService {

  private examTopicUrl = "http://localhost:8080/examTopic";

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<ExamTopic[]>(this.examTopicUrl);
  }

  getOne(id: String) {
    return this.http.get<ExamTopic>(this.examTopicUrl+`/${id}`);
  }

  delete(id: String) {
    return this.http.delete(this.examTopicUrl+`/${id}`);
  }

  add(examTopic:ExamTopic) {
    return this.http.post(this.examTopicUrl, examTopic);
  }

  update(id:string, examTopic:ExamTopic) {
    return this.http.put(this.examTopicUrl+`/${id}`, examTopic)
  }

}
