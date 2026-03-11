import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StaffDash } from './staff-dash';

describe('StaffDash', () => {
  let component: StaffDash;
  let fixture: ComponentFixture<StaffDash>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StaffDash],
    }).compileComponents();

    fixture = TestBed.createComponent(StaffDash);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
