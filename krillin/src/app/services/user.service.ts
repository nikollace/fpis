import { Injectable } from '@angular/core';
import { SocialAuthService } from 'angularx-social-login';
import { JwtHelperService } from '@auth0/angular-jwt';
import { HttpClient } from '@angular/common/http';

const TOKEN_KEY = 'jwt-token';
const helper = new JwtHelperService();

@Injectable({
  providedIn: 'root'
})
export class UserService {

  public user: any;
  private apiUrl: String;
  public loggedIn = false;

  constructor(private authService: SocialAuthService, private http: HttpClient) {

    this.apiUrl = 'http://localhost:8080/auth';

    try {
      const a = helper.decodeToken(JSON.stringify(localStorage.getItem(TOKEN_KEY)));
      if (a) {
        this.loggedIn = true;
        this.user = a;
      }
    } catch (error) {

    }

    this.authService.authState.subscribe((user) => {
      this.login(user.response.id_token).subscribe(res => {
        let token = res;
        localStorage.setItem(TOKEN_KEY, JSON.stringify(token));
      });

    });
  }

  login(id_token: String) {
    return this.http.get<any>(`${this.apiUrl}?id_token=${id_token}`);
  }

  signOut() {
    this.loggedIn = false;
    localStorage.clear();
  }
}
