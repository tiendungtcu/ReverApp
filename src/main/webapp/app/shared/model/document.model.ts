import { Moment } from 'moment';

export const enum ResourceType {
  PROJECT = 'PROJECT',
  PROPERTY = 'PROPERTY',
  EMPLOYEE = 'EMPLOYEE',
  RESIDENTIAL_AREA = 'RESIDENTIAL_AREA'
}

export interface IDocument {
  id?: number;
  documentName?: string;
  documentUrl?: string;
  documentDate?: Moment;
  documentMimeType?: string;
  documentSize?: string;
  resourceType?: ResourceType;
  resourceId?: number;
}

export const defaultValue: Readonly<IDocument> = {};
