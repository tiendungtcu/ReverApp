export const enum SupportType {
  ACCOUNT = 'ACCOUNT',
  SELL = 'SELL',
  RENT = 'RENT',
  BUY = 'BUY',
  HOUSE = 'HOUSE',
  PROJECT = 'PROJECT'
}

export interface ISupportCategory {
  id?: number;
  categoryName?: string;
  categoryDescription?: string;
  categorySupportType?: SupportType;
}

export const defaultValue: Readonly<ISupportCategory> = {};
