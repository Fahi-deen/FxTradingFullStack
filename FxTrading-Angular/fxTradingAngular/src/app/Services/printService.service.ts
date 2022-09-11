import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Router } from '@angular/router';
import { TradingData } from '../models/TradingData';

@Injectable({
  providedIn: 'root'
})
export class PrintServiceService {
private baseUrl="http://localhost:8082/printtrade";
constructor(private http:HttpClient) { }
printTrade():Observable<TradingData[]>{
   return this.http.get<TradingData[]>(`${this.baseUrl}`);

  }
}
