import { HttpClient, HttpParams} from '@angular/common/http';
import { Component, OnInit,ChangeDetectorRef } from '@angular/core';
import { Mail } from 'src/app/model/mail.model';
import { Museum } from 'src/app/model/museum.model';
import { User } from 'src/app/model/user.model';
import jspdf from 'jspdf';
import { Weather } from 'src/app/model/weather.model';
import { Router } from '@angular/router';
import * as mapboxgl from 'mapbox-gl';
import { environment } from 'src/environments/environment';
import { VirtualVisits } from 'src/app/model/virtual-visits.model';
import { Ticket } from 'src/app/model/ticket.model';
@Component({
  selector: 'app-museum-details',
  templateUrl: './museum-details.component.html',
  styleUrls: ['./museum-details.component.css']
})
export class MuseumDetailsComponent implements OnInit {
  map: mapboxgl.Map | undefined;
  style = 'mapbox://styles/mapbox/streets-v11';
  private lat :number;
  private lng:number;
  constructor(private router:Router) { }
  dataMuseum: any=[];
  dataUser:any=[];
  public weathersItem:Weather[]=[];
  STORAGE_KEY:string="museumDetails";
  ACTIVE_USER :string="activeUser";
  APP_MAIL:string="virtualmuseum@gmail.com";
  TICKET:string="ticket";
  MESSAGE:string="test";
  MIN:number=1;
  MAX:number=200000;
  public museumDetails:Museum;
  public museumsName:string="";
  public museumsAddress:string="";
  public museumsCountry:string="";
  public museumsCity:string="";
  public museumsTelNumber:string="";
  public museumsGeoLocation:string="";
  public museumsType:string="";
  public ticket:string="";
  public ticketNum:number=0;
  public activeVisits:VirtualVisits[];
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
     this.dataMuseum=JSON.parse(localStorage.getItem(this.STORAGE_KEY)  || '{}');
     this.museumDetails=this.dataMuseum[0];
     this.museumsName=this.museumDetails.naziv;
     this.museumsAddress=this.museumDetails.adresa;
     this.museumsCity=this.museumDetails.grad;
     this.museumsCountry=this.museumDetails.drzava;
     this.museumsTelNumber=this.museumDetails.brojTelefona;
     this.museumsGeoLocation=this.museumDetails.geolokacija;
     this.lat=+this.museumsGeoLocation.split(";")[0];
     this.lng=+this.museumsGeoLocation.split(";")[1];
     this.museumsType=this.museumDetails.tipMuzejaId;


  

     this.ticketNum=Math.floor(Math.random() * (this.MAX - this.MIN) + this.MIN);
     this.ticket="                                                     Ticket for vitual tour"+"\n\n\n\n"+"Serial number: "+this.ticketNum+"\n\n"+"Museum's name: "+this.museumsName+"\n"+"Address:"+this.museumsAddress+","+
     this.museumsCity+","+this.museumsCountry+"\n"+"Telephone: "+this.museumsTelNumber+
     "\n"+"Museum's type: "+this.museumsType;
     localStorage.setItem(this.TICKET,JSON.stringify(this.ticket));

     this.getWeather();

      this.map = new mapboxgl.Map({
        accessToken: environment.mapbox.accessToken,
        container: 'map',
        style: this.style,
        zoom: 13,
        center: [this.lng, this.lat]
    });
    // Add map controls
    this.map.addControl(new mapboxgl.NavigationControl());
  }
  public getWeather(){
    let params = new HttpParams().set("country", this.museumsCountry);
     fetch("http://localhost:8080/museum-details/weather?"+ new URLSearchParams({
      country: this.museumsCountry,})).then(response=>{
        return response.json()
      }
    ).then(responseData=>{
      this.weathersItem= responseData;
    })

  }
  public buyTicket(message:any){
    fetch("http://localhost:8080/virtual-visits/available-virtual-visits?id="+this.activeUser?.id).then(response=>{
      return response.json()
    }
  ).then(responseData=>{
    this.activeVisits= responseData;
    for(var i=0;i<this.activeVisits.length;i++){
     var muzejId=this.activeVisits[i].muzej.id;
     var datum=this.activeVisits[i].datum;
     //var krajFormatirano=new Date(this.activeVisits[i].kraj).toLocaleString('sr-SR');
     var vrijeme=this.activeVisits[i].vrijeme;
     var trajanje=this.activeVisits[i].trajanje;
     var trajanjeFormatirano=this.activeVisits[i].trajanje.split(":")[0];
     if(muzejId==this.museumDetails.id){
      var userId=JSON.parse(localStorage.getItem("activeUser") || '{}').id;
      var ticket=new Ticket(userId,muzejId,datum,vrijeme,trajanje);
      console.log("ticket");
      sessionStorage.setItem("CARD",JSON.stringify(ticket));
      this.router.navigate(['details/buy-ticket']);
     }
   }
  message.value="There is no available virtual tour for this museum.";
   
  })
    
  }

}




