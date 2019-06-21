import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EnrollmentToTheNextYearComponent } from './enrollment-to-the-next-year.component';

describe('EnrollmentToTheNextYearComponent', () => {
  let component: EnrollmentToTheNextYearComponent;
  let fixture: ComponentFixture<EnrollmentToTheNextYearComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EnrollmentToTheNextYearComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EnrollmentToTheNextYearComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
