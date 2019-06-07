import { StudentYear } from "./student-year.model";
import { ExamDTO } from "../exam/examDTO.model";

export class StudentDetails {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    profilePicturePath: string;
    studentYears: StudentYear[];
}