export interface TradingData {
  trade: {
    customerName: string;
    currencyPair: string;
    amount: number;
    status: string;
  };
}
