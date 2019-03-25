import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LayeredTileComponent } from './layered-tile.component';

describe('LayeredTileComponent', () => {
  let component: LayeredTileComponent;
  let fixture: ComponentFixture<LayeredTileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LayeredTileComponent ]
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
