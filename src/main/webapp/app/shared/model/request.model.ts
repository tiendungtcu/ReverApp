import { Moment } from 'moment';

export const enum ResourceType {
  PROJECT = 'PROJECT',
  PROPERTY = 'PROPERTY',
  EMPLOYEE = 'EMPLOYEE',
  RESIDENTIAL_AREA = 'RESIDENTIAL_AREA'
}

export const enum RequestType {
  MEETING = 'MEETING',
  PRICE = 'PRICE',
  QUESTION = 'QUESTION',
  OTHER = 'OTHER'
}

export interface IRequest {
  id?: number;
  requestFirstName?: string;
  requestLastName?: string;
  requestEmail?: string;
  requestPhone?: string;
  requestGetAnalysis?: boolean;
  requestGetPrice?: boolean;
  requestPageUrl?: string;
  resourceId?: number;
  resourceType?: ResourceType;
  requestType?: RequestType;
  requestMeetingDate?: Moment;
  requestQuestion?: string;
  requestPrice?: number;
  senderLogin?: string;
  senderId?: number;
  receiverLogin?: string;
  receiverId?: number;
}

export const defaultValue: Readonly<IRequest> = {
  requestGetAnalysis: false,
  requestGetPrice: false
};
