import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EventCreatorComponent } from './components/event-creator/event-creator.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationFormComponent } from './components/registration-form/registration-form.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { AuthGuardService } from './service/auth-guard.service';


const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'registrationForm', component: RegistrationFormComponent },
  { path: 'welcome', component: WelcomeComponent,canActivate:[AuthGuardService] },
  { path: 'createEvent', component: EventCreatorComponent, canActivate:[AuthGuardService] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
