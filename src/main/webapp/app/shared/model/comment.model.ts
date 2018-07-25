import { Moment } from 'moment';

export interface IComment {
  id?: number;
  commentContent?: string;
  commentTimeStamp?: Moment;
  userLogin?: string;
  userId?: number;
  postPostTitle?: string;
  postId?: number;
}

export const defaultValue: Readonly<IComment> = {};
