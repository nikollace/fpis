import { Component, OnInit } from '@angular/core';
import { GoogleLoginProvider, SocialAuthService } from 'angularx-social-login';
import { UserService } from 'src/app/services/user.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable, Subject } from 'rxjs';

const TOKEN_KEY = 'jwt-token';
const helper = new JwtHelperService();

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  public user = new Subject();

  korisnik: any;

  constructor(private auth: UserService) {
    try {
      let token = helper.decodeToken(JSON.stringify(localStorage.getItem(TOKEN_KEY)));
      this.user = token;
      this.korisnik = token;
    } catch (e) {
      // handle the error, for eg.: 
      
    }
  }

  ngOnInit(): void {
  }

  signOut(): void {
    this.auth.signOut();
  }
}
