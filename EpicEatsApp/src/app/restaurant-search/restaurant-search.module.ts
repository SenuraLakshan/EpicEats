import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { RestaurantSearchPageRoutingModule } from './restaurant-search-routing.module';

import { RestaurantSearchPage } from './restaurant-search.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RestaurantSearchPageRoutingModule
  ],
  declarations: [RestaurantSearchPage]
})
export class RestaurantSearchPageModule {}
