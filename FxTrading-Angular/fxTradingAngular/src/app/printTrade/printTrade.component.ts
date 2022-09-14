import { Component, OnInit } from '@angular/core';
import { TradingData } from '../models/TradingData';

import { PrintServiceService } from '../Services/printService.service';
@Component({
  selector: 'app-printTrade',
  templateUrl: './printTrade.component.html',
  styleUrls: ['./printTrade.component.css'],
})
export class PrintTradeComponent implements OnInit {
  datas!: any;
  page: number = 1;
  count: number = 0;
  tableSize: number = 10;
  tableSizes: any = [4, 8, 12];
  constructor(private printService: PrintServiceService) {}

  ngOnInit(): void {
    this.tradeList();
  }
  tradeList(): void {
    this.printService.printTrade().subscribe((data) => {
      this.datas = data;
    });
  }
  onTableDataChange(event: any) {
    this.page = event;
    console.log('hello');
    console.log(this.tradeList());
    this.tradeList();
  }
  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.page = 1;
    this.tradeList();
  }
}
