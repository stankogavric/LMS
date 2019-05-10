import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdministrativeStaffComponent } from './administrative-staff.component';

describe('AdministrativeStaffComponent', () => {
  let component: AdministrativeStaffComponent;
  let fixture: ComponentFixture<AdministrativeStaffComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdministrativeStaffComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdministrativeStaffComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
