import { Component, OnInit } from '@angular/core';
import { EventsService } from '../../service/events.service';
import {Event} from '../../common/event';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  constructor(private eventService:EventsService,private route:ActivatedRoute) { }

  events:Event[];
  ngOnInit(): void {
    if(!this.route.snapshot.paramMap.has("id"))
      this.getAllEvents()
    if(this.route.snapshot.paramMap.has("id"))
      this.getPersonEvents()
  }
  getAllEvents(){
    this.eventService.getAllEvents().subscribe(
     data=>{
       this.events=data;
       console.log(this.events)
     }
    )
  }
  subscribeToEvent(eventId:number){
    console.log(sessionStorage.getItem('email'))
    this.eventService.subscribeToEvent(eventId)
  }
  getPersonEvents(){
    this.eventService.getPersonEvents().subscribe(
      data=>{
        this.events=data;
      }
    )
  }
}
