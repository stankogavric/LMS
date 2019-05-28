import { Address } from '../address/address.model';
import { Title } from './title.model'
import { AccountData } from '../account-data/account-data.model';
import { PersonalData } from '../personal-data/personal-data.model';

export class Teacher {
	id:number;
	accountData: AccountData;
	personalData: PersonalData;
	address: Address;
	biography:string;
	titles:Title[];
}