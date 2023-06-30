import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Museum } from 'src/app/model/museum.model';
import { User } from 'src/app/model/user.model';
import { PopupComponent } from 'src/app/popup/popup.component';

@Component({
  selector: 'app-museum',
  templateUrl: './museum.component.html',
  styleUrls: ['./museum.component.css']
})
export class MuseumComponent implements OnInit {

  constructor(private dialog:MatDialog,private router:Router) { }
  private activeUser:User | undefined;
  public userId:number | undefined;
  ACTIVE_USER :string="activeUser";
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
  
  }
  public openPopup(){
    this.dialog.open(PopupComponent)
  }

}
