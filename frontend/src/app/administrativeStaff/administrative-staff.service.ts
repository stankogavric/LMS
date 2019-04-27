import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AdministrativeStaff } from './administrative-staff';

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

  getOne(id: String) {
    return this.http.get<AdministrativeStaff>(this.administrativeStaffUrl+`/${id}`);
  }

  delete(id: String) {
    return this.http.delete(this.administrativeStaffUrl+`/${id}`);
  }

  add(administrativeStaff:AdministrativeStaff) {
    return this.http.post(this.administrativeStaffUrl, administrativeStaff);
  }

  update(id:string, administrativeStaff:AdministrativeStaff) {
    return this.http.put(this.administrativeStaffUrl+`/${id}`, administrativeStaff)
  }

}
