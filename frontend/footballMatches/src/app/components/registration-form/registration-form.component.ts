import { Component, OnInit } from '@angular/core';
import{Person} from '../../common/person';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import {NotWhitespaces} from '../../common/not-whitespaces';
@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.css']
})
export class RegistrationFormComponent implements OnInit {

  constructor(private formBuilder: FormBuilder) { }
  checkoutFormGroup: FormGroup;
  person:Person;
  emailPattern= "^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";
  phoneNumberPattern='[0-9]{9}';
  ngOnInit(): void {
    this.checkoutFormGroup=this.formBuilder.group({
      person: this.formBuilder.group({
        name: new FormControl('', [Validators.required,Validators.minLength(4),NotWhitespaces.notWhitespaces]),
        email: new FormControl('', [Validators.required,Validators.pattern(this.emailPattern)]),
        phoneNumber: new FormControl('', [Validators.required,Validators.pattern(this.phoneNumberPattern)]),
        password: new FormControl('', [Validators.required,Validators.minLength(4)])
      })
    })
  }
  onSubmit(){
    if(this.checkoutFormGroup.invalid){
      this.checkoutFormGroup.markAllAsTouched();
    }
    console.log(this.checkoutFormGroup.get('person').value)
  }
  get email(){return this.checkoutFormGroup.get('person.email')}
  get password(){return this.checkoutFormGroup.get('person.password')}
  get name(){return this.checkoutFormGroup.get('person.name')}
  get phoneNumber(){return this.checkoutFormGroup.get('person.phoneNumber')}
}
