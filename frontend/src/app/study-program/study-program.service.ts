import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { StudyProgram } from './study-program.model';
import { YearOfStudy } from '../year-of-study/year-of-study.model';

@Injectable({
    providedIn: 'root'
  })
  export class StudyProgramService {
  
    private studyProgramUrl = "http://localhost:8080/studyprogram";
  
    constructor(private http: HttpClient) {
    }
  
    getAll() {
      return this.http.get<StudyProgram[]>(this.studyProgramUrl);
    }
  
    getOne(id: String) {
      return this.http.get<StudyProgram>(this.studyProgramUrl+`/${id}`);
    }
  
    delete(id: String) {
      return this.http.delete(this.studyProgramUrl+`/${id}`);
    }
  
    add(studyProgram:StudyProgram) {
      return this.http.post(this.studyProgramUrl, studyProgram);
    }
  
    update(id:string, studyProgram:StudyProgram) {
      return this.http.put(this.studyProgramUrl+`/${id}`, studyProgram)
    }

    getYearsOfStudy(studyProgramId: number) {
      return this.http.get<YearOfStudy[]>(this.studyProgramUrl+`/yearsofstudy/${studyProgramId}`);
    }
  
  }