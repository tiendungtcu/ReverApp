export interface ILocation {
  id?: number;
  locationName?: string;
  locationFullAddress?: string;
  locationNumber?: string;
  locationRoad?: string;
  locationWard?: string;
  locationDistrict?: string;
  locationProvince?: string;
  locationGmapUrl?: string;
  longitude?: number;
  latitude?: number;
  locationZipCode?: string;
}

export const defaultValue: Readonly<ILocation> = {};
