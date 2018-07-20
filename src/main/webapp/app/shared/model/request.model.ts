import { Moment } from 'moment';

export const enum PropertyType {
  PROJECT = 'PROJECT',
  PROPERTY = 'PROPERTY',
  LAND = 'LAND'
}

export const enum RequestType {
  MEETING = 'MEETING',
  PRICE = 'PRICE',
  QUESTION = 'QUESTION'
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
  requestPageName?: string;
  requestPropertyId?: number;
  requestPropertyType?: PropertyType;
  requestType?: RequestType;
  requestMeetingDate?: Moment;
  requestQuestion?: string;
  requestPrice?: number;
  requestCreatedDate?: Moment;
  requestConsultantId?: number;
  userLogin?: string;
  userId?: number;
  propertyPropertyName?: string;
  propertyId?: number;
  projectProjectName?: string;
  projectId?: number;
}

export const defaultValue: Readonly<IRequest> = {
  requestGetAnalysis: false,
  requestGetPrice: false
};
