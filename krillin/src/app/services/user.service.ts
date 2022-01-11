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

  constructor(private authService: SocialAuthService, private http: HttpClient) {

    this.apiUrl = 'http://localhost:8080/auth';

    this.authService.authState.subscribe((user) => {
      this.login(user.response.id_token).subscribe(res => {
        let token = res;
        this.user = helper.decodeToken(JSON.stringify(token.token));

        localStorage.setItem(TOKEN_KEY, JSON.stringify(token));
      });
      
    });
  }

  login(id_token: String) {
    return this.http.get<any>(`${this.apiUrl}?id_token=${id_token}`);
  }

  signOut() {
    localStorage.clear();
  }
}
