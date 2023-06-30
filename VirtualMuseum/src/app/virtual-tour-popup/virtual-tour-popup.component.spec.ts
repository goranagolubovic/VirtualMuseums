import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VirtualTourPopupComponent } from './virtual-tour-popup.component';

describe('VirtualTourPopupComponent', () => {
  let component: VirtualTourPopupComponent;
  let fixture: ComponentFixture<VirtualTourPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VirtualTourPopupComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VirtualTourPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
