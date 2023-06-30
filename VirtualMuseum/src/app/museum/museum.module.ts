import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MuseumComponent } from './museum/museum.component';
import { AppMaterialModule } from '../app-material/app-material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MuseumListComponent } from './museum-list/museum-list.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { MuseumDetailsComponent } from './museum-details/museum-details.component';
import { CultureNewsComponent } from './culture-news/culture-news.component';
import { ActiveVirtualVisitsComponent } from '../active-virtual-visits/active-virtual-visits.component';


@NgModule({
  declarations: [
    MuseumComponent,
    MuseumListComponent,
    MuseumDetailsComponent,
    CultureNewsComponent,
    ActiveVirtualVisitsComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    AppMaterialModule,
    MatGridListModule
  ]
})
export class MuseumModule { }
