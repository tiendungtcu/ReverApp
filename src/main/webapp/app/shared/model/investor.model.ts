import { Moment } from 'moment';
import { IProject } from './project.model';

export interface IInvestor {
  id?: number;
  investorName?: string;
  investorTitle?: string;
  investorDate?: Moment;
  investorDescription?: string;
  investorAddress?: string;
  investorWebsite?: string;
  investorPhone?: string;
  investorPhotoContentType?: string;
  investorPhoto?: any;
  projects?: IProject[];
}

export const defaultValue: Readonly<IInvestor> = {};
