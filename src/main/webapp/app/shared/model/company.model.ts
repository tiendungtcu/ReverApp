export interface ICompany {
  id?: number;
  companyName?: string;
  companyPhone?: string;
  companyAddress?: string;
  companyLogoContentType?: string;
  companyLogo?: any;
  companyWebsite?: string;
  companyFacebook?: string;
  companyTwitter?: string;
  companyInstagram?: string;
  companyLinkedin?: string;
  companyGooglePlus?: string;
  companyYoutube?: string;
  companyDescription?: any;
}

export const defaultValue: Readonly<ICompany> = {};
