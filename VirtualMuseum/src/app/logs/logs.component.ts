import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import jsPDF from 'jspdf';
import { User } from '../model/user.model';

@Component({
  selector: 'app-logs',
  templateUrl: './logs.component.html',
  styleUrls: ['./logs.component.css']
})
export class LogsComponent implements OnInit {
  private user:User | undefined;
  public userId:number | undefined;
  public fileContent:string[]=[];
  public downloadedFile:string="";
  constructor(private router:Router,private http:HttpClient) { }

  ngOnInit(): void {
    this.user=JSON.parse(localStorage.getItem("activeUser") || '{}');
    console.log(this.user)
    this.userId=this.user?.id;
    if(localStorage.getItem("activeUser")==null)
    this.router.navigate([""])
    if(!this.user?.isAdmin==true)
     this.router.navigate(["museum"])
     this.getFileContent();
  }
  public getFileContent = async()=> {
    fetch("http://localhost:8080/admin/logs?id="+this.user?.id).then(responseData=>{
      return responseData.json();
    }).then(response=>{
        this.fileContent=response;
    });
  
  }
  downloadLogs(){
    this.http.get("http://localhost:8080/admin/logs/download?id="+this.user?.id,{observe:'response',responseType:'blob'}).subscribe
    (async response=>{
      let blob:Blob=response.body as Blob
      let a=document.createElement('a');
      a.download="users.log";
      a.href=window.URL.createObjectURL(blob);
      a.click();
    });
  }
}
