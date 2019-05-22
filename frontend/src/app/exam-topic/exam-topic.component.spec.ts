import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExamTopicComponent } from './exam-topic.component';

describe('ExamTopicComponent', () => {
  let component: ExamTopicComponent;
  let fixture: ComponentFixture<ExamTopicComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExamTopicComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExamTopicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
