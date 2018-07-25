import { ITag } from './tag.model';

export interface IResidentialArea {
  id?: number;
  residentialName?: string;
  residentialAlias?: string;
  residentialDescription?: any;
  residentialDetail?: any;
  residentialProvince?: string;
  residentialDistrict?: string;
  residentialBoundary?: any;
  residentialAvatar?: string;
  tags?: ITag[];
}

export const defaultValue: Readonly<IResidentialArea> = {};
