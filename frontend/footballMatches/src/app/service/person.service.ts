import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import{Person} from '../common/person';
import { Router } from '@angular/router';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class PersonService {
  url = 'http://localhost:8080/api/persons/';
  public errorMessage:string = null;
  constructor(private http: HttpClient,private route:Router,private loginService:LoginService) { }
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
  loginPerson(login:string,password:string){
    let params = new HttpParams().set('login', login).set('password',password);
    this.http.post<Person>(this.url+'login/',params).subscribe(
       data=>{
        console.log("Logged in")
        console.log(data.id)
        this.errorMessage=null
        this.route.navigateByUrl('/welcome')
        this.loginService.isLogged.next(true);
        sessionStorage.setItem('email',login);
        sessionStorage.setItem('id',data.id.toString());
      },
      error=>{
        this.errorMessage=error.error.message;
      }
    )
  }
}
