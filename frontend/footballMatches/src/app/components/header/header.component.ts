import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../service/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  shotButtons:boolean=false;
  constructor(public loginService:LoginService) {}

  
  ngOnInit(): void {
    this.showButtons();
  }
  showButtons(){
    this.loginService.isLogged.subscribe(type=>{
      this.shotButtons=type;
    })
  }
  logout(){
    sessionStorage.removeItem('email');
    this.loginService.isLogged.next(false);
  }
}
