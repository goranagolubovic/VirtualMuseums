import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user.model';
import { RegistrationService } from '../registration.service';

@Component({
  selector: 'app-registration-list',
  templateUrl: './registration-list.component.html',
  styleUrls: ['./registration-list.component.css']
})
export class RegistrationListComponent implements OnInit {
  users!: User[];
  constructor(private registrationService:RegistrationService) { }

  ngOnInit(): void {
    this.getUsers();
  }
  getUsers(): void {
    this.registrationService.getUsers().subscribe((users:any) => this.users = users);
  }
}
