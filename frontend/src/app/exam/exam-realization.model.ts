import { Exam } from './exam.model';
import { StudentYear } from '../student/student-year.model';

export class ExamRealization {
	id:number;
	points: number;
	note: String;
    exam: Exam;
    studentYear: StudentYear;
}