import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { MatSort } from '@angular/material/sort';
import { Administrator } from '../administrator.model';
import { AdministratorService } from '../administrator.service';

@Component({
  selector: 'app-administrators',
  templateUrl: './administrators.component.html',
  styleUrls: ['./administrators.component.css']
})
export class AdministratorsComponent implements OnInit {

  administrators : Administrator[] = [];
  administrator : Administrator = new Administrator();
  displayedColumns: string[] = ['no', 'accountData.email', 'accountData.username', 'actions'];
  dataSource = new MatTableDataSource<Administrator>(this.administrators);

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private administratorService: AdministratorService) {}

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
    this.administratorService.getAll().subscribe((data: Administrator[]) => {
      this.administrators = data;
      this.dataSource.data = data;
    });
  }

  delete(id: String){
    this.administratorService.delete(id).subscribe((data: any) => {
      this.getAll();
    });
  }

  update(id: string, administrator: Administrator){
    this.administratorService.update(id, administrator).subscribe((data: any) => {
      this.getAll();
    });
  }

}
