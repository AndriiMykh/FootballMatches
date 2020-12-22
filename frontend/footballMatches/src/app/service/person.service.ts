import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import{Person} from '../common/person';
import { Router } from '@angular/router';
@Injectable({
  providedIn: 'root'
})
export class PersonService {
  url = 'http://localhost:8080/api/persons/'
  constructor(private http: HttpClient,private route:Router) { }
  savePerson(person:Person){
    this.http.post(this.url,person).subscribe(
      isValid=>{
        console.log(isValid)
        this.route.navigateByUrl('/login')
      },
      error=>{
        alert(error.error.message)
      }
    )
  }
}
