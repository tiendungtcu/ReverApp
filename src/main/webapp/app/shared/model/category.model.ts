import { Moment } from 'moment';

export interface ICategory {
  id?: number;
  categoryName?: string;
  categoryAlias?: string;
  categoryDate?: Moment;
}

export const defaultValue: Readonly<ICategory> = {};
