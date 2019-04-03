import {Component, ElementRef, Input, OnInit} from '@angular/core';

class Tile {
  constructor(id: number) {
    this.id = id;
  }

  id: number;
  // block
}

@Component({
  selector: 'app-layered-tile',
  templateUrl: './layered-tile.component.html',
  styleUrls: ['./layered-tile.component.css']
})
export class LayeredTileComponent implements OnInit {

  constructor() { }

  @Input() X: number;
  @Input() Y: number;
  @Input() map: any;

  // layers = [];

  ngOnInit() {
    // const layerAmount=this.map.length;
    console.log(this.X, this.Y);
    /*for (var i = 0; i < layerAmount; i++){
      let mapTile = this.map[i][this.X][this.Y];
      if (mapTile!= null){
        let tile = new Tile(mapTile.id);
        this.layers[i]=(tile);
      }
    }*/
  }

}
