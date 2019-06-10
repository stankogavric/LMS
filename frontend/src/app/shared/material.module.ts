import { NgModule } from '@angular/core';
import { MatSortModule } from '@angular/material/sort';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatInputModule, MatCardModule, MatButtonModule, MatToolbarModule, MatExpansionModule, 
  MatProgressSpinnerModule, MatPaginatorModule, MatSidenavModule, MatTableModule, MatCheckboxModule, 
  MatIconModule, MatMenuModule, MatSelectModule, MatDatepickerModule, MatStepperModule, MatListModule, 
  MatNativeDateModule } from "@angular/material";
import { DragDropModule } from '@angular/cdk/drag-drop';
import { MatTabsModule } from '@angular/material/tabs';
import {MatSnackBarModule} from '@angular/material/snack-bar';

@NgModule({
  imports: [
    MatInputModule,
    MatCardModule,
    MatButtonModule,
    MatToolbarModule,
    MatExpansionModule,
    MatProgressSpinnerModule,
    MatPaginatorModule,
    MatSidenavModule,
    MatTableModule,
    MatGridListModule,
    MatCheckboxModule,
    MatIconModule,
    MatMenuModule,
    MatSelectModule,
    MatDatepickerModule,
    DragDropModule,
    MatStepperModule,
    MatListModule,
    MatNativeDateModule,
    MatTabsModule,
    MatSortModule,
    MatSnackBarModule
  ],
  exports: [
    MatInputModule,
    MatCardModule,
    MatButtonModule,
    MatToolbarModule,
    MatExpansionModule,
    MatProgressSpinnerModule,
    MatPaginatorModule,
    MatSidenavModule,
    MatTableModule,
    MatGridListModule,
    MatCheckboxModule,
    MatIconModule,
    MatMenuModule,
    MatSelectModule,
    MatDatepickerModule,
    DragDropModule,
    MatStepperModule,
    MatListModule,
    MatNativeDateModule,
    MatTabsModule,
    MatSortModule,
    MatSnackBarModule
  ]
})
export class MaterialModule {}