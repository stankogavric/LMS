import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AdministrativeStaff } from './administrative-staff';
import { YearOfStudy } from '../year-of-study/year-of-study.model';
import { Student } from '../student/student.model';

@Injectable({
  providedIn: 'root'
})
export class AdministrativeStaffService {

  private administrativeStaffUrl = "http://localhost:8080/administrativestaff";

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<AdministrativeStaff[]>(this.administrativeStaffUrl);
  }

  getStudentsForEnrollmentToTheNextYear(yearOfStudyId: number) {
    return this.http.get<Student[]>(this.administrativeStaffUrl+`/enrollment/${yearOfStudyId}`);
  }

  enrollmentToTheNextYear(student: Student, yearOfStudy: YearOfStudy){
    return this.http.put(this.administrativeStaffUrl+`/enrollment/${student.id}`, yearOfStudy);
  }

  getOne(id: String) {
    return this.http.get<AdministrativeStaff>(this.administrativeStaffUrl+`/${id}`);
  }

  getOneByUsername(username: String) {
    return this.http.get<AdministrativeStaff>(this.administrativeStaffUrl+`/username/${username}`);
  }

  delete(id: String) {
    return this.http.delete(this.administrativeStaffUrl+`/${id}`);
  }

  add(admStf:AdministrativeStaff, image:File) {
    const postData = new FormData();
    if(image) {
      postData.append("profileImage", image, image.name);
    }
    postData.append("data", JSON.stringify(admStf));
    return this.http.post(this.administrativeStaffUrl+'/register', postData);
  }

  update(username:string, administrativeStaff:AdministrativeStaff, image:File) {
    const postData = new FormData();
    if(image) {
      postData.append("profileImage", image, image.name);
    }
    postData.append("data", JSON.stringify(administrativeStaff));
    return this.http.put(this.administrativeStaffUrl+`/${username}`, postData)
  }

}
