import { Component, OnInit } from '@angular/core';

import { SocialAuthService, SocialUser } from "angularx-social-login";
import { GoogleLoginProvider } from "angularx-social-login";
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private authService: SocialAuthService, private auth: UserService) { }

  ngOnInit(): void {
  }

  signInWithGoogle(): void {
    this.auth.loggedIn = true;
    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID);
  }
}
