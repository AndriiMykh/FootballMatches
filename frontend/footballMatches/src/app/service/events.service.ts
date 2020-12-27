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
  secondURL='http://localhost:8080/api/persons/';
  public errorMessage:string = null;
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
        this.route.navigateByUrl('/welcome');
        this.errorMessage=null;
      },
      error=>{
        this.errorMessage=error.error.message;
      }
    )
  }
  subscribeToEvent(eventId:number){
    this.http.get(this.secondURL+`id/${sessionStorage.getItem('id')}/signPersonToEvent/event/${eventId}`).subscribe(
      isValid=>{
        console.log("ok")
      },
      error=>{
        console.error(error.error.message);
      }
    )
  }
  getPersonEvents(){
   return this.http.get<Event[]>(this.secondURL+sessionStorage.getItem('id')+'/events');
  }
}
