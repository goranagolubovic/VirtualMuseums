import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'login',
    loadChildren: () => import('./login/login.module').then(mod => mod.LoginModule)
  },
  {
    path:'',
    loadChildren: () => import('./home/home.module').then(mod => mod.HomeModule)
  },
  {
    path: 'registration',
    loadChildren: () => import('./registration/registration.module').then(mod => mod.RegistrationModule)
  },
  {
    path: 'korisnici_list',
    loadChildren: () => import('./registration/registration.module').then(mod => mod.RegistrationModule)
  },
  {
    path: 'museum',
    loadChildren: () => import('./museum/museum.module').then(mod => mod.MuseumModule)
  },
  {
      path: 'admin',
      loadChildren: () => import('./museum/museum.module').then(mod => mod.MuseumModule)
  },
  {
    path: 'details',
    loadChildren: () => import('./museum/museum.module').then(mod => mod.MuseumModule)
  }
]; 
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
