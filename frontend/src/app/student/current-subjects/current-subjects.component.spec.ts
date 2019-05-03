import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrentSubjectsComponent } from './current-subjects.component';

describe('CurrentSubjectsComponent', () => {
  let component: CurrentSubjectsComponent;
  let fixture: ComponentFixture<CurrentSubjectsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CurrentSubjectsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrentSubjectsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
