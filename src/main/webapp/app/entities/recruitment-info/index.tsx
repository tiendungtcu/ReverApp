import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import RecruitmentInfo from './recruitment-info';
import RecruitmentInfoDetail from './recruitment-info-detail';
import RecruitmentInfoUpdate from './recruitment-info-update';
import RecruitmentInfoDeleteDialog from './recruitment-info-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={RecruitmentInfoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={RecruitmentInfoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={RecruitmentInfoDetail} />
      <ErrorBoundaryRoute path={match.url} component={RecruitmentInfo} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={RecruitmentInfoDeleteDialog} />
  </>
);

export default Routes;
