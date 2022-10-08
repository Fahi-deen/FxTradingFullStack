import { Component, OnInit } from '@angular/core';
import { HttpService } from '../Service/http.service';

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
  tableSizes: any = [10, 15, 20];
  constructor(private printService: HttpService) {}

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
    console.log(this.tradeList());
    this.tradeList();
  }
  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.page = 1;
    this.tradeList();
  }
}
