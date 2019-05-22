import { NgModule } from '@angular/core';

import {MatGridListModule} from '@angular/material/grid-list';
import { MatInputModule, MatCardModule, MatButtonModule, MatToolbarModule, MatExpansionModule, 
  MatProgressSpinnerModule, MatPaginatorModule, MatSidenavModule, MatTableModule, MatCheckboxModule, 
  MatIconModule, MatMenuModule, MatSelectModule, MatDatepickerModule
} from "@angular/material";
import { DragDropModule } from '@angular/cdk/drag-drop';

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
    DragDropModule
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
    DragDropModule
  ]
})
export class MaterialModule {}