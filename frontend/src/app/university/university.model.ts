import { Address } from '../address/address.model';
import { Teacher } from '../teacher/teacher.model';
import { Faculty } from '../faculty/faculty.model';
import { Phone } from '../phone/phone.model';
import { Email } from '../email/email.model';

export class University {
	id:number;
	name:string;
	dateOfEstablishment:Date;
	faculties:Faculty[];
	address:Address;
	rector:Teacher;
	description: string;
	phones: Phone[];
	emails: Email[];
}