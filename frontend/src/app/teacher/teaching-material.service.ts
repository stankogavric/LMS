import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TeachingMaterial } from './teaching-material.model';

@Injectable({
  providedIn: 'root'
})
export class TeachingMaterialService {

  private teachingMaterialUrl = "http://localhost:8080/teachingmaterial";

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<TeachingMaterial[]>(this.teachingMaterialUrl);
  }

  getAllBySubject(id: number) {
    return this.http.get<TeachingMaterial[]>(this.teachingMaterialUrl+`/subject/${id}`);
  }

  getOne(id: String) {
    return this.http.get<TeachingMaterial>(this.teachingMaterialUrl+`/${id}`);
  }

  delete(id: String) {
    return this.http.delete(this.teachingMaterialUrl+`/${id}`);
  }

  add(teachingMaterial:TeachingMaterial) {
    return this.http.post(this.teachingMaterialUrl, teachingMaterial);
  }

  update(id:string, teachingMaterial:TeachingMaterial) {
    return this.http.put(this.teachingMaterialUrl+`/${id}`, teachingMaterial)
  }

}