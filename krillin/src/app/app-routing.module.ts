import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { NarudzbenicaComponent } from './components/narudzbenica/narudzbenica.component';
import { RadnikComponent } from './components/radnik/radnik.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'radnik', component: RadnikComponent },
  { path: 'narudzbenica', component: NarudzbenicaComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
