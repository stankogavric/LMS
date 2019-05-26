import { Subject } from '../subject/subject.model';

export class Topic {
	id:number;
	description:String;
	week:number;
	iconPath: string;
	subject:Subject;

	constructor(description:String, week:number, subject:Subject, iconPath:string){
		this.description = description;
		this.week = week;
		this.iconPath = iconPath;
		this.subject = subject;
	}
}