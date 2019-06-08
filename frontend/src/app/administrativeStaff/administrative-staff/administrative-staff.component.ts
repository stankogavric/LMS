import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { AdministrativeStaff } from '../administrative-staff';
import { AdministrativeStaffService } from '../administrative-staff.service';

@Component({
  selector: 'app-administrative-staff',
  templateUrl: './administrative-staff.component.html',
  styleUrls: ['./administrative-staff.component.css']
})
export class AdministrativeStaffComponent implements OnInit {

  administrativeStaff : AdministrativeStaff[] = [];
  administrativeStaffPerson : AdministrativeStaff = new AdministrativeStaff();

  displayedColumns: string[] = ['no', 'firstName', 'lastName', 'personalNumber', 'profilePicturePath', 'country', 'city', 'street', 'streetNumber', 'email', 'username', 'actions'];
  dataSource = new MatTableDataSource<AdministrativeStaff>(this.administrativeStaff);

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private administrativeStaffService: AdministrativeStaffService) {}

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.getAll();
  }

  getAll(){
    this.administrativeStaffService.getAll().subscribe((data: AdministrativeStaff[]) => {
      this.administrativeStaff = data;
      this.dataSource.data = data;
    });
  }

  delete(id: String){
    this.administrativeStaffService.delete(id).subscribe((data: any) => {
      this.getAll();
    });
  }

  update(id: string, administrativeStaffPerson: AdministrativeStaff, image: File){
    this.administrativeStaffService.update(id, administrativeStaffPerson, image).subscribe((data: any) => {
      this.getAll();
    });
  }

}
