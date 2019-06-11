import { SubjectRealization } from '../subject/subject-realization.model';
import { Author } from './author.model';

export class TeachingMaterial {
	id:number;
	name:string;
	authors:Author[];
	publicationDate:Date;
	files:File[];
	subjectRealization:SubjectRealization;
}