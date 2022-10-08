import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-loadingAnimation',
  templateUrl: './loadingAnimation.component.html',
  styleUrls: ['./loadingAnimation.component.css'],
})
export class LoadingAnimationComponent implements OnInit {
  constructor(private router: Router) {}

  ngOnInit() {
    setTimeout(() => {
      this.router.navigate(['home']);
    }, 1900);
  }
}
