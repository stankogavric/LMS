import { Subject } from './subject.model';
import { Exam } from '../exam/exam.model';
import { YearOfStudy } from '../year-of-study/year-of-study.model';
import { TeacherRealization } from '../teacher/teacher-realization.model';

export class SubjectRealization {
	id:number;
	teacherRealizations:TeacherRealization[];
	//teachingTerms: TeachingTerms[];
	exams: Exam[];
	subject:Subject;
	//teachingMaterials: TeachingMaterial[];
	yearOfStudy: YearOfStudy;
}