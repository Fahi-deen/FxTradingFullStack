import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PageNotFoundComponent } from './pageNotFound/pageNotFound.component';
import { DashBoardComponent } from './dashBoard/dashBoard.component';
import { LoadingAnimationComponent } from './loadingAnimation/loadingAnimation.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BookTradeComponent } from './bookTrade/bookTrade.component';
import { PrintTradeComponent } from './printTrade/printTrade.component';
import { ExitComponent } from './exit/exit.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgxPaginationModule } from 'ngx-pagination';
import { HttpClientModule } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    DashBoardComponent,
    LoadingAnimationComponent,
    BookTradeComponent,
    PrintTradeComponent,
    ExitComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    NgxPaginationModule,
    HttpClientModule,
    ToastrModule.forRoot(),
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
