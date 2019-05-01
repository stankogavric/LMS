import { University } from '../university/university.model';
import { Address } from '../address/address.model';
import { Teacher } from '../teacher/teacher.model';
import { Phone } from '../phone/phone.model';
import { Email } from '../email/email.model';
import { StudyProgram } from '../study-program/study-program.model';

export class Faculty {
	id:number;
	name: String;
	university: University;
	address:Address;
	dean:Teacher;
	description: string;
	phones: Phone[];
	emails: Email[];
    studyPrograms: StudyProgram[];
}