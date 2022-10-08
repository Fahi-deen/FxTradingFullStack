import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TradingData } from '../Model/tradingData';
@Injectable({
  providedIn: 'root',
})
export class HttpService {
  private baseUrl = 'http://localhost:8080/';

  constructor(private http: HttpClient) {}
  onSubmitBook(data: any) {
    return this.http.post(`${this.baseUrl}booktrade`, data);
  }
  onConfirmTrade(id: any) {
    return this.http.put(`${this.baseUrl}confirmtrade/${id}`, {
      responseType: 'text',
    });
  }
  onCancelTrade(id: any) {
    return this.http.delete(`${this.baseUrl}canceltrade/${id}`, {
      responseType: 'text',
    });
  }
  getRate(id: any) {
    return this.http.get(`${this.baseUrl}printrate/${id}`, {
      responseType: 'text',
    });
  }

  printTrade() {
    return this.http.get(`${this.baseUrl}printtrade`);
  }
  exitTrade() {
    return this.http.get(`${this.baseUrl}exit`, { responseType: 'text' });
  }
}
