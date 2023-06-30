import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegistrationComponent } from 'src/app/registration/registration/registration.component';
import { RegistrationRoutingModule } from '../registration/registration-routing/registration-routing.module';
import { AppMaterialModule } from '../app-material/app-material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RegistrationListComponent } from './registration-list/registration-list.component';
@NgModule({
  declarations: [
    RegistrationComponent,
    RegistrationListComponent
  ],
  imports: [
    CommonModule,
    RegistrationRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    AppMaterialModule
  ]
})
export class RegistrationModule { }