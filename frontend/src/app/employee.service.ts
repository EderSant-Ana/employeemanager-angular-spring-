import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Employee } from './employee';
import { environment } from './../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private apiServerUrl = `${environment.apiUrl}/api/v1`;

  constructor(private http: HttpClient) { }

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
  }

  public getEmployeesList(): Observable<Employee[]>{
    return this.http.get<Employee[]>(`${this.apiServerUrl}/employees`, this.httpOptions)
      .pipe(
        catchError(this.handleError)
      )
  }

  public getEmployeeById(id: number): Observable<Employee>{
    return this.http.get<Employee>(`${this.apiServerUrl}/employees/${id}`, this.httpOptions)
      .pipe(
        catchError(this.handleError)
      )
  }

  public postEmployee(employee: Employee): Observable<Employee>{
    return this.http.post<Employee>(`${this.apiServerUrl}/employees/add`, employee, this.httpOptions)
    .pipe(
      catchError(this.handleError)
    );
  }

  public updateEmployee(employee: Employee): Observable<Employee>{
    return this.http.put<Employee>(`${this.apiServerUrl}/employees/update`, employee, this.httpOptions)
    .pipe(
      catchError(this.handleError)
    );
  }

  public deleteEmployee(id: number): Observable<void>{
    return this.http.delete<void>(`${this.apiServerUrl}/employees/delete/${id}`, this.httpOptions)
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse) {
    // Return an observable with a user-facing error message.
    return throwError(error);
  }

}
