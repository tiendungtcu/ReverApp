import { Moment } from 'moment';

export const enum BlogStatus {
  PUBLISHED = 'PUBLISHED',
  DRAFT = 'DRAFT',
  DELETED = 'DELETED'
}

export interface IArticle {
  id?: number;
  articleTitle?: string;
  articleStatus?: BlogStatus;
  articleDate?: Moment;
  articleSeenCount?: number;
  articleContent?: any;
  categoryId?: number;
  userLogin?: string;
  userId?: number;
}

export const defaultValue: Readonly<IArticle> = {};
