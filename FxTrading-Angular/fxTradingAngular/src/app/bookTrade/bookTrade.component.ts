import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TradingData } from '../models/TradingData';
import { ConfirmBookingService } from '../Services/ConfirmBooking.service';
import { PrintServiceService } from '../Services/printService.service';
@Component({
  selector: 'app-bookTrade',
  templateUrl: './bookTrade.component.html',
  styleUrls: ['./bookTrade.component.css'],
})
export class BookTradeComponent implements OnInit {
  data: TradingData = new TradingData();
  displayMsg!: string;
  totalAmount = 0;
  current = 0;
  currentTradeId = -1;
  isHidden = true;
  displayBtn = false;
  id!: string;
  selectedPair: string = '';
  constructor(
    private confirmBookingService: ConfirmBookingService,
    private printServiceService: PrintServiceService,
    private router: Router
  ) {}

  ngOnInit() {}
  onPrint() {
    if (this.data.amount <= 0) alert('Please Enter Postive value');
    else if (
      this.data.amount !== undefined &&
      this.data.customerName !== undefined
    ) {
      this.totalAmount = this.data.amount * 66;
      this.displayMsg = `You are transferring INR ${this.totalAmount} to ${this.data.customerName}`;
    } else
      this.displayMsg = 'To see Rate please enter customer name and amount';
  }

  onConfirmBook() {
    if (this.data.currencyPair.toUpperCase() !== 'USDINR') {
      alert(`${this.data.currencyPair} is not valid only USDINR is supported`);
    } else if (this.data.amount <= 0) {
      alert(`Cannot accept ${this.data.amount} Enter Postive value`);
    } else {
      this.confirmBookingService.onSubmitBook(this.data).subscribe(
        (data: any) => {
          this.currentTradeId = data.trade.tradeNo;

          alert('sucess,please confirm your trade');
          this.displayBtn = true;
          this.isHidden = false;
        },
        (error) => alert('Cannot Book trade')
      );
    }
  }

  onConfirmedTrade() {
    this.confirmBookingService.onConfirmTrade(this.currentTradeId).subscribe(
      (data: any) => {
        alert(data.msg);
      },
      (error) => alert('Something went Wrong')
    );
    this.router.navigateByUrl('/display');
  }

  onCancelledTrade() {
    this.confirmBookingService.onCancelTrade(this.currentTradeId).subscribe(
      (data: any) => {
        alert(data.msg);
      },
      (error) => {
        'something went wrong';
      }
    );
    this.router.navigateByUrl('/display');
  }
}
