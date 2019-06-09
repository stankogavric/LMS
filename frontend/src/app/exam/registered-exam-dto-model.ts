export class RegisteredExamDTO{
    registrationNum: number;
    examType: string;
    startTime: Date;
    endTime: Date;
    subject: string;

    constructor(registrationNum: number, examType: string, startTime: Date, endTime: Date, subject: string){
        this.registrationNum = registrationNum;
        this.examType = examType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.subject = subject;
    }

}