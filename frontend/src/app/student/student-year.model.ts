import { Student } from './student.model';
import { YearOfStudy } from '../year-of-study/year-of-study.model';
import { ExamRealization } from '../exam/exam-realization.model';

export class StudentYear {
	id:string;
	enrolmentDate:Date;
	numIndex:string;
	yearOfStudy:YearOfStudy;
    student:Student;
}