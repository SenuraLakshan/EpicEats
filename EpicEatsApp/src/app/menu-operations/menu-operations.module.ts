import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { MenuOperationsPageRoutingModule } from './menu-operations-routing.module';

import { MenuOperationsPage } from './menu-operations.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    MenuOperationsPageRoutingModule
  ],
  declarations: [MenuOperationsPage]
})
export class MenuOperationsPageModule {}
