const config = {
  VERSION: process.env.VERSION,
  SERVER_API_URL: process.env.SERVER_API_URL
};

export default config;

export const SERVER_API_URL = '';

export const AUTHORITIES = {
  ADMIN: 'ROLE_ADMIN',
  MANAGER: 'ROLE_MANAGER',
  POSTER: 'ROLE_POSTER',
  CONSULTANT: 'ROLE_CONSULTANT',
  USER: 'ROLE_USER'
};

export const messages = {
  DATA_ERROR_ALERT: 'Internal Error'
};

export const APP_DATE_FORMAT = 'DD/MM/YY HH:mm';
export const APP_TIMESTAMP_FORMAT = 'DD/MM/YY HH:mm:ss';
export const APP_LOCAL_DATE_FORMAT = 'DD/MM/YYYY';
export const APP_LOCAL_DATETIME_FORMAT = 'YYYY-MM-DDThh:mm';
export const APP_WHOLE_NUMBER_FORMAT = '0,0';
export const APP_TWO_DIGITS_AFTER_POINT_NUMBER_FORMAT = '0,0.[00]';
