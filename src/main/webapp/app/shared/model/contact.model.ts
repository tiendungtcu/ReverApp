export interface IContact {
  id?: number;
  contactName?: string;
  contactPhone?: string;
  contactAddress?: string;
  contactWebsite?: string;
  contactAvatarUrl?: string;
  contactFacebook?: string;
  contactTwitter?: string;
  contactInstagram?: string;
  contactLinkedin?: string;
  contactGooglePlus?: string;
  contactYoutube?: string;
  contactStatus?: boolean;
  userId?: number;
}

export const defaultValue: Readonly<IContact> = {
  contactStatus: false
};
