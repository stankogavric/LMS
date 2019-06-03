import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentExamsComponent } from './student-exams.component';

describe('StudentExamsComponent', () => {
  let component: StudentExamsComponent;
  let fixture: ComponentFixture<StudentExamsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StudentExamsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentExamsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
