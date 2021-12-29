import { TestBed } from '@angular/core/testing';

import { NarudzbenicaService } from './narudzbenica.service';

describe('NarudzbenicaService', () => {
  let service: NarudzbenicaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NarudzbenicaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
