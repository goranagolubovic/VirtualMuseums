import { Injectable, NgModule } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Museum } from '../model/museum.model';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class MuseumService {
constructor(private http:HttpClient) { }
}
