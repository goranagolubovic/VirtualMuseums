import { HttpClient } from '@angular/common/http';
import { IfStmt } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user.model';
import { RegistrationComponent } from './registration/registration.component';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
 
  private observableArray: User[]=[];
  private users:Observable<User[]>;
  private activeUser: User | undefined;
  public async getUser(username: any, password: any): Promise<User | undefined>  {
    this.observableArray = await this.http.get<User[]>("http://localhost:8080/korisnici").toPromise() || []
    this.activeUser=this.observableArray.find(elem=>elem.korisnickoIme===username && elem.lozinka===password);
    return this.activeUser;
}
  constructor(private http:HttpClient) { }
  public addUser(user:User,error:any){
    fetch("http://localhost:8080/korisnici",{method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(user)
  }).then(responseData=>{
   return responseData.json()
  }).then(
    response=>
    {

      if(response==="OK"){
        error.value="You are registrated now.";
      }
      else{
        error.value="Credentials are reserved."
      }
     
    }
  );
  
  }
  /*public getUsers():Observable<User[]>{
       fetch("http://localhost:8080/korisnici").then((response: { json: () => any; })=>{
        return response.json()
      }
    ).then(responseData=>{
      this.users= responseData;
    })
    return this.users;
  }*/
  public getUsers():Observable<User[]>{
    return this.http.get<User[]>("http://localhost:8080/admin/registrovani");
  }
  
  public getActiveUsers(id:any):Observable<User[]>{
    return this.http.get<User[]>("http://localhost:8080/admin/aktivni?id="+id);
  }
  public updateUser(user:User){
    fetch("http://localhost:8080/korisnici",{method: 'PUT',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(user)
  });
  }
}
