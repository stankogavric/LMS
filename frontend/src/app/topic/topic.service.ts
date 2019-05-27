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

  getAllBySubjectId(id: number) {
    return this.http.get<Topic[]>(this.topicUrl+`/bySubject/${id}`);
  }

  getOne(id: String) {
    return this.http.get<Topic>(this.topicUrl+`/${id}`);
  }

  delete(id: number) {
    return this.http.delete(this.topicUrl+`/${id}`);
  }

  add(topic:Topic, icon:File) {
    const postData = new FormData();
    postData.append("icon", icon, icon.name);
    postData.append("data", JSON.stringify(topic));
    return this.http.post(this.topicUrl, postData);
  }

  update(id:number, topic:Topic) {
    return this.http.put(this.topicUrl+`/${id}`, topic)
  }

}
