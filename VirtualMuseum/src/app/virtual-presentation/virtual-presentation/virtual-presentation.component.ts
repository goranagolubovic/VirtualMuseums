import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user.model';
import { VirtualVisits } from 'src/app/model/virtual-visits.model';
@Component({
  selector: 'app-virtual-presentation',
  templateUrl: './virtual-presentation.component.html',
  styleUrls: ['./virtual-presentation.component.css']
})
export class VirtualPresentationComponent implements OnInit {
  public pictures:string[]=[];
  public video:string="";
  private virtualPresentation:VirtualVisits;
  private delay:number;
  private activeUser:User | undefined;
  public userId:number | undefined;
  constructor(private router:Router) { }
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
   this.virtualPresentation=JSON.parse(sessionStorage.getItem("VIRTUAL_PRESENTATION")|| "");
   this.getPresentation(this.virtualPresentation.muzej.naziv);
   this.getVideo(this.virtualPresentation.muzej.naziv);
   console.log(this.virtualPresentation.muzej.naziv);
   const timeGroup=this.virtualPresentation.trajanje.split(":");
   var delay = (+timeGroup[0]) * 60 * 60*1000 + (+timeGroup[1]) * 60*1000 + (+timeGroup[2])*1000; 
  
   setTimeout(() => {
    this.router.navigate(['museum']);
  }, delay);
  
   
  }

public getPresentation = async(virtualPresentation:any)=> {
  fetch("http://localhost:8080/korisnik?pictures="+virtualPresentation).then(responseData=>{
    return responseData.json();
  }).then(response=>{
    console.log(response);
      this.pictures=response as string[];
      console.log(this.pictures)
  });

}
public getVideo = async(virtualPresentation:any)=> {
  fetch("http://localhost:8080/korisnik/video?video="+virtualPresentation).then(responseData=>{
    return responseData.json();
  }).then(response=>{
    console.log(response);
      this.video=response as string;
     
  });

}
}

