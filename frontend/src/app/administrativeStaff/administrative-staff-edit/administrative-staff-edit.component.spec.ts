import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdministrativeStaffEditComponent } from './administrative-staff-edit.component';

describe('AdministrativeStaffEditComponent', () => {
  let component: AdministrativeStaffEditComponent;
  let fixture: ComponentFixture<AdministrativeStaffEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdministrativeStaffEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdministrativeStaffEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
