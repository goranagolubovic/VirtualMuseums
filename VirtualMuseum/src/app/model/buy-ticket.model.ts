export class BuyTicket {
    public name: string;
    public surname: string;
    public  creditCardType: string;
    public creditCardNumber: string;
    public pin: string;
    public date: string;
    public ticketPrice: string;
 
     constructor(name: string, surname: string, creditCardType: string, creditCardNumber: string, pin: string, date: string,ticketPrice:string) {
         this.name = name;
         this.surname = surname;
         this.creditCardType = creditCardType;
         this.creditCardNumber=creditCardNumber;
         this.pin=pin;
         this.date=date;
         this.ticketPrice = ticketPrice;
     }
}
