import { Component, OnInit } from '@angular/core';
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-menu-operations',
  templateUrl: './menu-operations.page.html',
  styleUrls: ['./menu-operations.page.scss'],
})
export class MenuOperationsPage implements OnInit {

  constructor(
    private authService: AuthService,
    private router: Router,) {
  }

  ngOnInit() {
  }

  async logout() {
    await this.authService.logout();
    this.router.navigateByUrl('/', {replaceUrl: true});
  }

}
