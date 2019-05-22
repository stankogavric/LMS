import { NgModule } from '@angular/core';

import {MatGridListModule} from '@angular/material/grid-list';
import { MatInputModule, MatCardModule, MatButtonModule, MatToolbarModule, MatExpansionModule, 
  MatProgressSpinnerModule, MatPaginatorModule, MatSidenavModule, MatTableModule, MatCheckboxModule, 
  MatIconModule, MatMenuModule, MatSelectModule, MatDatepickerModule, MatStepperModule
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
    DragDropModule,
    MatStepperModule
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
    MatStepperModule
  ]
})
export class MaterialModule {}