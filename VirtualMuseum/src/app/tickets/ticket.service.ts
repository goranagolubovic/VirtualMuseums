import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ticket } from '../model/ticket.model';

@Injectable({
  providedIn: 'root'
})
export class TicketService {
  private listOfTickets:Ticket[]=[];
  constructor(private http:HttpClient) { }
  public getTicketsForUser(id:any):Observable<Ticket[]>{
    return this.http.get<Ticket[]>("http://localhost:8080/ticket?id="+id);
   /* fetch("http://localhost:8080/ticket?id="+id).then(responseData=>{
   return responseData.json()
  }).then(
    response=>
    {
     this.listOfTickets=response;
     return this.listOfTickets;
    }
  );
  return this.listOfTickets;
  }
}*/
  }
}
