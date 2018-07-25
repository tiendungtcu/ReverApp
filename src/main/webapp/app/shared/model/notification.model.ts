import { Moment } from 'moment';

export const enum NotificationType {
  SYSTEM = 'SYSTEM',
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
  notificationSender?: number;
}

export const defaultValue: Readonly<INotification> = {
  notificationSeen: false
};
