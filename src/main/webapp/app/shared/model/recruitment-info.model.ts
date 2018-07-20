import { Moment } from 'moment';

export interface IRecruitmentInfo {
  id?: number;
  recruitmentTitle?: string;
  recruitmentImageContentType?: string;
  recruitmentImage?: any;
  recruitmentContent?: any;
  recruitmentNotes?: string;
  recruitmentDate?: Moment;
  recruitmentSeenCount?: number;
  recruitmentStatus?: boolean;
  photoId?: number;
  jobtitleTitleName?: string;
  jobtitleId?: number;
}

export const defaultValue: Readonly<IRecruitmentInfo> = {
  recruitmentStatus: false
};
