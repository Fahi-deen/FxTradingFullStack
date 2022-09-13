import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Observer } from 'rxjs';
import { TradingData } from '../models/TradingData';


@Injectable({
  providedIn: 'root'
})
export class ConfirmBookingService {
  baseUrl="http://localhost:8082/";
constructor(private httpClient:HttpClient) { }
  onSubmitBook(data:TradingData):Observable<Object>{
     console.log(data);
     const res =  this.httpClient.post(`${this.baseUrl}booktrade`,data);
     console.log(res)
      return res;
  }
  onConfirmTrade(id : number):Observable<Object>{

    const res =  this.httpClient.put(`${this.baseUrl}confirmtrade`, { tradeNo : id })
    console.log(res)
     return res;
  }
  onCancelTrade(id : number):Observable<Object>{
    const res =  this.httpClient.delete(`${this.baseUrl}canceltrades?id=${id}`)
    console.log(res)
     return res;
  }

  }
