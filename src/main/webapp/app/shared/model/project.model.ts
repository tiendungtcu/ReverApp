import { Moment } from 'moment';
import { ITag } from './tag.model';
import { IBuildingType } from './building-type.model';
import { IInvestor } from './investor.model';
import { IContractor } from './contractor.model';

export const enum TransactionStatus {
  SELLING = 'SELLING',
  PRESELL = 'PRESELL',
  CLOSED = 'CLOSED'
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
  projectAvatarUrl?: string;
  projectDistrict?: string;
  projectProvince?: string;
  residentialAreaId?: number;
  projectRoad?: string;
  projectWard?: string;
  projectStatus?: TransactionStatus;
  projectNoBlocks?: number;
  projectNoFloors?: number;
  projectNoApartments?: number;
  projectNoShophouse?: number;
  projectDescription?: any;
  projectMinSellPrice?: number;
  projectMaxSellPrice?: number;
  projectSellPriceUnit?: PriceUnit;
  projectMinRentPrice?: number;
  projectMaxRentPrice?: number;
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
  locationId?: number;
  consultantLogin?: string;
  consultantId?: number;
  tags?: ITag[];
  buildingtypes?: IBuildingType[];
  inverstors?: IInvestor[];
  contractors?: IContractor[];
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
