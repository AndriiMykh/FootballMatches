import { Component, OnInit } from '@angular/core';
import { EventsService } from '../../service/events.service';
import {Event} from '../../common/event';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  constructor(private eventService:EventsService) { }

  events:Event[];
  ngOnInit(): void {
    this.getAllEvents()
  }
  getAllEvents(){
    this.eventService.getAllEvents().subscribe(
     data=>{
       this.events=data;
       console.log(this.events)
     }
    )
  }

}
