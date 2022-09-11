
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TradingData } from '../models/TradingData';
import { ConfirmBookingService } from '../Services/ConfirmBooking.service';
;

@Component({
  selector: 'app-bookTrade',
  templateUrl: './bookTrade.component.html',
  styleUrls: ['./bookTrade.component.css']
})
export class BookTradeComponent implements OnInit {
   data:TradingData=new TradingData();
    displayMsg:string | undefined;
   totalAmount=0;
   current=0;
   currentTradeId = -1;
    id!:string;
  constructor(private confirmBookingService:ConfirmBookingService,private router:Router,httpClient:HttpClient) {
  }

  ngOnInit() {
  }
  onPrint(){
    if(typeof this.data.amount=='string' )

     this.totalAmount=(this.data.amount * 66) ;
     this.displayMsg=`You are transferring INR ${this.totalAmount} to ${this.data.customerName}`

  }
  onConfirmBook(){
      console.log(this.data);
      this.confirmBookingService.onSubmitBook(this.data).subscribe((data : any)=>{
        this.currentTradeId = data.trade.tradeNo;
        alert("sucess,please confirm your trade");
      },error=>alert("Cannot Book trade"));

    }

    onConfirmedTrade(){
      if(this.currentTradeId > 0){
        console.log("csdc",this.currentTradeId)
      this.confirmBookingService.onConfirmTrade(this.currentTradeId).subscribe((data : any)=>{
        this.currentTradeId = -2 ;
        alert("sucess");
      },error=>alert("Your trade booked sucessfully.."));
      this.router.navigateByUrl("/display");
    }
    else if(this.currentTradeId  == -1){
      alert(" Not booked Yet");
    }
    else {
      alert(" Already booked");
    }

  }
  onCancelledTrade(){
    if(this.currentTradeId > 0){
      this.confirmBookingService.onCancelTrade(this.currentTradeId).subscribe((data : any)=>{
        this.currentTradeId = -2 ;
        alert("Trade Cancelled");
      },error=>alert("ok"));
      this.router.navigateByUrl("/display");
    }
    else if(this.currentTradeId  == -1){
      alert(" Not booked Yet");
    }
    else {
      alert(" Already booked");
    }

  }

}
