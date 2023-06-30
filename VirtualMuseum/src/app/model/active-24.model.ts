
import { Time } from "@angular/common";
import { User } from "./user.model";

export class Active24 {
    public idKorisnik: number;
    public datum: string;
    public vrijeme: string;
    public datumIsteka: string;
    public vrijemeIsteka: string;
 
     constructor(datum: string, vrijeme: string,idKorisnik: number, datumIsteka:string, vrijemeIsteka:string) {
         this.datum = datum;
         this.vrijeme = vrijeme;
         this.idKorisnik = idKorisnik;
         this.datumIsteka=datumIsteka;
         this.vrijemeIsteka=vrijemeIsteka;
     }
}
