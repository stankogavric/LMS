import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisteredExamsComponent } from './registered-exams.component';

describe('RegisteredExamsComponent', () => {
  let component: RegisteredExamsComponent;
  let fixture: ComponentFixture<RegisteredExamsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisteredExamsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisteredExamsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
