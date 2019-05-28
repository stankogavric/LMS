import { TestBed } from '@angular/core/testing';

import { SubjectRealizationService } from './subject-realization.service';

describe('SubjectRealizationService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SubjectRealizationService = TestBed.get(SubjectRealizationService);
    expect(service).toBeTruthy();
  });
});
