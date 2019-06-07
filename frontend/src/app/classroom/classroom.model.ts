import { Faculty } from '../faculty/faculty.model';

export class Classroom {
	id:number;
	name:String;
	type:String;
	capacity:number;
	faculty:Faculty;
}