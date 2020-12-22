import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegistrationFormComponent } from './components/registration-form/registration-form.component';


const routes: Routes = [
  { path: '', redirectTo: 'registrationForm', pathMatch: 'full' },
  { path: 'login',component:LoginComponent },
  { path: 'registrationForm', component: RegistrationFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
