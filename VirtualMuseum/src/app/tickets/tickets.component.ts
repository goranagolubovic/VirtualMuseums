import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { elementAt } from 'rxjs';
import { UserTicket } from '../model/user-ticket.model';
import { User } from '../model/user.model';
import { VirtualVisits } from '../model/virtual-visits.model';
import { TicketService } from './ticket.service';

@Component({
  selector: 'app-tickets',
  templateUrl: './tickets.component.html',
  styleUrls: ['./tickets.component.css']
})
export class TicketsComponent implements OnInit {
  public listOfTickets:VirtualVisits[]=[];
  public listOfTicketsOdd:VirtualVisits[]=[];
  public listOfTicketsEven:VirtualVisits[]=[];
  public listOfActiveVirtualVisits:VirtualVisits[]=[];
  private STORAGE_KEY:string="activeUser";
  private activeUser:User | undefined;
  public userId:number | undefined;
  constructor(private ticketService:TicketService,private router:Router) { }

   ngOnInit():void {
    this.activeUser=JSON.parse(localStorage.getItem("activeUser") || '{}');
    console.log(this.activeUser)
    this.userId=this.activeUser?.id;
    if(!this.activeUser?.isAdmin==false){
      this.router.navigate(["admin"])
    }
      if(localStorage.getItem("activeUser")==null){
     this.router.navigate([""])
      }
    var user=JSON.parse(localStorage.getItem(this.STORAGE_KEY) || '{}');
    console.log(this.userId);
    this.getTickets(this.userId);
    
    }
     public getTickets = async(id:any)=> {

    await fetch("http://localhost:8080/ticket?id="+id as string).then(response=>{
         return response.json()
       }
     ).then(responseData=>{
      var oddIndex=0;
      var evenIndex=0;
       this.listOfTickets= responseData.map((elem: any)=>elem.virtuelnaPosjeta);
       console.log(this.listOfTickets);
       for(var i=0;i<this.listOfTickets.length;i++){
        if(i%2!=0){
          this.listOfTicketsEven[evenIndex++]=this.listOfTickets[i];
        }
        else{
          this.listOfTicketsOdd[oddIndex++]=this.listOfTickets[i];
        }
       }
     })
   }
   public startVirtualTour(error:any,value:any){
    fetch("http://localhost:8080/virtual-visits?id="+this.activeUser?.id).then(responseData=>{
      return responseData.json();
    }).then(response=>{
        this.listOfActiveVirtualVisits=response;
        for(var i=0;i<this.listOfActiveVirtualVisits.length;i++){
          if(JSON.stringify(this.listOfActiveVirtualVisits[i])==JSON.stringify(value)){
            sessionStorage.setItem("VIRTUAL_PRESENTATION",JSON.stringify(value));
            this.router.navigate(['virtual-presentation']);
            return;
          }
        }
        error.value="The virtual tour is currently unvailable.";
    }

    )
   }
   }
   
