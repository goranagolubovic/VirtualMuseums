import { Time } from "@angular/common";
import { Museum } from "./museum.model";

export class VirtualVisits {
    public muzej:Museum;
    public datum:string;
    public vrijeme: string;
    public trajanje:string;
   // public pocetakFormatirano:string;
    public trajanjeFormatirano:string;
    constructor(muzej:Museum, datum: string, trajanje: string,vrijeme:string,trajanjeFormatirano:string) {
      this.muzej=muzej;
      this.datum=datum;
      this.trajanje=trajanje;
      this.vrijeme=vrijeme;
      this.trajanjeFormatirano=trajanjeFormatirano;
    }
}
