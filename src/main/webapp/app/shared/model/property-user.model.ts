import { Moment } from 'moment';

export interface IPropertyUser {
  id?: number;
  liked?: boolean;
  shared?: boolean;
  likedDate?: Moment;
  sharedDate?: Moment;
  userLogin?: string;
  userId?: number;
  propertyPropertyName?: string;
  propertyId?: number;
}

export const defaultValue: Readonly<IPropertyUser> = {
  liked: false,
  shared: false
};
