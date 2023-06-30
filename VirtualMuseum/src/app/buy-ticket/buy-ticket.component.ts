import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BuyTicket } from '../model/buy-ticket.model';
import { Mail } from '../model/mail.model';
import { Museum } from '../model/museum.model';
import { User } from '../model/user.model';
import { Ticket } from '../model/ticket.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-buy-ticket',
  templateUrl: './buy-ticket.component.html',
  styleUrls: ['./buy-ticket.component.css']
})
export class BuyTicketComponent implements OnInit {
  public form: FormGroup = new FormGroup({});
  SUBJECT:string="You have a new ticket";
  MESSAGE:string="test";
  TICKET:string="ticket";
  TICKET_PRICE:string="price";
  private entryCard:Ticket;
  SUCCESSFULLY_ACTION_MSG:string="Successfully buyed.";

  public ticket:string="";
  public museumTemp:Museum | undefined;
  public museum:Museum[]=[];

  public creditCardTypes:string[]=["VISA","MASTERCARD","AMERICAN EXPRESS"];
  private activeUser:User | undefined;
  ACTIVE_USER :string="activeUser";
  MUSEUM:string="museumDetails";
  price:string="";
  result:string="";
  public userId:number | undefined;
  constructor(private formBuilder: FormBuilder,private router:Router) { }

  ngOnInit(): void {
    
    this.activeUser=JSON.parse(localStorage.getItem("activeUser") || '{}');
    console.log(this.activeUser)
    this.userId=this.activeUser?.id;
    if(!this.activeUser?.isAdmin==false){
      this.router.navigate(["admin"])
    }
      if(localStorage.getItem("activeUser")==null){
     this.router.navigate([""])
      }
    this.ticket=JSON.parse(localStorage.getItem(this.TICKET)   || '{}');
    this.museum=JSON.parse(localStorage.getItem(this.MUSEUM)   || '{}');
    this.entryCard=JSON.parse(sessionStorage.getItem("CARD") || "");
    this.form = this.formBuilder.group({
      name: [null, Validators.required],
      surname: [null, Validators.required],
      creditCardNumber: [null, Validators.required],
      creditCardType: [null, Validators.required],
      validUntil: [null, Validators.required],
      PIN: [null, Validators.required]
    });

  }
  
  private sendTicketToUser(user:any){
    var email=new Mail(user.email,user.email,this.SUBJECT,this.ticket);

    fetch("http://localhost:8080/sendEmail",{method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(email)
  });
  }
  public async  tryToBuyATicket(message:any) {
    console.log(this.form.value.type);
    let body=new BuyTicket(this.form.value.name,this.form.value.surname,this.form.value.creditCardType,this.form.value.creditCardNumber,
      this.form.value.PIN,this.form.value.validUntil,this.museum[0].cijenaUlaznice.toString());
      console.log(JSON.stringify(body));
    fetch("http://localhost:8080/virtual-bank?id="+this.activeUser?.id,{method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(body)
  }).then(response=>{
    return response.text()
  }
).then(responseData=>{
  this.result= responseData;
  message.value=this.result;
    if(this.result===this.SUCCESSFULLY_ACTION_MSG){
      console.log("String are equal")
    this.addTicketToDatabase(this.entryCard);
    this.sendTicketToUser(this.activeUser);
    }
})

  }

  private addTicketToDatabase(ticket:Ticket){
    fetch("http://localhost:8080/ticket",{method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(ticket),
  });
  }
 /* public  buyTicket(message:any){
    this.tryToBuyATicket();
    console.log(this.result)
    message.value=this.result;
    if(this.result===(this.SUCCESSFULLY_ACTION_MSG))
    this.sendTicketToUser(this.activeUser);
    
  }*/

}
