import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { VirtualAction } from 'rxjs';
import { User } from '../model/user.model';
import { VirtualVisits } from '../model/virtual-visits.model';

@Component({
  selector: 'app-active-virtual-visits',
  templateUrl: './active-virtual-visits.component.html',
  styleUrls: ['./active-virtual-visits.component.css']
})
export class ActiveVirtualVisitsComponent implements OnInit {
  public activeVisits:VirtualVisits[]=[];
  constructor(private router:Router){}
  activeVisitsFormated: VirtualVisits[]=[];
  private activeUser:User | undefined;
  public userId:number | undefined;
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
    this.getActiveVisits();
    this.getFormated();
  
  }
  protected getFormated = async()=>{

  }
  public getActiveVisits = async()=> {
    
    await fetch("http://localhost:8080/virtual-visits/available-virtual-visits?id="+this.userId).then(response=>{
       return response.json()
     }
   ).then(responseData=>{
     this.activeVisits= responseData;
     for(var i=0;i<this.activeVisits.length;i++){
      var muzej=this.activeVisits[i].muzej;
      var datum=this.activeVisits[i].datum;
      //var krajFormatirano=new Date(this.activeVisits[i].kraj).toLocaleString('sr-SR');
      var vrijeme=this.activeVisits[i].vrijeme;
      var trajanje=this.activeVisits[i].trajanje;
      var trajanjeFormatirano=this.activeVisits[i].trajanje.split(":")[0];
      this.activeVisitsFormated[i]=new VirtualVisits(muzej,datum,trajanje,vrijeme,trajanjeFormatirano);
    }
   })
 }
}