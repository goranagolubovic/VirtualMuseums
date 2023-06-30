import { Time } from "@angular/common";

export class UserTicket {
    public korisnikId: number;
    public virtuelnaPosjetaMuzejId:number;
    public virtuelnaPosjetaDatum: String;
    public  virtuelnaPosjetaVrijeme: String;
    public virtuelnaPosjetaTrajanje: String;
    public nazivPosjete:string;
    
 
     constructor(korisnikId: number,virtuelnaPosjetaMuzejId :number, virtuelnaPosjetaDatum: String, virtuelnaPosjetaVrijeme: String, virtuelnaPosjetaTrajanje: String,nazivPosjete:string) {
        this.korisnikId=korisnikId;
        this.virtuelnaPosjetaMuzejId=virtuelnaPosjetaMuzejId;
        this.virtuelnaPosjetaDatum=virtuelnaPosjetaDatum;
        this.virtuelnaPosjetaVrijeme=virtuelnaPosjetaVrijeme;
        this.virtuelnaPosjetaTrajanje=virtuelnaPosjetaTrajanje;
        this.nazivPosjete=nazivPosjete;
     }
 }
