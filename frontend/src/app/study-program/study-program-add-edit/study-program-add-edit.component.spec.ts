import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StudyProgramAddEditComponent } from './study-program-add-edit.component';

describe('StudyProgramAddEditComponent', () => {
  let component: StudyProgramAddEditComponent;
  let fixture: ComponentFixture<StudyProgramAddEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StudyProgramAddEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudyProgramAddEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
