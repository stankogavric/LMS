import { TestBed } from '@angular/core/testing';

import { TeachingMaterialService } from './teaching-material.service';

describe('TeachingMaterialService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TeachingMaterialService = TestBed.get(TeachingMaterialService);
    expect(service).toBeTruthy();
  });
});
