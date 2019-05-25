import { Exam } from '../exam/exam.model';

export class ExamTopic {
	id:number;
	description:String;
	exam:Exam;

	constructor(description:String, exam:Exam){
		this.description = description;
		this.exam = exam;
	}
}
