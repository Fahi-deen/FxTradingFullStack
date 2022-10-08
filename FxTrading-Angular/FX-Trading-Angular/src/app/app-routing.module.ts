import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookTradeComponent } from './bookTrade/bookTrade.component';
import { DashBoardComponent } from './dashBoard/dashBoard.component';
import { ExitComponent } from './exit/exit.component';
import { LoadingAnimationComponent } from './loadingAnimation/loadingAnimation.component';
import { PageNotFoundComponent } from './pageNotFound/pageNotFound.component';
import { PrintTradeComponent } from './printTrade/printTrade.component';

const routes: Routes = [
  {
    path: '',
    component: LoadingAnimationComponent,
  },
  {
    path: 'home',
    component: DashBoardComponent,
  },
  {
    path: 'bookTrade',
    component: BookTradeComponent,
  },
  {
    path: 'loading',
    component: LoadingAnimationComponent,
  },
  {
    path: 'printTrade',
    component: PrintTradeComponent,
  },
  {
    path: 'exit',
    component: ExitComponent,
  },
  {
    path: '**',
    component: PageNotFoundComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
