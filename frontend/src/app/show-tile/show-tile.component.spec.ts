import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowTileComponent } from './show-tile.component';
import {CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {Routes} from '@angular/router';
import {AppRoutingModule} from '../app-routing.module';
import {RouterTestingModule} from '@angular/router/testing';
import {MapArrayComponent} from '../map-array/map-array.component';
import {ShowTilesetComponent} from '../show-tileset/show-tileset.component';
import {FormsModule} from '@angular/forms';
const routes: Routes = AppRoutingModule.routes;


describe('ShowTileComponent', () => {
  let component: ShowTileComponent;
  let fixture: ComponentFixture<ShowTileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        ShowTileComponent,
        MapArrayComponent,
        ShowTilesetComponent ],
      schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
      imports: [HttpClientModule, FormsModule, RouterTestingModule.withRoutes( routes)]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowTileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
