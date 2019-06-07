import { TestBed } from '@angular/core/testing';

import { TeachingTermService } from './teaching-term.service';

describe('TeachingTermService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TeachingTermService = TestBed.get(TeachingTermService);
    expect(service).toBeTruthy();
  });
});
