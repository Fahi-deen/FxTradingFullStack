import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookTradeComponent } from './bookTrade/bookTrade.component';
import { DisplayComponent } from './display/display.component';
import { ExitComponent } from './exit/exit.component';
import { PrintTradeComponent } from './printTrade/printTrade.component';

const routes: Routes = [


    {path:"printtrade",component:PrintTradeComponent},
    {path:"bookTrade",component:BookTradeComponent},
    {path:"exitTrade",component:ExitComponent},
    {path:"display",component:DisplayComponent},
    {path:'',component:DisplayComponent},
    {path:'**',component:DisplayComponent,pathMatch:'full'},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
