import { Moment } from 'moment';

export const enum BlogStatus {
  PUBLISHED = 'PUBLISHED',
  DRAFT = 'DRAFT',
  DELETED = 'DELETED'
}

export interface IBlogPost {
  id?: number;
  postTitle?: string;
  postStatus?: BlogStatus;
  postCreatedDate?: Moment;
  postSeenCount?: number;
  postContent?: any;
  categoryCategoryName?: string;
  categoryId?: number;
  userLogin?: string;
  userId?: number;
}

export const defaultValue: Readonly<IBlogPost> = {};
