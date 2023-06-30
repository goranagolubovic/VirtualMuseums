import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Active24 } from 'src/app/model/active-24.model';
import { User } from 'src/app/model/user.model';
import { RegistrationService } from 'src/app/registration/registration.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public form: FormGroup = new FormGroup({});
  private activeUser:User | undefined;
  ACTIVE_USER :string="activeUser";
  ACTIVE_USER24 :string="activeUser24";
  private items:any[];
  constructor(private registrationService:RegistrationService,private route:ActivatedRoute, private router:Router,private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      username: [null, Validators.required],
      password: [null, Validators.required]
    });
  }
    async login(form : any,error:any){
    this.activeUser=await this.registrationService.getUser(form.value.username,form.value.password);
    if(this.activeUser!=undefined){
      if(!this.activeUser.isAdmin && this.activeUser.isApproved){
        this.activeUser.isActive=1;
        this.registrationService.updateUser(this.activeUser);
      localStorage.setItem(this.ACTIVE_USER,JSON.stringify(this.activeUser));
      var today = new Date();
      var tomorrow = new Date(today);
      tomorrow.setDate(tomorrow.getDate() + 1);
      var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
      var dateExpire=tomorrow.getFullYear()+'-'+(tomorrow.getMonth()+1)+'-'+tomorrow.getDate();
      var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds().toPrecision(2);

      const id=this.activeUser.id || 0;
      var activeUser24=new Active24(date,time,id,dateExpire,time);
      fetch("http://localhost:8080/admin/aktivni24",{method: 'POST',
      body: JSON.stringify(activeUser24)
    });
      this.router.navigate(['museum']);
      }
      else if(!this.activeUser.isApproved){
        error.value="Your account isn't approved.";
      }
      else{
        localStorage.setItem(this.ACTIVE_USER,JSON.stringify(this.activeUser));
        sessionStorage.setItem("TOKEN",this.activeUser.token);
        this.router.navigate(['admin']);
      }
    }
    else{
      error.value="Check your credentials."
    }
  }
}
