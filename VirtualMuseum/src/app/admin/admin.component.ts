import { JsonpClientBackend } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { FileUploadComponent } from '../file-upload/file-upload.component';
import { User } from '../model/user.model';
import { PopupComponent } from '../popup/popup.component';
import { RegistrationService } from '../registration/registration.service';
import { VirtualTourPopupComponent } from '../virtual-tour-popup/virtual-tour-popup.component';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  
  constructor(private registrationService:RegistrationService,private dialogRef:MatDialog, private router:Router) { }
  private activeUsersText="";
  public token:string;
  public activeUser:User[];
  public numberOfRegUsers:number=0;
  public numberOfActiveUsers:number=0;
  private user:User | undefined;
  public userId:number | undefined;
  ACTIVE_USER :string="activeUser";
  ngOnInit(): void {
    this.user=JSON.parse(localStorage.getItem("activeUser") || '{}');
   console.log(this.user)
   this.userId=this.user?.id;
   if(!this.user?.isAdmin==true){
    this.router.navigate(["museum"])
  }
    if(localStorage.getItem("activeUser")==null){
   this.router.navigate([""])
    }
    this.token=sessionStorage.getItem("TOKEN") || "";
    this.registrationService.getActiveUsers(this.user?.id)
      .subscribe((users: any) => {
        this.numberOfActiveUsers=users.length;
      });
    this.registrationService.getUsers()
      .subscribe((users: any) => {
        this.numberOfRegUsers=users.length;
      });
    }
    public openMuseumDialog(){
      this.dialogRef.open(PopupComponent);
    }
    public openVirtualTourDialog(){
      this.dialogRef.open(VirtualTourPopupComponent);
    }
    public createVirtualPresentation(){
     this.dialogRef.open(FileUploadComponent);
    }
}
