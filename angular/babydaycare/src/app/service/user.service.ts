import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { isPlatformBrowser } from '@angular/common';
import { User } from '../model/userModel';
import { environment } from '../../environments/envirronment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = environment.apiBaseUrl + '/user/';

  constructor(
    private http: HttpClient,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  getAllParent(): Observable<User[]> {
    let headers = new HttpHeaders();

    if (isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem('authToken'); // ✅ match your key name
      if (token) {
        headers = headers.set('Authorization', 'Bearer ' + token);
      }
    }

    return this.http.get<User[]>(`${this.baseUrl}all`, { headers });
  }
}
