import { TestBed } from '@angular/core/testing';

import { ExamTopicService } from './exam-topic.service';

describe('ExamTopicService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ExamTopicService = TestBed.get(ExamTopicService);
    expect(service).toBeTruthy();
  });
});
