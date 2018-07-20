import { IProject } from './project.model';
import { IProperty } from './property.model';

export const enum PropertyType {
  PROJECT = 'PROJECT',
  PROPERTY = 'PROPERTY',
  LAND = 'LAND'
}

export interface IBuildingType {
  id?: number;
  typeName?: string;
  typeSelect?: PropertyType;
  projects?: IProject[];
  properties?: IProperty[];
}

export const defaultValue: Readonly<IBuildingType> = {};
