import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RESTService {

  constructor(private http: HttpClient) { }


  getMapImg(seed): Observable<Blob> {
    return this.http.get('rest/img?seed=' + seed, { responseType: 'blob' } );
  }

  getTileSetIDs(): Observable<number[]> {
    return this.http.get<number[]>('rest/getTileSetIDs' );
  }

  getMapArray(seed): Observable<string> {
    return this.http.get<string>('rest/getMapArray?seed=' + seed );
  }

  getTileImg(tileID: number): Observable<Blob> {
    return this.http.get('rest/getTileImg?tileID=' + tileID, { responseType: 'blob' } );
  }

  getTileSet(id: number): Observable<string> {
    return this.http.get<string>('rest/getTileSet?tileSetID=' + id );
  }

  uploadImage(image: File, tileSetID: number, tileIndex1: number): Observable<Response> {
    const uploadData = new FormData();
    uploadData.append('tileImg', image, image.name);
    return this.http.post<Response>('rest/postTileImg?tileSetID=' + tileSetID + '&tileIndex=' + tileIndex1, uploadData);
  }
}
