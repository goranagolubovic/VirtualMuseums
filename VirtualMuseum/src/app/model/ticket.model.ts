import { Time } from "@angular/common";

export class Ticket {
    public korisnikId: number;
    public virtuelnaPosjetaMuzejId:number;
    public virtuelnaPosjetaDatum: String;
    public  virtuelnaPosjetaVrijeme: String;
    public virtuelnaPosjetaTrajanje: String;
    
 
     constructor(korisnikId: number,virtuelnaPosjetaMuzejId :number, virtuelnaPosjetaDatum: String, virtuelnaPosjetaVrijeme: String, virtuelnaPosjetaTrajanje: String) {
        this.korisnikId=korisnikId;
        this.virtuelnaPosjetaMuzejId=virtuelnaPosjetaMuzejId;
        this.virtuelnaPosjetaDatum=virtuelnaPosjetaDatum;
        this.virtuelnaPosjetaVrijeme=virtuelnaPosjetaVrijeme;
        this.virtuelnaPosjetaTrajanje=virtuelnaPosjetaTrajanje;
     }
 }
