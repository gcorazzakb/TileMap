import { TestBed } from '@angular/core/testing';

import { RESTService } from './rest.service';
import {HttpClientModule} from '@angular/common/http';

describe('RESTService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [
    HttpClientModule
  ]}));

  it('should be created', () => {
    const service: RESTService = TestBed.get(RESTService);
    expect(service).toBeTruthy();
  });

  // describe('ValueService', () => {
  //   let service: RESTService;
  //   beforeEach(() => { service = new RESTService(); });
  //
  //   it('#getValue should return real value', () => {
  //     expect(service.getValue()).toBe('real value');
  //   });
  //
  //   it('#getObservableValue should return value from observable',
  //     (done: DoneFn) => {
  //       service.getObservableValue().subscribe(value => {
  //         expect(value).toBe('observable value');
  //         done();
  //       });
  //     });
  //
  //   it('#getPromiseValue should return value from a promise',
  //     (done: DoneFn) => {
  //       service.getPromiseValue().then(value => {
  //         expect(value).toBe('promise value');
  //         done();
  //       });
  //     });
  // });

});
