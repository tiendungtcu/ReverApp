export interface ILocation {
  id?: number;
  locationName?: string;
  locationFullAddress?: string;
  locationNumber?: string;
  locationRoad?: string;
  locationWard?: string;
  locationDistrict?: string;
  locationProvince?: string;
  locationCountry?: string;
  locationGmapUrl?: string;
  longitude?: number;
  latitude?: number;
  locationHide?: boolean;
}

export const defaultValue: Readonly<ILocation> = {
  locationHide: false
};
