export interface IContact {
  id?: number;
  contactName?: string;
  contactPhone?: string;
  contactAddress?: string;
  contactEmail?: string;
  contactWebsite?: string;
  contactPhotoContentType?: string;
  contactPhoto?: any;
  contactFacebook?: string;
  contactTwitter?: string;
  contactInstagram?: string;
  contactLinkedin?: string;
  contactGooglePlus?: string;
  contactYoutube?: string;
  contactStatus?: boolean;
}

export const defaultValue: Readonly<IContact> = {
  contactStatus: false
};
