import { Moment } from 'moment';

export const enum Gender {
  MALE = 'MALE',
  FEMALE = 'FEMALE',
  OTHER = 'OTHER'
}

export interface IEmployee {
  id?: number;
  employeeFirstName?: string;
  employeeLastName?: string;
  employeeDob?: Moment;
  employeeSex?: Gender;
  employeeIdentityCard?: string;
  employeePhone?: string;
  employeeEmail?: string;
  accountLogin?: string;
  accountId?: number;
  departmentDepartmentName?: string;
  departmentId?: number;
  jobtitleTitleName?: string;
  jobtitleId?: number;
}

export const defaultValue: Readonly<IEmployee> = {};
