import { Component, OnInit } from '@angular/core';
import { TradingData } from '../models/TradingData';

import { PrintServiceService } from '../Services/printService.service';
@Component({
  selector: 'app-printTrade',
  templateUrl: './printTrade.component.html',
  styleUrls: ['./printTrade.component.css'],
})
export class PrintTradeComponent implements OnInit {
  datas!: TradingData[];
  constructor(private printService: PrintServiceService) {}

  ngOnInit(): void {
    this.printService.printTrade().subscribe((data: TradingData[]) => {
      this.datas = data;
    });
  }
}
