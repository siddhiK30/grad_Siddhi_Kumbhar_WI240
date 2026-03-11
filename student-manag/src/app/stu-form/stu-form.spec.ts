import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StuForm } from './stu-form';

describe('StuForm', () => {
  let component: StuForm;
  let fixture: ComponentFixture<StuForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StuForm],
    }).compileComponents();

    fixture = TestBed.createComponent(StuForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
