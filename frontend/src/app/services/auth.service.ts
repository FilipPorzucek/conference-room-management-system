import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { LoginResponse } from '../model/login-response';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8081/api/auth/login';

  constructor(private http: HttpClient) { }
   login(email: string, password: string): Observable<LoginResponse> {

    const body = {email,password};


   return this.http.post<LoginResponse>(this.apiUrl, body).pipe(
      tap(response => {
        if (response.token) {
          localStorage.setItem('token', response.token);
          console.log('Token zapisany:', response.token);
        }
      })
    );
  }

  logout() :void {
    localStorage.removeItem('token');
  }
}
