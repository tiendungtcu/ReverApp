export interface IDepartment {
  id?: number;
  departmentName?: string;
  departmentAvatarUrl?: string;
  departmentPhone?: string;
}

export const defaultValue: Readonly<IDepartment> = {};
