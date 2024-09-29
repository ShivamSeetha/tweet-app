import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-back-to-login',
  templateUrl: './back-to-login.component.html',
  styleUrls: ['./back-to-login.component.css']
})
export class BackToLoginComponent implements OnInit {

  constructor(
    private router : Router,
    private authService: AuthService
  ) { }

  goBack(){
    this.authService.setCurrentUser(null);
    this.router.navigate(["login"]);
  }

  ngOnInit(): void {  }

}

