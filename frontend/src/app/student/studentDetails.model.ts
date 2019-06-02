import { StudentYear } from "./student-year.model";
import { SubjectAttendance } from "../subject/SubjectAttendance.model";

export class StudentDetails {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    profilePicturePath: string;
    studentYears: StudentYear[];
    subjectAttendances: SubjectAttendance[];
}