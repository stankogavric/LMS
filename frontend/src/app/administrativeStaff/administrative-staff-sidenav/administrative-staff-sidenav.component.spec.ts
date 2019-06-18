import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdministrativeStaffSidenavComponent } from './administrative-staff-sidenav.component';

describe('AdministrativeStaffSidenavComponent', () => {
  let component: AdministrativeStaffSidenavComponent;
  let fixture: ComponentFixture<AdministrativeStaffSidenavComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdministrativeStaffSidenavComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdministrativeStaffSidenavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
