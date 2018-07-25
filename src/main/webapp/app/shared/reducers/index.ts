import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import company, {
  CompanyState
} from 'app/entities/company/company.reducer';
// prettier-ignore
import jobTitle, {
  JobTitleState
} from 'app/entities/job-title/job-title.reducer';
// prettier-ignore
import exchanger, {
  ExchangerState
} from 'app/entities/exchanger/exchanger.reducer';
// prettier-ignore
import department, {
  DepartmentState
} from 'app/entities/department/department.reducer';
// prettier-ignore
import investor, {
  InvestorState
} from 'app/entities/investor/investor.reducer';
// prettier-ignore
import contractor, {
  ContractorState
} from 'app/entities/contractor/contractor.reducer';
// prettier-ignore
import document, {
  DocumentState
} from 'app/entities/document/document.reducer';
// prettier-ignore
import photo, {
  PhotoState
} from 'app/entities/photo/photo.reducer';
// prettier-ignore
import location, {
  LocationState
} from 'app/entities/location/location.reducer';
// prettier-ignore
import buildingType, {
  BuildingTypeState
} from 'app/entities/building-type/building-type.reducer';
// prettier-ignore
import category, {
  CategoryState
} from 'app/entities/category/category.reducer';
// prettier-ignore
import tag, {
  TagState
} from 'app/entities/tag/tag.reducer';
// prettier-ignore
import notification, {
  NotificationState
} from 'app/entities/notification/notification.reducer';
// prettier-ignore
import supportCategory, {
  SupportCategoryState
} from 'app/entities/support-category/support-category.reducer';
// prettier-ignore
import contact, {
  ContactState
} from 'app/entities/contact/contact.reducer';
// prettier-ignore
import userNotification, {
  UserNotificationState
} from 'app/entities/user-notification/user-notification.reducer';
// prettier-ignore
import article, {
  ArticleState
} from 'app/entities/article/article.reducer';
// prettier-ignore
import employee, {
  EmployeeState
} from 'app/entities/employee/employee.reducer';
// prettier-ignore
import residentialArea, {
  ResidentialAreaState
} from 'app/entities/residential-area/residential-area.reducer';
// prettier-ignore
import project, {
  ProjectState
} from 'app/entities/project/project.reducer';
// prettier-ignore
import property, {
  PropertyState
} from 'app/entities/property/property.reducer';
// prettier-ignore
import favouriteItem, {
  FavouriteItemState
} from 'app/entities/favourite-item/favourite-item.reducer';
// prettier-ignore
import blogPost, {
  BlogPostState
} from 'app/entities/blog-post/blog-post.reducer';
// prettier-ignore
import recruitmentInfo, {
  RecruitmentInfoState
} from 'app/entities/recruitment-info/recruitment-info.reducer';
// prettier-ignore
import request, {
  RequestState
} from 'app/entities/request/request.reducer';
// prettier-ignore
import comment, {
  CommentState
} from 'app/entities/comment/comment.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly company: CompanyState;
  readonly jobTitle: JobTitleState;
  readonly exchanger: ExchangerState;
  readonly department: DepartmentState;
  readonly investor: InvestorState;
  readonly contractor: ContractorState;
  readonly document: DocumentState;
  readonly photo: PhotoState;
  readonly location: LocationState;
  readonly buildingType: BuildingTypeState;
  readonly category: CategoryState;
  readonly tag: TagState;
  readonly notification: NotificationState;
  readonly supportCategory: SupportCategoryState;
  readonly contact: ContactState;
  readonly userNotification: UserNotificationState;
  readonly article: ArticleState;
  readonly employee: EmployeeState;
  readonly residentialArea: ResidentialAreaState;
  readonly project: ProjectState;
  readonly property: PropertyState;
  readonly favouriteItem: FavouriteItemState;
  readonly blogPost: BlogPostState;
  readonly recruitmentInfo: RecruitmentInfoState;
  readonly request: RequestState;
  readonly comment: CommentState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  company,
  jobTitle,
  exchanger,
  department,
  investor,
  contractor,
  document,
  photo,
  location,
  buildingType,
  category,
  tag,
  notification,
  supportCategory,
  contact,
  userNotification,
  article,
  employee,
  residentialArea,
  project,
  property,
  favouriteItem,
  blogPost,
  recruitmentInfo,
  request,
  comment,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
