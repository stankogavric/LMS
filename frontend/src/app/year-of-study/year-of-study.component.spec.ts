import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { YearOfStudyComponent } from './year-of-study.component';

describe('YearOfStudyComponent', () => {
  let component: YearOfStudyComponent;
  let fixture: ComponentFixture<YearOfStudyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ YearOfStudyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(YearOfStudyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
