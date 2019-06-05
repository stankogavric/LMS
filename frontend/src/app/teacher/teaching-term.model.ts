import { SubjectRealization } from '../subject/subject-realization.model';
import { Classroom } from '../classroom/classroom.model';

export class TeachingTerm {
	id:number;
	day:string;
	startTime:Date;
	endTime:Date;
	subjectRealization:SubjectRealization;
	classroom:Classroom;

	constructor(day:string, startTime:Date, endTime:Date, subjectRealization:SubjectRealization, classroom:Classroom){
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
		this.subjectRealization = subjectRealization;
		this.classroom = classroom;
	}
}