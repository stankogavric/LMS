import { YearOfStudy } from '../year-of-study/year-of-study.model';
import { Topic } from '../topic/topic.model';

export class Subject {
	id:number;
	name:string;
	ects:number;
	mandatory:boolean;
	lecturesNum:number;
	exercisesNum:number;
	otherActivitiesNum:number;
	researchPaper:number;
	otherClasses:number;
	syllabus:Topic[];
	prerequisite:Subject[];
	yearOfStudy:YearOfStudy;
}