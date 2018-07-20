import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Company from './company';
import Contact from './contact';
import JobTitle from './job-title';
import Exchanger from './exchanger';
import Department from './department';
import Investor from './investor';
import ProjectBuilder from './project-builder';
import Document from './document';
import Photo from './photo';
import Location from './location';
import BuildingType from './building-type';
import Category from './category';
import Tag from './tag';
import Notification from './notification';
import SupportCategory from './support-category';
import Article from './article';
import Employee from './employee';
import ResidentialArea from './residential-area';
import Project from './project';
import Property from './property';
import BlogPost from './blog-post';
import RecruitmentInfo from './recruitment-info';
import Request from './request';
import Comment from './comment';
import ProjectUser from './project-user';
import PropertyUser from './property-user';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/company`} component={Company} />
      <ErrorBoundaryRoute path={`${match.url}/contact`} component={Contact} />
      <ErrorBoundaryRoute path={`${match.url}/job-title`} component={JobTitle} />
      <ErrorBoundaryRoute path={`${match.url}/exchanger`} component={Exchanger} />
      <ErrorBoundaryRoute path={`${match.url}/department`} component={Department} />
      <ErrorBoundaryRoute path={`${match.url}/investor`} component={Investor} />
      <ErrorBoundaryRoute path={`${match.url}/project-builder`} component={ProjectBuilder} />
      <ErrorBoundaryRoute path={`${match.url}/document`} component={Document} />
      <ErrorBoundaryRoute path={`${match.url}/photo`} component={Photo} />
      <ErrorBoundaryRoute path={`${match.url}/location`} component={Location} />
      <ErrorBoundaryRoute path={`${match.url}/building-type`} component={BuildingType} />
      <ErrorBoundaryRoute path={`${match.url}/category`} component={Category} />
      <ErrorBoundaryRoute path={`${match.url}/tag`} component={Tag} />
      <ErrorBoundaryRoute path={`${match.url}/notification`} component={Notification} />
      <ErrorBoundaryRoute path={`${match.url}/support-category`} component={SupportCategory} />
      <ErrorBoundaryRoute path={`${match.url}/article`} component={Article} />
      <ErrorBoundaryRoute path={`${match.url}/employee`} component={Employee} />
      <ErrorBoundaryRoute path={`${match.url}/residential-area`} component={ResidentialArea} />
      <ErrorBoundaryRoute path={`${match.url}/project`} component={Project} />
      <ErrorBoundaryRoute path={`${match.url}/property`} component={Property} />
      <ErrorBoundaryRoute path={`${match.url}/blog-post`} component={BlogPost} />
      <ErrorBoundaryRoute path={`${match.url}/recruitment-info`} component={RecruitmentInfo} />
      <ErrorBoundaryRoute path={`${match.url}/request`} component={Request} />
      <ErrorBoundaryRoute path={`${match.url}/comment`} component={Comment} />
      <ErrorBoundaryRoute path={`${match.url}/project-user`} component={ProjectUser} />
      <ErrorBoundaryRoute path={`${match.url}/property-user`} component={PropertyUser} />
      {/* jhipster-needle-add-route-path - JHipster will routes here */}
    </Switch>
  </div>
);

export default Routes;
