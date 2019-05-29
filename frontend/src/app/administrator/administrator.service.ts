import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Administrator } from './administrator.model';

@Injectable({
  providedIn: 'root'
})
export class AdministratorService {

  private administratorUrl = "http://localhost:8080/administrator";

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<Administrator[]>(this.administratorUrl);
  }

  getOne(id: String) {
    return this.http.get<Administrator>(this.administratorUrl+`/${id}`);
  }

  getOneByUsername(username: String) {
    return this.http.get<Administrator>(this.administratorUrl+`/username/${username}`);
  }

  delete(id: String) {
    return this.http.delete(this.administratorUrl+`/${id}`);
  }

  add(administrator:Administrator) {
    return this.http.post(this.administratorUrl+'/register', administrator);
  }

  update(username:string, administrator:Administrator) {
    return this.http.put(this.administratorUrl+`/${username}`, administrator)
  }

}
