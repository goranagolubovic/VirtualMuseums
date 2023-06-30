import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { News } from 'src/app/model/news.model';
import { User } from 'src/app/model/user.model';

@Component({
  selector: 'app-culture-news',
  templateUrl: './culture-news.component.html',
  styleUrls: ['./culture-news.component.css']
})
export class CultureNewsComponent implements OnInit {

  public newsItems:News[]=[];
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
    this.getNews();
  }
  public getNews = async()=> {
    
    await fetch("http://localhost:8080/rssfeed").then(response=>{
       return response.json()
     }
   ).then(responseData=>{
     this.newsItems= responseData;
   })
   console.log(this.newsItems[2].pubDate);
 }

}
