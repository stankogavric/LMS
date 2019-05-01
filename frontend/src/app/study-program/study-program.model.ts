import { Teacher } from '../teacher/teacher.model';
import { Faculty } from '../faculty/faculty.model';
import { YearOfStudy } from '../year-of-study/year-of-study.model';

export class StudyProgram {
	id:number;
	name:string;
	yearsOfStudy:YearOfStudy[];
	headTeacher:Teacher;
	faculty:Faculty;
	description:string;
}