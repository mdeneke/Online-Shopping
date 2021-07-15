/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { JersyComponent } from './jersy.component';

describe('JersyComponent', () => {
  let component: JersyComponent;
  let fixture: ComponentFixture<JersyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JersyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JersyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
