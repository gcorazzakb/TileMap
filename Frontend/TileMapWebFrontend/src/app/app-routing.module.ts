import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ShowMapComponent} from "./show-map/show-map.component";
import {ShowTilesetComponent} from "./show-tileset/show-tileset.component";
import {ShowTileComponent} from "./show-tile/show-tile.component";

const routes: Routes = [
  { path: '', redirectTo: '/showMap', pathMatch: 'full'},
  { path: 'showMap', component: ShowMapComponent },
  { path: 'showTileset', component: ShowTilesetComponent},
  { path: 'showTile', component: ShowTileComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
