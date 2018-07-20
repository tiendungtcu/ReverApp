export interface ICategory {
  id?: number;
  categoryName?: string;
  categoryAlias?: string;
  categoryDescription?: string;
}

export const defaultValue: Readonly<ICategory> = {};
