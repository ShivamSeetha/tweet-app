import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-server-error',
  templateUrl: './server-error.component.html',
  styleUrls: ['./server-error.component.css']
})
export class ServerErrorComponent implements OnInit {

  constructor(
    private authService: AuthService, 
    private router : Router
  )
  {}
  goBack(){
    this.authService.setCurrentUser(null);
    this.router.navigate(["login"]);
  }

  ngOnInit(): void {  }

}
