import { Component, OnInit } from '@angular/core';
import { PersonService } from '../../service/person.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(public personService:PersonService) { }
  login:string;
  password:string;
  ngOnInit(): void {
    
  }
  printValues(){
    console.log(this.login);
    console.log(this.password);
  }
  loginPerson(){
    this.personService.loginPerson(this.login,this.password);
  }
}
