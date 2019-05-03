import { TestBed } from '@angular/core/testing';

import { SubjectAttendanceService } from './subject-attendance.service';

describe('SubjectAttendanceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SubjectAttendanceService = TestBed.get(SubjectAttendanceService);
    expect(service).toBeTruthy();
  });
});
