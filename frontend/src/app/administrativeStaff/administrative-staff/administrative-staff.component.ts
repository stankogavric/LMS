import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { MatSort } from '@angular/material/sort';
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

  displayedColumns: string[] = ['no', 'personalData.firstName', 'personalData.lastName', 'personalData.personalNumber', 'personalData.profilePicturePath', 'address.city.country.name', 'address.city.name', 'address.street', 'address.number', 'accountData.email', 'accountData.username', 'actions'];
  dataSource = new MatTableDataSource<AdministrativeStaff>(this.administrativeStaff);

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private administrativeStaffService: AdministrativeStaffService) {}

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sortingDataAccessor = (item, property) => {
      if (property.includes('.')) return property.split('.').reduce((o,i)=>o[i], item)
        return item[property];
    };
    this.dataSource.sort = this.sort;
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
