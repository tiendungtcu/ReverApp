import { Moment } from 'moment';

export const enum DocumentType {
  PROJECT = 'PROJECT',
  PROPERTY = 'PROPERTY',
  EMPLOYEE = 'EMPLOYEE'
}

export interface IDocument {
  id?: number;
  documentName?: string;
  documentUrl?: string;
  documentDate?: Moment;
  documentContent?: any;
  documentPhotoContentType?: string;
  documentPhoto?: any;
  documentType?: DocumentType;
  photoId?: number;
  projectId?: number;
}

export const defaultValue: Readonly<IDocument> = {};
