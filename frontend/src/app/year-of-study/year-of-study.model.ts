import { StudyProgram } from '../study-program/study-program.model';
import { Subject } from '../subject/subject.model';

export class YearOfStudy {
	id:number;
	year:Date;
	subjects:Subject[];
	studyProgram:StudyProgram;
}