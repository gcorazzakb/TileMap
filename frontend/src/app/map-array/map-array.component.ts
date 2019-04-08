import {Component, OnInit} from '@angular/core';
import {RESTService} from '../rest.service';
import {Util} from '../Util';

@Component({
  selector: 'app-map-array',
  templateUrl: './map-array.component.html',
  styleUrls: ['./map-array.component.css']
})
export class MapArrayComponent implements OnInit {
  mapArray: any[][][];

  private mapWidth: number;
  private mapHeight: number;
  private mapLayers: number;

  seed: number;
  zoom = 2;

  mapImg;

  showTile = {x: -1, y: 0, id: 0};


  constructor(private rest: RESTService) {
  }

  ngOnInit() {
  }

  loadMap(seed): void {
    this.seed = seed;
    this.getMapImg();
    this.getMapArray();
  }

  getMapArray(): void {
    this.rest.getMapArray(this.seed).subscribe(ma => {
      console.log(ma);
      this.mapArray = ma as any; // is it good? I mean.. no how to make it better?
      this.mapWidth = this.mapArray[0].length;
      this.mapHeight = this.mapArray[0][0].length;
      this.mapLayers = this.mapArray.length;
      // this.getImg(0,0,3);
    });
  }

  getMapImg(): void {
    this.rest.getMapImg(this.seed).subscribe(data => {
      const fileReader = Util.createImageFromBlob(data, () => {
        this.mapImg = fileReader.result;
      });
    }, error => {
      console.log(error);
    });
  }

  clickOnTile(event: MouseEvent): void {
    const id = +(event.target as Element).id;
    const x = id % this.mapWidth;
    const y = Math.floor(id / this.mapWidth);
    this.showTile.x = x;
    this.showTile.y = y;
    this.showTile.id = this.getHighestShowTileID();
    console.log(this.showTile);
  }

  zoomIn(): void {
    this.zoom++;
    if (this.zoom > 2) {
      this.zoom = 2;
    }
    this.setZoom();
  }

  zoomOut(): void {
    this.zoom--;
    if (this.zoom < 0) {
      this.zoom = 0;
    }
    this.setZoom();
  }

  setZoom(): void {
    const htmlElement = document.getElementById('zoomDiv');
    htmlElement.classList.forEach(value => htmlElement.classList.remove(value));
    if (this.zoom === 0) {
      htmlElement.classList.add('scaled1');
    } else if (this.zoom === 1) {
      htmlElement.classList.add('scaled2');
    } else {
      htmlElement.classList.add('scaled3');
    }
  }

  getHighestShowTileID(): number {
    const showTileIDs = this.getShowTileIDs();
    return showTileIDs[1];
  }

  getShowTileIDs(): number[] {
    const tiles: number[] = [];
    for (let i = 0; i < this.mapLayers; i++) {
      const id = this.mapArray[i][this.showTile.x][this.showTile.y];
      if ( id != null ) {
        tiles.push(id.id);
      }
    }
    console.log(tiles)
    return tiles;
  }
}
