import { Moment } from 'moment';

export const enum NotificationType {
  NOTIFICATION = 'NOTIFICATION',
  REQUEST = 'REQUEST',
  ALERT = 'ALERT'
}

export interface INotification {
  id?: number;
  notificationTitle?: string;
  notificationContent?: any;
  notificationSeen?: boolean;
  notificationDate?: Moment;
  notificationType?: NotificationType;
  notificationReference?: string;
  userLogin?: string;
  userId?: number;
}

export const defaultValue: Readonly<INotification> = {
  notificationSeen: false
};
