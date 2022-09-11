/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { ConfirmBookingService } from './ConfirmBooking.service';

describe('Service: ConfirmBooking', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ConfirmBookingService]
    });
  });

  it('should ...', inject([ConfirmBookingService], (service: ConfirmBookingService) => {
    expect(service).toBeTruthy();
  }));
});
