import { TestBed } from '@angular/core/testing';

import { StavkaNarudzbeniceService } from './stavka-narudzbenice.service';

describe('StavkaNarudzbeniceService', () => {
  let service: StavkaNarudzbeniceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StavkaNarudzbeniceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
