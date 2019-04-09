import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RESTService {

  constructor(private http: HttpClient) { }


  getMapImg(seed): Observable<Blob> {
    return this.http.get('http://localhost:8080/img?seed=' + seed, { responseType: 'blob' } );
  }

  getTileSetIDs(): Observable<number[]> {
    return this.http.get<number[]>('http://localhost:8080/getTileSetIDs' );
  }

  getMapArray(seed): Observable<string> {
    return this.http.get<string>('http://localhost:8080/getMapArray?seed=' + seed );
  }

  getTileImg(tileID: number): Observable<Blob> {
    return this.http.get('http://localhost:8080/getTileImg?tileID=' + tileID, { responseType: 'blob' } );
  }

  getTileSet(id: number): Observable<string> {
    return this.http.get<string>('http://localhost:8080/getTileSet?tileSetID=' + id );
  }
}
