import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { isPlatformBrowser } from '@angular/common';
import { ApplyDTO } from '../model/applyDTO';
import { ViewDetailsDTO } from '../model/view-details-DTO';
import { environment } from '../../environments/envirronment';

@Injectable({
  providedIn: 'root'
})
export class ApplyService {

  private apiUrl = `${environment.apiBaseUrl}/applications`;

  constructor(
    private http: HttpClient,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  /** ✅ Helper to build JWT headers */
  private getAuthHeaders(): HttpHeaders {
    let headers = new HttpHeaders();
    if (isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem('authToken');
      if (token) {
        headers = headers.set('Authorization', `Bearer ${token}`);
      }
    }
    return headers;
  }

  /** ✅ Apply for a job */
  applyForJob(apply: any): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.http.post<any>(`${this.apiUrl}`, apply, { headers });
  }

  /** ✅ Get logged-in caregiver's applications */
  getMyApplications(): Observable<ApplyDTO[]> {
    const headers = this.getAuthHeaders();
    return this.http.get<ApplyDTO[]>(`${this.apiUrl}/my`, { headers });
  }

  /** ✅ Get all applicants for a specific job (Parent view) */
  getApplicationsForJob(jobId: number): Observable<ApplyDTO[]> {
    const headers = this.getAuthHeaders();
    return this.http.get<ApplyDTO[]>(`${this.apiUrl}/applicant/${jobId}`, { headers });
  }

  /** ✅ Get detailed applicant info (ViewDetailsDTO) for a job */
  getViewDetailsForJob(jobId: number): Observable<ViewDetailsDTO[]> {
    const headers = this.getAuthHeaders();
    return this.http.get<ViewDetailsDTO[]>(`${this.apiUrl}/applicant/${jobId}`, { headers });
  }

  /** ✅ Get single caregiver details by ID */
  getViewDetailsForCaregiver(caregiverId: number): Observable<ViewDetailsDTO> {
    const headers = this.getAuthHeaders();
    return this.http.get<ViewDetailsDTO>(`${this.apiUrl}/caregiver/${caregiverId}`, { headers });
  }

   /** ✅ Delete an application by ID */
  deleteApplication(applicationId: number): Observable<void> {
    const headers = this.getAuthHeaders();
    return this.http.delete<void>(`${this.apiUrl}/${applicationId}`, { headers });
  }
}
