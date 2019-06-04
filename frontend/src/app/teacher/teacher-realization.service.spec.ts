import { TestBed } from '@angular/core/testing';

import { TeacherRealizationService } from './teacher-realization.service';

describe('TeacherRealizationService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TeacherRealizationService = TestBed.get(TeacherRealizationService);
    expect(service).toBeTruthy();
  });
});
