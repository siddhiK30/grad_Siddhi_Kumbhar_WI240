import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StuList } from './stu-list';

describe('StuList', () => {
  let component: StuList;
  let fixture: ComponentFixture<StuList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StuList],
    }).compileComponents();

    fixture = TestBed.createComponent(StuList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
