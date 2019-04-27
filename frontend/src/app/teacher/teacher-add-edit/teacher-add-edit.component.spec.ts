import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TeacherAddEditComponent } from './teacher-add-edit.component';

describe('TeacherAddEditComponent', () => {
  let component: TeacherAddEditComponent;
  let fixture: ComponentFixture<TeacherAddEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TeacherAddEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TeacherAddEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
