import { Moment } from 'moment';
import { IBlogPost } from './blog-post.model';
import { ITag } from './tag.model';
import { IBuildingType } from './building-type.model';
import { IInvestor } from './investor.model';
import { IProjectBuilder } from './project-builder.model';
import { IPhoto } from './photo.model';

export const enum TransactionStatus {
  SELLING = 'SELLING',
  PRESELL = 'PRESELL',
  CLOSED = 'CLOSED'
}

export const enum AreaUnit {
  M2 = 'M2',
  CAN = 'CAN',
  MONTH = 'MONTH',
  YEAR = 'YEAR'
}

export const enum PriceUnit {
  THOUSAND = 'THOUSAND',
  MILLION = 'MILLION',
  BILLION = 'BILLION'
}

export interface IProject {
  id?: number;
  projectName?: string;
  projectAlias?: string;
  projectAvatarContentType?: string;
  projectAvatar?: any;
  projectAvatarId?: number;
  projectAvatarUrl?: string;
  projectDistrict?: string;
  projectProvince?: string;
  projectResidentialArea?: string;
  projectRoad?: string;
  projectWard?: string;
  projectStatus?: TransactionStatus;
  projectType?: string;
  projectNoBlocks?: number;
  projectNoFloors?: number;
  projectNoApartments?: number;
  projectNoShophouse?: number;
  projectDescription?: any;
  projectMinSellPrice?: number;
  projectMaxSellPrice?: number;
  projectSellAreaUnit?: AreaUnit;
  projectSellPriceUnit?: PriceUnit;
  projectMinRentPrice?: number;
  projectMaxRentPrice?: number;
  projectRentAreaUnit?: AreaUnit;
  projectRentPriceUnit?: PriceUnit;
  projectStartedDate?: Moment;
  projectFinishingDate?: Moment;
  projectMinApartmentSquare?: number;
  projectMaxApartmentSquare?: number;
  projectGreenSpace?: number;
  projectBuildingDensity?: number;
  projectDesignCompany?: string;
  projectCarPark?: boolean;
  projectBbqCourt?: boolean;
  projectElevator?: boolean;
  projectShoppingCenter?: boolean;
  projectSwimmingPool?: boolean;
  projectCommunityRoom?: boolean;
  projectGym?: boolean;
  projectCityPark?: boolean;
  projectGuard?: boolean;
  projectPlayGround?: boolean;
  longitude?: number;
  latitude?: number;
  projectSeenCount?: number;
  projectAvailable?: boolean;
  documentId?: number;
  locationId?: number;
  posts?: IBlogPost[];
  tags?: ITag[];
  buildingtypes?: IBuildingType[];
  investors?: IInvestor[];
  projectbuilders?: IProjectBuilder[];
  photos?: IPhoto[];
}

export const defaultValue: Readonly<IProject> = {
  projectCarPark: false,
  projectBbqCourt: false,
  projectElevator: false,
  projectShoppingCenter: false,
  projectSwimmingPool: false,
  projectCommunityRoom: false,
  projectGym: false,
  projectCityPark: false,
  projectGuard: false,
  projectPlayGround: false,
  projectAvailable: false
};
