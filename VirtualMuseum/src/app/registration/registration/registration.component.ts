import { Component, OnInit } from '@angular/core';
import { Form, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from 'src/app/model/user.model';
import { RegistrationService } from '../registration.service';
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
  template: `
    <h2>Single-slot content projection</h2>
    <ng-content></ng-content>
  `
})
export class RegistrationComponent implements OnInit {
  user!: User;
  public form: FormGroup = new FormGroup({});
  //private form: HTMLFormElement = document.querySelector('#myform') || undefined;
  constructor(private registrationService:RegistrationService,private route:ActivatedRoute, private router:Router,private formBuilder: FormBuilder) { }

  ngOnInit(): void {
      this.form = this.formBuilder.group({
      name: [null, Validators.required],
      surname: [null, Validators.required],
      username: [null, Validators.required],
      password: [null, Validators.required],
      passwordConfirmed: [null, Validators.required],
      email: [null, Validators.required]
    });
    this.getUsers();
  }
 
  addUser(form: any,error: any): void{
    if(!form.valid){
      error.value="Invalid form.";
    return;
    }
    this.user=new User(form.value.name,form.value.surname,form.value.username,form.value.password,form.value.email,0,0,0,"");
    if(form.value.name===null || form.value.surname===null || form.value.username===null || form.value.email===null || form.value.password===null || form.value.confirmedPassword===null) 
   {
    error.value="Fill in all fields.";
    return;
   }
    if(this.user.lozinka !== form.value.passwordConfirmed){
      error.value="Passwords do not match.";
      return;
    }
    console.log("called")
    this.registrationService.addUser(this.user,error);
   
    
   // this.router.navigate(["/korisnici_list"]);
  }
  getUsers(): void{
    this.registrationService.getUsers().subscribe((user:any) =>{console.log(user); this.user=user});
  }
  logForm(value: any) {
    console.log(value);
  }

}
