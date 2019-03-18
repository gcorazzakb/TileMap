import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RESTService {

  constructor(private http: HttpClient) { }

  getUpperCase(toUpper: String) : Observable<Object> {
    return this.http.get("http://localhost:8080/greeting?name=" + toUpper);
  }

  getImg() : Observable<Object> {
    return this.http.get("http://localhost:8080/img" );
  }
}
