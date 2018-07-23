import { Moment } from 'moment';
import { ITag } from './tag.model';
import { IBuildingType } from './building-type.model';
import { IPhoto } from './photo.model';

export const enum TransactionType {
  SELL = 'SELL',
  BUY = 'BUY',
  RENT = 'RENT',
  SELL_RENT = 'SELL_RENT'
}

export const enum UseEstablishment {
  LODGE = 'LODGE',
  PUB = 'PUB',
  SHOP = 'SHOP',
  RESTAURANT = 'RESTAURANT',
  HOTEL = 'HOTEL',
  LEASE = 'LEASE'
}

export const enum PriceUnit {
  THOUSAND = 'THOUSAND',
  MILLION = 'MILLION',
  BILLION = 'BILLION'
}

export interface IProperty {
  id?: number;
  propertyCode?: string;
  propertyName?: string;
  propertyAlias?: string;
  propertyTransaction?: TransactionType;
  propertyNumber?: string;
  propertyRoad?: string;
  propertyWard?: string;
  propertyDistrict?: string;
  propertyProvince?: string;
  propertyDescription?: any;
  propertyBedRooms?: number;
  propertyBathRooms?: number;
  propertySquare?: number;
  propertyUsePurpose?: UseEstablishment;
  propertyOwnerType?: string;
  propertyTower?: string;
  propertyRentPrice?: number;
  propertyRentUnit?: PriceUnit;
  propertyRentStartedDate?: Moment;
  propertySellPrice?: number;
  propertySellUnit?: PriceUnit;
  propertySellStartedDate?: Moment;
  propertySofa?: boolean;
  propertyDiningTable?: boolean;
  propertyKitchen?: boolean;
  propertyCabinetKitchen?: boolean;
  propertyKitchenEquipment?: boolean;
  propertyWardrobe?: boolean;
  propertyMakeupTable?: boolean;
  propertyDesk?: boolean;
  propertyTivi?: boolean;
  propertyWashingMachine?: boolean;
  propertyMicrowave?: boolean;
  propertyWaterHeater?: boolean;
  propertyBed?: boolean;
  propertyHeater?: boolean;
  propertyAudioEquipment?: boolean;
  propertyInternet?: boolean;
  propertyCableTivi?: boolean;
  propertyPetPermission?: boolean;
  propertyElevator?: boolean;
  propertySwimmingPool?: boolean;
  propertyGym?: boolean;
  propertyFunctionalArea?: boolean;
  propertyOpen24h?: boolean;
  propertyCarPark?: boolean;
  propertyBalcony?: boolean;
  propertySauna?: boolean;
  propertySteamSauna?: boolean;
  propertyAttraction?: boolean;
  propertySpecialFeature?: string;
  propertyFurnitureOverview?: string;
  propertyLocationOverview?: string;
  propertyResidentialCommunity?: string;
  propertyEducationalAspect?: string;
  propertyExtraInfo?: string;
  propertyDraftContentType?: string;
  propertyDraft?: any;
  longitude?: number;
  latitude?: number;
  propertyGoodPrice?: boolean;
  propertySeenCount?: number;
  propertyIsSold?: boolean;
  propertyIsRent?: boolean;
  propertyAvailable?: boolean;
  propertyRefrigerator?: boolean;
  propertyAirconditioner?: boolean;
  locationId?: number;
  residentialAreaResidentialName?: string;
  residentialAreaId?: number;
  tags?: ITag[];
  buildingtypes?: IBuildingType[];
  photos?: IPhoto[];
}

export const defaultValue: Readonly<IProperty> = {
  propertySofa: false,
  propertyDiningTable: false,
  propertyKitchen: false,
  propertyCabinetKitchen: false,
  propertyKitchenEquipment: false,
  propertyWardrobe: false,
  propertyMakeupTable: false,
  propertyDesk: false,
  propertyTivi: false,
  propertyWashingMachine: false,
  propertyMicrowave: false,
  propertyWaterHeater: false,
  propertyBed: false,
  propertyHeater: false,
  propertyAudioEquipment: false,
  propertyInternet: false,
  propertyCableTivi: false,
  propertyPetPermission: false,
  propertyElevator: false,
  propertySwimmingPool: false,
  propertyGym: false,
  propertyFunctionalArea: false,
  propertyOpen24h: false,
  propertyCarPark: false,
  propertyBalcony: false,
  propertySauna: false,
  propertySteamSauna: false,
  propertyAttraction: false,
  propertyGoodPrice: false,
  propertyIsSold: false,
  propertyIsRent: false,
  propertyAvailable: false,
  propertyRefrigerator: false,
  propertyAirconditioner: false
};
