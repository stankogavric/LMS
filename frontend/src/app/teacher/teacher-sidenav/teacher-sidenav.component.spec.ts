import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TeacherSidenavComponent } from './teacher-sidenav.component';

describe('TeacherSidenavComponent', () => {
  let component: TeacherSidenavComponent;
  let fixture: ComponentFixture<TeacherSidenavComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TeacherSidenavComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TeacherSidenavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
