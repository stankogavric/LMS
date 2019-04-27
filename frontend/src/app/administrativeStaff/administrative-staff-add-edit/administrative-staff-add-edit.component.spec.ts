import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdministrativeStaffAddEditComponent } from './administrative-staff-add-edit.component';

describe('AdministrativeStaffAddEditComponent', () => {
  let component: AdministrativeStaffAddEditComponent;
  let fixture: ComponentFixture<AdministrativeStaffAddEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdministrativeStaffAddEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdministrativeStaffAddEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
