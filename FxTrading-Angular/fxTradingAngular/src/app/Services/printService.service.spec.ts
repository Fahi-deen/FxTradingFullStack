/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { PrintServiceService } from './printService.service';

describe('Service: PrintService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PrintServiceService]
    });
  });

  it('should ...', inject([PrintServiceService], (service: PrintServiceService) => {
    expect(service).toBeTruthy();
  }));
});
