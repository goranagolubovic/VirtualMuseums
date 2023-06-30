import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Museum } from '../model/museum.model';
import { User } from '../model/user.model';

@Component({
  selector: 'app-popup',
  templateUrl: './popup.component.html',
  styleUrls: ['./popup.component.css']
})
export class PopupComponent implements OnInit {

  constructor(private dialog:MatDialog,private formBuilder:FormBuilder) { }
  public form:FormGroup;
  private museum:Museum;
  public countriesItem:string[];
  public countriesCode:Map<string,string> = new Map<string,string>();
  public citiesItem:string[];
  public regionsItem:string[];
  public selectedCountry:string;
  private activeUser:User;
  ngOnInit(): void {
    this.activeUser=JSON.parse(localStorage.getItem("activeUser") || "");
    fetch("http://localhost:8080/admin/pronadjiDrzave")
    .then(response=> 
      { 
        console.log(response)
       return response.json()
      })
    .then(responseData=>{
      console.log(responseData)
    this.countriesItem=responseData.map((elem: any)=>elem.name.common)
    this.countriesCode=new Map<string,string>(
      responseData.map((elem: any)=>[elem.name.common,elem.cca2])
    )
  }
  )
    this.form = this.formBuilder.group({
      country: [null, Validators.required],
      city: [null, Validators.required],
      address: [null, Validators.required],
      telephone: [null, Validators.required],
      name: [null, Validators.required],
      type: [null, Validators.required],
      geolocation: [null, Validators.required],
      price: [null, Validators.required]
    });
  }
  addMuseum(form: any,error: any): void{
    console.log(form.value.name);
    console.log(form.value.city.item);
    this.museum=new Museum(0,form.value.name,form.value.address,form.value.telephone,form.value.city.item,form.value.country.item,form.value.geolocation,form.value.type,form.value.price);
    console.log(this.museum);
    fetch("http://localhost:8080/admin/dodajMuzej?id="+this.activeUser.id,{method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(this.museum)
  });
    error.value="Successfully saved.";
  }
  
  public close(){
    this.dialog.closeAll();
  }
  public fillRegions(event:any,item:any){
    this.selectedCountry=item;
    
    console.log(this.selectedCountry)
    let countryCode=this.countriesCode.get(item) ||  "";
    fetch("http://localhost:8080/admin/pronadjiRegione?"+ new URLSearchParams({
      countryCode:countryCode})).then(response=>{
        return response.json()
      }
    ).then(responseData=>{
      this.regionsItem= responseData;
      console.log(this.regionsItem)
    })
  }
  public fillCities(event:any,region:any){
    let countryCode=this.countriesCode.get(this.selectedCountry) ||  "";
    fetch("http://localhost:8080/admin/pronadjiGradove?"+ new URLSearchParams({
      region: region,countryCode:countryCode})).then(response=>{
        return response.json()
      }
    ).then(responseData=>{
      this.citiesItem= responseData;
    })
  }
}
