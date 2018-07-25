import { Moment } from 'moment';

export const enum ResourceType {
  PROJECT = 'PROJECT',
  PROPERTY = 'PROPERTY',
  EMPLOYEE = 'EMPLOYEE',
  RESIDENTIAL_AREA = 'RESIDENTIAL_AREA'
}

export interface IPhoto {
  id?: number;
  photoName?: string;
  photoDate?: Moment;
  photoUrl?: string;
  photoMimeType?: string;
  resourceId?: number;
  resourceType?: ResourceType;
  photoSize?: number;
  photoAltText?: string;
  photoThumbnailUrl?: string;
}

export const defaultValue: Readonly<IPhoto> = {};
