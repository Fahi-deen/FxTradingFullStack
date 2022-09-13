import { Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TradingData } from '../models/TradingData';

import { PrintServiceService } from '../Services/printService.service';


@Component({
  selector: 'app-display',
  templateUrl: './display.component.html',
  styleUrls: ['./display.component.css']
})
export class DisplayComponent implements OnInit {
  datas:TradingData[]|undefined;
  constructor(private printService:PrintServiceService,private router:Router,) { }

  ngOnInit() {
  }
onPrint(){
  this.printService.printTrade().subscribe((data: TradingData[]) => {
    this.datas= data;
  });
  this.router.navigateByUrl("/printtrade");
}
onBook(){
  this.router.navigateByUrl('/bookTrade');
}
onExit(){
  this.router.navigateByUrl('/exitTrade');
}
}
