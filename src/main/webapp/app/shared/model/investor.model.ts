import { Moment } from 'moment';

export interface IInvestor {
  id?: number;
  investorName?: string;
  investorTitle?: string;
  investorDate?: Moment;
  investorDescription?: string;
  investorAddress?: string;
  investorWebsite?: string;
  investorPhone?: string;
  investorAvatarUrl?: string;
}

export const defaultValue: Readonly<IInvestor> = {};
