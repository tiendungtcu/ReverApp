export interface IExchanger {
  id?: number;
  exchangerName?: string;
  exchangerAddress?: string;
  exchangerPhone?: string;
  exchangerAvatarUrl?: string;
}

export const defaultValue: Readonly<IExchanger> = {};
