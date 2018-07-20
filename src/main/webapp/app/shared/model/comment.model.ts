import { Moment } from 'moment';

export interface IComment {
  id?: number;
  commentTitle?: string;
  commentContent?: any;
  commentCreatedDate?: Moment;
  commentUpdateDate?: Moment;
  userLogin?: string;
  userId?: number;
  postId?: number;
}

export const defaultValue: Readonly<IComment> = {};
