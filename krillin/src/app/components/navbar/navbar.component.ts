import { Component, OnInit } from '@angular/core';
import { GoogleLoginProvider, SocialAuthService } from 'angularx-social-login';
import { UserService } from 'src/app/services/user.service';
import { JwtHelperService } from '@auth0/angular-jwt';

const TOKEN_KEY = 'jwt-token';
const helper = new JwtHelperService();

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  user: any;

  constructor(private auth: UserService, private authService: SocialAuthService) {
    try {
      let token = helper.decodeToken(JSON.stringify(localStorage.getItem(TOKEN_KEY)));
      this.user = token;
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
