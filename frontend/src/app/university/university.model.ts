import { Address } from '../address/address.model';
import { Teacher } from '../teacher/teacher.model';
import { Faculty } from '../faculty/faculty.model';

export class University {
	id:string;
	name:string;
	dateOfEstablishment:Date;
	faculties:Faculty[];
	address:Address;
	rector:Teacher;
}