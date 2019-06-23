import { TestBed } from '@angular/core/testing';

import { PersonalDataService } from './personal-data.service';

describe('PersonalDataService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PersonalDataService = TestBed.get(PersonalDataService);
    expect(service).toBeTruthy();
  });
});
