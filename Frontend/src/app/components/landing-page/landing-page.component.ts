import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-landing-page',
  imports: [],
  templateUrl: './landing-page.component.html',
  styleUrl: './landing-page.component.css'
})

export class LandingPageComponent implements OnInit{
  constructor(private router: Router) {}

  ngOnInit(): void {
      setTimeout(() => {
        this.router.navigate(['/config']);
      }, 7000);
  }

}
