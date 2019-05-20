import { Topic } from '../topic/topic.model';
import { SubjectRealization } from '../subject/subject-realization.model';
import { ExamType } from './exam-type.model';
import { ExamRealization } from './exam-realization.model';

export class Exam {
	id:number;
	startTime: Date;
	endTime: Date;
    points: number;
    subjectRealization: SubjectRealization;
    examType: ExamType;
    examRealizations: ExamRealization[];
    syllabus: Topic[];
}