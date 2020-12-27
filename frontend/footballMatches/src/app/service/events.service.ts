import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import {Event} from '../common/event';
import {Team} from '../common/team';


@Injectable({
  providedIn: 'root'
})
export class EventsService {
  url = 'http://localhost:8080/api/events/';
  constructor(private http: HttpClient,private route:Router) { }
  getAllEvents():Observable<Event[]>{
    return this.http.get<Event[]>(this.url);
  }
  getAllTeams():Observable<Team[]>{
    return this.http.get<Team[]>(this.url+'getTeams');
  }
  createEvent(event:Event){
    return this.http.post(this.url,event).subscribe(
      isValid=>{
        alert('created')
      },
      error=>{
        console.log(error.error.message);
      }
    )
  }
}
