import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RestaurantSearchPage } from './restaurant-search.page';

describe('RestaurantSearchPage', () => {
  let component: RestaurantSearchPage;
  let fixture: ComponentFixture<RestaurantSearchPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(RestaurantSearchPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
