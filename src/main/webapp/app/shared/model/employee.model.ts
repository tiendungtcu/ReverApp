import { Moment } from 'moment';

export const enum Gender {
  MALE = 'MALE',
  FEMALE = 'FEMALE',
  OTHER = 'OTHER'
}

export interface IEmployee {
  id?: number;
  employeeName?: string;
  employeeFirstName?: string;
  employeeLastName?: string;
  employeeDob?: Moment;
  employeeSex?: Gender;
  employeeIdentityCard?: string;
  employeePhone?: string;
  employeeEmail?: string;
  employeeAvatarContentType?: string;
  employeeAvatar?: any;
  employeeFacebook?: string;
  employeeLinkedin?: string;
  employeeInstagram?: string;
  employeeGooglePlus?: string;
  employeeZalo?: string;
  employeeTwitter?: string;
  employeeYoutube?: string;
  contactId?: number;
  photoId?: number;
  jobtitleTitleName?: string;
  jobtitleId?: number;
  departmentDepartmentName?: string;
  departmentId?: number;
  managerEmployeeName?: string;
  managerId?: number;
}

export const defaultValue: Readonly<IEmployee> = {};
