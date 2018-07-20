export interface IDepartment {
  id?: number;
  departmentName?: string;
  departmentPhotoContentType?: string;
  departmentPhoto?: any;
  departmentPhone?: string;
}

export const defaultValue: Readonly<IDepartment> = {};
