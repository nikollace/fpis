import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { UserService } from 'src/app/services/user.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { take, map } from 'rxjs/operators';
import { NavbarComponent } from '../components/navbar/navbar.component';

const TOKEN_KEY = 'jwt-token';
const helper = new JwtHelperService();

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  korisnik: any;
  loggedIn: any;

  constructor(private auth: UserService, private router: Router) {
    try {
      let token = helper.decodeToken(JSON.stringify(localStorage.getItem(TOKEN_KEY)));
      this.korisnik = token;
      this.loggedIn = (this.korisnik != null);
    } catch (e) {
      // handle the error, for eg.: 
    }
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    if (!this.korisnik) {
      if (state.url !== '/login') {
        this.router.navigate(['/login']);
        return false;
      }
      return true;
    } else {
      if (state.url === '/login') {
        this.router.navigate(['/home']);
      }
      return true;
    }
  }
}
