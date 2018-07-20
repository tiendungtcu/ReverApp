export interface IExchanger {
  id?: number;
  exchangerName?: string;
  exchangerAddress?: string;
  exchangerPhone?: string;
  exchangerPhotoContentType?: string;
  exchangerPhoto?: any;
}

export const defaultValue: Readonly<IExchanger> = {};
