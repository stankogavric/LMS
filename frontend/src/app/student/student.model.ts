import { Address } from '../address/address.model';
import { AccountData } from '../account-data/account-data.model';
import { PersonalData } from '../personal-data/personal-data.model';
import { StudentYear } from './student-year.model';

export class Student {
	id:number;
	accountData: AccountData;
	personalData: PersonalData;
	address: Address;
	// subjectAttendances:SubjectAttendance[];
	studentYears:StudentYear[];
}