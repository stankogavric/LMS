import { Teacher } from './teacher.model';
import { SubjectRealization } from '../subject/subject-realization.model';
import { TeachingType } from './teaching-type.model';

export class TeacherRealization {
	id:number;
    numberOfClasses: number;
    teachingType:TeachingType;
    teacher: Teacher;
    subjectRealization: SubjectRealization;
}