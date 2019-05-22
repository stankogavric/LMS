import { SubjectRealization } from '../subject/subject-realization.model';
import { ExamType } from './exam-type.model';
import { ExamRealization } from './exam-realization.model';
import { ExamTopic } from '../exam-topic/exam-topic.model';

export class Exam {
	id:number;
	startTime: Date;
	endTime: Date;
    points: number;
    durationInMinutes: number;
    subjectRealization: SubjectRealization;
    examType: ExamType;
    examRealizations: ExamRealization[];
    syllabus: ExamTopic[];
}