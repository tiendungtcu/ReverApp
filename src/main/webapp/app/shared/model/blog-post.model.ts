import { Moment } from 'moment';
import { IComment } from './comment.model';

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
  postPublishDate?: Moment;
  postUpdateDate?: Moment;
  postSeenCount?: number;
  postContent?: any;
  comments?: IComment[];
  categoryId?: number;
  userLogin?: string;
  userId?: number;
  projectProjectName?: string;
  projectId?: number;
}

export const defaultValue: Readonly<IBlogPost> = {};
