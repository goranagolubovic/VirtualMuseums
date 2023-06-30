import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppMaterialModule } from '../app-material/app-material.module';
import { HomeRoutingModule } from '../home/home-routing/home-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HomeComponent } from './home/home.component';
import {MatIconModule} from '@angular/material/icon';
import { RegistrationModule } from '../registration/registration.module';
import {MatGridListModule} from '@angular/material/grid-list';
import { BrowserModule } from '@angular/platform-browser';
import { MuseumModule } from '../museum/museum.module';
@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    AppMaterialModule,
    FormsModule,
    ReactiveFormsModule,
    MatIconModule,
    RegistrationModule,
    MuseumModule,

  ]
})
export class HomeModule { }
