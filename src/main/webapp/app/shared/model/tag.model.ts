import { IProperty } from './property.model';
import { IProject } from './project.model';
import { IResidentialArea } from './residential-area.model';

export interface ITag {
  id?: number;
  tagName?: string;
  properties?: IProperty[];
  projects?: IProject[];
  residentialAreas?: IResidentialArea[];
}

export const defaultValue: Readonly<ITag> = {};
