import { University } from '../university/university.model';
import { Address } from '../address/address.model';
import { Teacher } from '../teacher/teacher.model';

export class Faculty {
	id:number;
	name: String;
	university: University;
	address:Address;
    dean:Teacher;
    //studyPrograms: StudyProgram[];
}