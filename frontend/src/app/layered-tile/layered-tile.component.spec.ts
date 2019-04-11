import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LayeredTileComponent } from './layered-tile.component';
import {Routes} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {MapArrayComponent} from '../map-array/map-array.component';
import {ShowTilesetComponent} from '../show-tileset/show-tileset.component';
import {ShowTileComponent} from '../show-tile/show-tile.component';
import {CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {AppRoutingModule} from '../app-routing.module';

describe('LayeredTileComponent', () => {
  let component: LayeredTileComponent;
  let fixture: ComponentFixture<LayeredTileComponent>;

  const routes: Routes = AppRoutingModule.routes;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        LayeredTileComponent,
        MapArrayComponent,
        ShowTilesetComponent,
        ShowTileComponent]
      ,
      schemas: [CUSTOM_ELEMENTS_SCHEMA],

      imports: [ RouterTestingModule.withRoutes( routes ),
        FormsModule]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LayeredTileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
