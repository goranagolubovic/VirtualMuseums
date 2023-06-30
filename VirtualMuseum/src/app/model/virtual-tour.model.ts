import { Time } from "@angular/common";
import { Museum } from "./museum.model";

export class VirtualTour {
    public muzejId:number;
    public datum:string;
    public vrijeme: string;
    public trajanje:string;
    public trajanjeFormatirano:string;
    constructor(muzejId:number, datum: string,vrijeme:string,trajanjeFormatirano:string) {
      this.muzejId=muzejId;
      this.datum=datum;
      this.vrijeme=vrijeme;
      this.trajanjeFormatirano=trajanjeFormatirano;
      this.trajanje=trajanjeFormatirano;
    }
}
