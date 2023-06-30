import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Museum } from '../model/museum.model';
import { User } from '../model/user.model';
import { VirtualTour } from '../model/virtual-tour.model';

@Component({
  selector: 'app-virtual-tour-popup',
  templateUrl: './virtual-tour-popup.component.html',
  styleUrls: ['./virtual-tour-popup.component.css']
})
export class VirtualTourPopupComponent implements OnInit {


  constructor(private dialog:MatDialog,private formBuilder:FormBuilder) { }
  public form:FormGroup;
  private virtualTour:VirtualTour;
  private museum:Museum;
  public museumsItems:String[];
  private museumsName:string;
  private user:User;
  ngOnInit(): void {
    this.user=JSON.parse(localStorage.getItem("activeUser") || '{}');
    this.form = this.formBuilder.group({
      name: [null, Validators.required],
      date: [null, Validators.required],
      time: [null, Validators.required],
      duration: [null, Validators.required]
    });
    fetch("http://localhost:8080/museums")
    .then(response=> response.json())
    .then(responseData=>{
    this.museumsItems=responseData.map((elem: any)=>elem.naziv)}
    )
  }
  setMuseumName(event:any,value:any){
    this.museumsName=value;
  }
  async addVirtualTour(form: any,error: any): Promise<void>{
    var durationFormated=form.value.duration+":00:00";
    var userid=this.user.id as unknown as string;
    await fetch("http://localhost:8080/admin/pronadjiMuzej?"+ new URLSearchParams({
      name: this.museumsName,id:userid})).then(response=>{
        return response.json()
      }
    ).then(responseData=>{
      this.museum= responseData;
      this.virtualTour=new VirtualTour(this.museum.id,form.value.date,form.value.time,durationFormated);
      console.log(this.virtualTour);
    });
    
    fetch("http://localhost:8080/admin/dodajVirtuelnuPosjetu?id="+this.user.id,{method: 'POST',
    body: JSON.stringify(this.virtualTour)
  });
    error.value="Successfully saved.";
  }
  
  public close(){
    this.dialog.closeAll();
  }
  
}
