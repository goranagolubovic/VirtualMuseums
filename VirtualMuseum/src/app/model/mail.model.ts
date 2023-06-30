import { ThisReceiver } from "@angular/compiler";
export class Mail {
    public emailFrom:string;
    public emailTo:string;
    public subject:string;
    public message:string;
    constructor(emailFrom:string,emailTo:string,subject:string,message:string){
        this.emailFrom=emailFrom;
        this.emailTo=emailTo;
        this.subject=subject;
        this.message=message;
    }
}
