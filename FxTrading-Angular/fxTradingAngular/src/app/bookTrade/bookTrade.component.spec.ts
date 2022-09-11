/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { BookTradeComponent } from './bookTrade.component';

describe('BookTradeComponent', () => {
  let component: BookTradeComponent;
  let fixture: ComponentFixture<BookTradeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookTradeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookTradeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
