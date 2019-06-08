export class StudentDissertationDTO {
    title: string;
    applicationDate: Date;
    defenseDate: Date;
    fileUrl: string;
    mentor: string;
    studyProgramName: string;

    constructor(title: string, applicationDate: Date, defenseDate: Date, 
        fileUrl: string, mentor: string, studyProgramName: string){
            this.title = title;
            this.applicationDate = applicationDate;
            this.defenseDate = defenseDate;
            this.fileUrl = fileUrl;
            this.mentor = mentor;
            this.studyProgramName = studyProgramName
        }
}