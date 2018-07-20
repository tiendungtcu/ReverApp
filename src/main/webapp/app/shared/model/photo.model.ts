import { IProperty } from './property.model';
import { IProject } from './project.model';

export interface IPhoto {
  id?: number;
  photoName?: string;
  photoImageContentType?: string;
  photoImage?: any;
  photoExtension?: string;
  photoUrl?: string;
  photoThumbnailUrl?: string;
  properties?: IProperty[];
  projects?: IProject[];
}

export const defaultValue: Readonly<IPhoto> = {};
