import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditTopicsComponent } from './add-edit-topics.component';

describe('AddEditTopicsComponent', () => {
  let component: AddEditTopicsComponent;
  let fixture: ComponentFixture<AddEditTopicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddEditTopicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEditTopicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
