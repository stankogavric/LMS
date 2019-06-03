import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClassScheduleAddEditComponent } from './class-schedule-add-edit.component';

describe('ClassScheduleAddEditComponent', () => {
  let component: ClassScheduleAddEditComponent;
  let fixture: ComponentFixture<ClassScheduleAddEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClassScheduleAddEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClassScheduleAddEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
