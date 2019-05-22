import { Subject } from '../subject/subject.model';

export class Topic {
	id:number;
	description:String;
	week:number;
	subject:Subject;

	constructor(description:String, week:number, subject:Subject){
		this.description = description;
		this.week = week;
		this.subject = subject;
	}
}