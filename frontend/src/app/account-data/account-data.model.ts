export class AccountData {
	id:number;
	username:string;
	password:string;
	email:string;

	constructor(username:string){
		this.username = username;
		this.password = null;
		this.email = null;
	}
}