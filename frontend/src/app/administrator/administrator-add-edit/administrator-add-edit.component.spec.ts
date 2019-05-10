import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdministratorAddEditComponent } from './administrator-add-edit.component';

describe('AdministratorAddEditComponent', () => {
  let component: AdministratorAddEditComponent;
  let fixture: ComponentFixture<AdministratorAddEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdministratorAddEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdministratorAddEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
