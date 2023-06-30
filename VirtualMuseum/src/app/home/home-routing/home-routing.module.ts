import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from 'src/app/home/home/home.component';
import { MuseumDetailsComponent } from 'src/app/museum/museum-details/museum-details.component';
import { MuseumListComponent } from 'src/app/museum/museum-list/museum-list.component';
import { MuseumComponent } from 'src/app/museum/museum/museum.component';
import { CultureNewsComponent } from 'src/app/museum/culture-news/culture-news.component';
import { RegistrationComponent } from 'src/app/registration/registration/registration.component';
import { BuyTicketComponent } from 'src/app/buy-ticket/buy-ticket.component';
import { ActiveVirtualVisitsComponent } from 'src/app/active-virtual-visits/active-virtual-visits.component';
import { AdminComponent } from 'src/app/admin/admin.component';
import { FileUploadComponent } from 'src/app/file-upload/file-upload.component';
import { VirtualPresentationComponent } from 'src/app/virtual-presentation/virtual-presentation/virtual-presentation.component';
import { TicketsComponent } from 'src/app/tickets/tickets.component';
import { LogsComponent } from 'src/app/logs/logs.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'registration',
    component: RegistrationComponent
  },
  {
    path: 'museum',
    component: MuseumComponent
  },
  {
      path: 'admin',
      component: AdminComponent 
  },
  {
    path: 'museum-list',
    component: MuseumListComponent
  },
  {
    path: 'details',
    component: MuseumDetailsComponent
  },
  {
    path: 'culture-news',
    component:CultureNewsComponent
  },
  {
    path:'details/buy-ticket',
    component:BuyTicketComponent
  },
  {
    path: 'active-visits',
    component:ActiveVirtualVisitsComponent
    
  },
  {
    path:'virtual-presentation',
    component:VirtualPresentationComponent
  },
  {
    path:'all-tickets',
    component:TicketsComponent
  },
  {
      path:'admin/logs',
      component:LogsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }

