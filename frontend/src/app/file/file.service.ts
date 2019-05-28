import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  public fileUrl = "http://localhost:8080/file/";

  constructor() { }
}
