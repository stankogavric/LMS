import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentYearComponent } from './student-year.component';

describe('StudentYearComponent', () => {
  let component: StudentYearComponent;
  let fixture: ComponentFixture<StudentYearComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StudentYearComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentYearComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
