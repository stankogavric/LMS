import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Message } from './message.model';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private messageUrl = "http://localhost:8080/message";

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<Message[]>(this.messageUrl);
  }

  getAllByUser(username: string){
    return this.http.get<Message[]>(this.messageUrl+`/user/${username}`);
  }

  getOne(id: String) {
    return this.http.get<Message>(this.messageUrl+`/${id}`);
  }

  delete(id: String) {
    return this.http.delete(this.messageUrl+`/${id}`);
  }

  add(message:Message) {
    return this.http.post(this.messageUrl, message);
  }

  update(id:string, message:Message) {
    return this.http.put(this.messageUrl+`/${id}`, message)
  }

}