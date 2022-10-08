import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { HttpService } from '../Service/http.service';

@Component({
  selector: 'app-exit',
  templateUrl: './exit.component.html',
  styleUrls: ['./exit.component.css'],
})
export class ExitComponent implements OnInit {
  constructor(private service: HttpService) {}
  displayMsg = '';
  ngOnInit() {
    this.onExit();
  }
  onExit() {
    this.service.exitTrade().subscribe((res) => {
      this.displayMsg = res;
      console.log(this.displayMsg);
    });
  }
}
