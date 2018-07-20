import { Moment } from 'moment';

export interface IProjectUser {
  id?: number;
  liked?: boolean;
  shared?: boolean;
  likedDate?: Moment;
  sharedDate?: Moment;
  userLogin?: string;
  userId?: number;
  projectProjectName?: string;
  projectId?: number;
}

export const defaultValue: Readonly<IProjectUser> = {
  liked: false,
  shared: false
};
