import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActiveVirtualVisitsComponent } from './active-virtual-visits.component';

describe('ActiveVirtualVisitsComponent', () => {
  let component: ActiveVirtualVisitsComponent;
  let fixture: ComponentFixture<ActiveVirtualVisitsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActiveVirtualVisitsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ActiveVirtualVisitsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
