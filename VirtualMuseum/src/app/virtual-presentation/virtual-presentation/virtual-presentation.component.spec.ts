import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VirtualPresentationComponent } from './virtual-presentation.component';

describe('VirtualPresentationComponent', () => {
  let component: VirtualPresentationComponent;
  let fixture: ComponentFixture<VirtualPresentationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VirtualPresentationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VirtualPresentationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
