import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Topic } from './topic.model';

@Injectable({
  providedIn: 'root'
})
export class TopicService {

  private topicUrl = "http://localhost:8080/topic";

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<Topic[]>(this.topicUrl);
  }

  getOne(id: String) {
    return this.http.get<Topic>(this.topicUrl+`/${id}`);
  }

  delete(id: String) {
    return this.http.delete(this.topicUrl+`/${id}`);
  }

  add(topic:Topic) {
    return this.http.post(this.topicUrl, topic);
  }

  update(id:string, topic:Topic) {
    return this.http.put(this.topicUrl+`/${id}`, topic)
  }

}
