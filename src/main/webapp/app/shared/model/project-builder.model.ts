import { Moment } from 'moment';
import { IProject } from './project.model';

export interface IProjectBuilder {
  id?: number;
  builderName?: string;
  builderTitle?: string;
  builderDate?: Moment;
  builderDescription?: string;
  builderAddress?: string;
  builderWebsite?: string;
  builderPhone?: string;
  builderPhotoContentType?: string;
  builderPhoto?: any;
  projects?: IProject[];
}

export const defaultValue: Readonly<IProjectBuilder> = {};
