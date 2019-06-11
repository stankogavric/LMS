import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { TeachingMaterial } from '../teacher/teaching-material.model';

export class File {
	id:number;
	description:string;
	url:string;
	message:Message;
	teachingMaterial:TeachingMaterial;
}