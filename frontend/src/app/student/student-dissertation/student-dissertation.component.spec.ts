import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentDissertationComponent } from './student-dissertation.component';

describe('StudentDissertationComponent', () => {
  let component: StudentDissertationComponent;
  let fixture: ComponentFixture<StudentDissertationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StudentDissertationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentDissertationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
