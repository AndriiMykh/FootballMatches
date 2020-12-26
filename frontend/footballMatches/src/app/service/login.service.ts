import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public isLogged=new BehaviorSubject<boolean>(false);;
  constructor() { }
}
