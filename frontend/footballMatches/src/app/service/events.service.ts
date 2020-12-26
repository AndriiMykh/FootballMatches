import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import {Event} from '../common/event'

@Injectable({
  providedIn: 'root'
})
export class EventsService {
  url = 'http://localhost:8080/api/events/';
  constructor(private http: HttpClient,private route:Router) { }
  getAllEvents():Observable<Event[]>{
    return this.http.get<Event[]>(this.url);
  }
}
