import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { File } from './file.model';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  public fileUrl = "http://localhost:8080/file/";

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<File[]>(this.fileUrl);
  }

  getAllBySubject(id: number) {
    return this.http.get<File[]>(this.fileUrl+`subject/${id}`);
  }

  getOne(id: String) {
    return this.http.get<File>(this.fileUrl+`/${id}`);
  }

  delete(id: String) {
    return this.http.delete(this.fileUrl+`/${id}`);
  }

  add(file:File) {
    return this.http.post(this.fileUrl, file);
  }

  update(id:string, file:File) {
    return this.http.put(this.fileUrl+`/${id}`, file)
  }

  downloadFile(data) {
    const REQUEST_PARAMS = new HttpParams().set('fileName', data.fileName);
    const REQUEST_URL = this.fileUrl+data.fileUrl;
    return this.http.get(REQUEST_URL, {
      params: REQUEST_PARAMS,
      responseType: 'arraybuffer'
    });
  }

  exportDataToXML(data) {
    const REQUEST_PARAMS = new HttpParams().set('fileName', data.fileName);
    const REQUEST_URL = data.fileUrl;
    return this.http.get(REQUEST_URL, {
      params: REQUEST_PARAMS,
      responseType: 'arraybuffer'
    });
  }

  exportDataToPDF(data) {
    const REQUEST_PARAMS = new HttpParams().set('fileName', data.fileName);
    const REQUEST_URL = data.fileUrl+'/pdf';
    return this.http.get(REQUEST_URL, {
      params: REQUEST_PARAMS,
      responseType: 'arraybuffer'
    });
  }

}

