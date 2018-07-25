import { Moment } from 'moment';

export interface IRecruitmentInfo {
  id?: number;
  recruitmentTitle?: string;
  recruitmentAvatarUrl?: string;
  recruitmentContent?: any;
  recruitmentNotes?: string;
  recruitmentDate?: Moment;
  recruitmentSeenCount?: number;
  recruitmentStatus?: boolean;
  userLogin?: string;
  userId?: number;
  jobtitleTitleName?: string;
  jobtitleId?: number;
}

export const defaultValue: Readonly<IRecruitmentInfo> = {
  recruitmentStatus: false
};
