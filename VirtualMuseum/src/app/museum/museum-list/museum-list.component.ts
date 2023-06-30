import { Component, OnInit } from '@angular/core';
import {MatTableModule} from '@angular/material/table'
import { HttpClient } from '@angular/common/http';
import { MuseumService } from '../museum.service';
import { ActivatedRoute, Router } from '@angular/router';
import { VirtualVisits } from 'src/app/model/virtual-visits.model';
import { User } from 'src/app/model/user.model';

@Component({
  selector: 'app-museum-list',
  templateUrl: './museum-list.component.html',

  styleUrls: ['./museum-list.component.css']
})
export class MuseumListComponent implements OnInit {
  
  public museumsItems:string[]=[];
  public activeVisits:VirtualVisits[]=[];
  constructor(private museumService:MuseumService,private router:Router){}
  museumDetails: string=""
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
   this.getMuseums();
  }
  public getMuseums = async()=> {
    
    await fetch("http://localhost:8080/museums").then(response=>{
       return response.json()
     }
   ).then(responseData=>{
     this.museumsItems= responseData.map((elem: { naziv: any; })=>elem.naziv);
   })
 }
 public findMuseumWithSpecifiedName = async(nameOfMuseum:any)=> {
  await fetch("http://localhost:8080/museums").then(response=>{
    return response.json()
  }
).then(responseData=>{
  this.museumsItems= responseData.map((elem: { naziv: any; })=>elem.naziv).filter((e: any)=>e === nameOfMuseum.value);
})
 }
 public findMuseumsInSpecifiedCity = async(city:any)=> {
  await fetch("http://localhost:8080/museums").then(response=>{
    return response.json()
  }
).then(responseData=>{
  this.museumsItems= responseData.filter((e: any)=>e.grad === city.value).map((elem: { naziv: any; })=>elem.naziv);
})
 }
 public viewDetails= async(item:any)=>{
  await fetch("http://localhost:8080/museums").then(response=>{
    return response.json()
  }
).then(responseData=>{
   this.museumDetails= responseData.filter((e: any)=>e.naziv === item);
    
})
localStorage.setItem("museumDetails",JSON.stringify(this.museumDetails));
  this.router.navigate(['details']);
 }

}
