import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import Swal from 'sweetalert2';
import { TradingData } from '../Model/tradingData';
import { HttpService } from '../Service/http.service';

@Component({
  selector: 'app-bookTrade',
  templateUrl: './bookTrade.component.html',
  styleUrls: ['./bookTrade.component.css'],
})
export class BookTradeComponent implements OnInit {
  data: any = <TradingData>{};
  displayMsg!: string;
  totalAmount = 0;
  options = [{ name: 'USDINR' }];
  currentTradeId = -1;
  displayAmount!: string;
  isHidden = true;
  displayBtn = false;
  selectedPair: string = '';
  constructor(
    private service: HttpService,
    private router: Router,
    private toastr: ToastrService
  ) {}

  ngOnInit() {}
  onPrint() {
    console.log(this.data);

    if (this.data.amount <= 0) alert('Please Enter Postive value');
    else if (
      this.data.amount !== undefined &&
      this.data.customerName !== undefined
    ) {
      this.totalAmount = this.data.amount * 66;
      this.displayAmount = this.totalAmount.toLocaleString('en-us', {
        minimumFractionDigits: 0,
      });

      this.displayMsg = `You are transferring INR ${this.displayAmount} to ${this.data.customerName}`;
    } else
      this.displayMsg = 'To see Rate please enter customer name and amount';
  }

  onConfirmBook() {
    if (this.data.amount <= 0) {
      alert(`Cannot accept ${this.data.amount} Enter Postive value`);
    } else {
      this.service.onSubmitBook(this.data).subscribe(
        (data: any) => {
          this.currentTradeId = data.trade.tradeNo;

          Swal.fire({
            position: 'center',
            icon: 'success',
            title:
              'Your Trade is Processing,Please Confirm or cancel your trade',
            showConfirmButton: false,
            timer: 5500,
            showCloseButton: true,
          });
          this.displayBtn = true;
          this.isHidden = false;
        },
        (error) => this.toastr.error('Cannot Book trade')
      );
    }
  }

  onConfirmedTrade() {
    this.service.onConfirmTrade(this.currentTradeId).subscribe((data: any) => {
      this.toastr.info(`${data.msg}`, '', {
        progressBar: true,
        closeButton: true,
        positionClass: 'toast-top-right',
      });
      this.router.navigateByUrl('/home');
    });
  }

  onCancelledTrade() {
    this.service.onCancelTrade(this.currentTradeId).subscribe(
      (data: any) => {
        this.toastr.info(data);
        this.router.navigateByUrl('/home');
      },
      (error) => {
        'something went wrong';
      }
    );
  }
}
