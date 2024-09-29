import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { User } from '../interfaces/user';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})

export class LoginComponent implements OnInit {
  constructor(
    private authService: AuthService,
    private snack: MatSnackBar,
    private router: Router
  ) { }
  loginForm: FormGroup;
  currentUser: User;

  ngOnInit(): void {
    if (this.authService.isLoggedIn()) {
      this.router.navigate(['home']);
    }
  }

  public username: string = "";    // ngModel - takes username from form
  public password: string = "";    // ngModel - takes password from form

  onSubmit() {
    console.log('Submitted');

    if (this.username == '' || this.username == null 
    || this.password == '' || this.password == null) {
      this.snack.open('Kindly fill all the details !!', 'OK', { duration: 5000 });
      return;
    }

    this.authService
      .login(this.username, this.password)
      .subscribe((data: any) => {
        if (data.loginStatus == 'success') {
          this.currentUser = data.user;
          console.log(this.currentUser);
          this.authService.setCurrentUser(this.currentUser);
          this.snack.open('You have logged in successfully !!', 'OK', { duration: 4000 });
          this.router.navigate(['home']);
        } else {
          this.snack.open('Username or Password is incorrect !!', 'OK', { duration: 5000 });
        }
      });
  }
}
