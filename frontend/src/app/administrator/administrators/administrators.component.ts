import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { Administrator } from '../administrator.model';
import { AdministratorService } from '../administrator.service';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from '../../shared/confirmation-dialog/confirmation-dialog.component';
import { SnackBarService } from 'src/app/shared/snack-bar.service';

@Component({
  selector: 'app-administrators',
  templateUrl: './administrators.component.html',
  styleUrls: ['./administrators.component.css']
})
export class AdministratorsComponent implements OnInit {

  administrators : Administrator[] = [];
  administrator : Administrator = new Administrator();
  displayedColumns: string[] = ['no', 'email', 'username', 'actions'];
  dataSource = new MatTableDataSource<Administrator>(this.administrators);

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private administratorService: AdministratorService, public dialog: MatDialog, private snackBarService: SnackBarService) {}

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.getAll();
  }

  getAll(){
    this.administratorService.getAll().subscribe((data: Administrator[]) => {
      this.administrators = data;
      this.dataSource.data = data;
      this.dataSource.filterPredicate = function(data, filter): boolean {
        return data.accountData.email.toLowerCase().includes(filter) || 
                data.accountData.username.toLowerCase().includes(filter);
      };
    });
  }

  delete(id: String){
    this.administratorService.delete(id).subscribe((data: any) => {
      this.getAll();
      this.snackBarService.openSnackBar("Successfully deleted administrator", "X")
    });
  }

  update(id: string, administrator: Administrator){
    this.administratorService.update(id, administrator).subscribe((data: any) => {
      this.getAll();
    });
  }

  openDialog(id: String): void {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '250px',
      data: {title: "Delete administrator", content: "Are you sure you want to delete this administrator?"}
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
