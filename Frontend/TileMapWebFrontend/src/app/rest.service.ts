import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Util} from "./Util";

@Injectable({
  providedIn: 'root'
})
export class RESTService {

  constructor(private http: HttpClient) { }


  getMapImg(seed) : Observable<Blob> {
    return this.http.get("http://localhost:8080/img?seed="+seed, { responseType: 'blob' } );
  }

  getTileSetNames() : Observable<string> {
    return this.http.get<string>("http://localhost:8080/getTileSetNames" );
  }

  getMapArray(seed): Observable<string>{
    return this.http.get<string>("http://localhost:8080/getMapArray?seed="+seed );
  }

  getTileImg(tileID: number) : Observable<Blob>{
    return this.http.get("http://localhost:8080/getTileImg?tileID=" + tileID, { responseType: 'blob' } );
  }
}
