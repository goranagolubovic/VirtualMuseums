import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { AppMaterialModule } from './app-material/app-material.module';
import { BuyTicketComponent } from './buy-ticket/buy-ticket.component';
import { ReactiveFormsModule } from '@angular/forms';
import { PopupComponent } from './popup/popup.component';
import {AgmCoreModule} from '@agm/core';
import { AdminComponent } from './admin/admin.component';
import { ChartComponent } from './admin/chart/chart.component';
import { AddMuseumComponent } from './add-museum/add-museum.component';
import { VirtualTourPopupComponent } from './virtual-tour-popup/virtual-tour-popup.component';
import { FileUploadComponent } from './file-upload/file-upload.component';
import { VirtualPresentationComponent } from './virtual-presentation/virtual-presentation/virtual-presentation.component';
import { TicketsComponent } from './tickets/tickets.component';
import { VirtualVisits } from './model/virtual-visits.model';
import { LogsComponent } from './logs/logs.component';
import {SafePipeModule} from 'safe-pipe';
@NgModule({
  declarations: [
    AppComponent,
    BuyTicketComponent,
    PopupComponent,
    AdminComponent,
    ChartComponent,
    AddMuseumComponent,
    VirtualTourPopupComponent,
    FileUploadComponent,
    TicketsComponent,
    LogsComponent,
    VirtualPresentationComponent,
    LogsComponent,
  ],
  imports: [
    AgmCoreModule.forRoot({apiKey:'AIzaSyDTa-Nc2BDqzBAOmEY8TqpxR7uhOx8F1vQ'}),
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppMaterialModule,
    SafePipeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
