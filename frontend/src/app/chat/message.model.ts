import { File } from '../file/file.model';

export class Message {
	id:number;
	content:string;
	date:Date;
	recipient:string;
	sender:string;
	attachments:File[];

	constructor(content:string, date:Date, recipient: string, sender:string, attachments: File[]){
		this.content = content;
		this.date = date;
		this.recipient = recipient;
		this.sender = sender;
		this.attachments = attachments;
	}
}