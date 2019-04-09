import {Component, OnInit} from '@angular/core';
import {RESTService} from '../rest.service';
import {Util} from '../Util';

@Component({
  selector: 'app-show-tileset',
  templateUrl: './show-tileset.component.html',
  styleUrls: ['./show-tileset.component.css']
})
export class ShowTilesetComponent implements OnInit {
  tileSetIDs: number[];
  tileset: any;
  tileImg: ArrayBuffer[] = [];

  constructor(private rest: RESTService) {
  }

  ngOnInit() {
    this.rest.getTileSetIDs().subscribe(ids => {
      this.tileSetIDs = ids;
    });
  }

  loadTileSet(id: number) {
    this.rest.getTileSet(id).subscribe(tilesetJSON => {
      this.tileset = tilesetJSON;


      const tiles = this.tileset.tiles;
      for (const i in tiles) {
        if ( tiles[i] != null) {
          this.rest.getTileImg(tiles[i].id).subscribe(blobImg => {
            const fl = Util.createImageFromBlob(blobImg,( ) => {
              this.tileImg[i] = fl.result;
            });
          });
        }
      }

    });
  }
}
