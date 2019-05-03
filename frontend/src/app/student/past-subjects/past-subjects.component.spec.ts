import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PastSubjectsComponent } from './past-subjects.component';

describe('PastSubjectsComponent', () => {
  let component: PastSubjectsComponent;
  let fixture: ComponentFixture<PastSubjectsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PastSubjectsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PastSubjectsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
