import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EnterGradeComponent } from './enter-grade.component';

describe('EnterGradeComponent', () => {
  let component: EnterGradeComponent;
  let fixture: ComponentFixture<EnterGradeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EnterGradeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EnterGradeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
