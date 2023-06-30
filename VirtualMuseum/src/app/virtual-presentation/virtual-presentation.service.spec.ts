import { TestBed } from '@angular/core/testing';

import { VirtualPresentationService } from './virtual-presentation.service';

describe('VirtualPresentationService', () => {
  let service: VirtualPresentationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VirtualPresentationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
