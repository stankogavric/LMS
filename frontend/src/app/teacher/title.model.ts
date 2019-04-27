import { Teacher } from './teacher.model';
import { ScientificField } from './scientific-field.model';

export class Title {
	id:number;
	startDate:Date;
	endDate:Date;
	scientificField:ScientificField;
	teacher:Teacher;
}