import { Address } from '../address/address.model';
import { AccountData } from '../account-data/account-data.model';
import { PersonalData } from '../personal-data/personal-data.model';

export class AdministrativeStaff {
	id:number;
	address:Address;
	accountData:AccountData;
	personalData:PersonalData;
}