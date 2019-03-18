import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RestButtonComponent } from './rest-button.component';

describe('RestButtonComponent', () => {
  let component: RestButtonComponent;
  let fixture: ComponentFixture<RestButtonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RestButtonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RestButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
