import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatTableDataSource, MatDialog } from '@angular/material';
import { AdministrativeStaff } from '../administrative-staff';
import { AdministrativeStaffService } from '../administrative-staff.service';
import { ConfirmationDialogComponent } from 'src/app/shared/confirmation-dialog/confirmation-dialog.component';
import { SnackBarService } from 'src/app/shared/snack-bar.service';

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

  constructor(private administrativeStaffService: AdministrativeStaffService, public dialog: MatDialog, private snackBarService: SnackBarService) {}

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.getAll();
  }

  getAll(){
    this.administrativeStaffService.getAll().subscribe((data: AdministrativeStaff[]) => {
      this.administrativeStaff = data;
      this.dataSource.data = data;
      this.dataSource.filterPredicate = function(data, filter): boolean {
        return data.personalData.firstName.toLowerCase().includes(filter) ||
                data.personalData.lastName.toLowerCase().includes(filter) || 
                data.personalData.personalNumber.toLowerCase().includes(filter) ||
                data.personalData.profilePicturePath.toLowerCase().includes(filter) || 
                data.address.city.country.name.toLowerCase().includes(filter) ||
                data.address.city.name.toLowerCase().includes(filter) || 
                data.address.street.toLowerCase().includes(filter) ||
                data.address.number.toLowerCase().includes(filter) || 
                data.accountData.email.toLowerCase().includes(filter) ||
                data.accountData.username.toLowerCase().includes(filter);
      };
    });
  }

  delete(id: String){
    this.administrativeStaffService.delete(id).subscribe((data: any) => {
      this.getAll();
      this.snackBarService.openSnackBar("Successfully deleted administrative staff", "X");
    });
  }

  update(id: string, administrativeStaffPerson: AdministrativeStaff, image: File){
    this.administrativeStaffService.update(id, administrativeStaffPerson, image).subscribe((data: any) => {
      this.getAll();
    });
  }

  openDialog(id: String): void {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '250px',
      data: {title: "Delete administrative staff", content: "Are you sure you want to delete this administrative staff?"}
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.delete(id);
      };
    });
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
