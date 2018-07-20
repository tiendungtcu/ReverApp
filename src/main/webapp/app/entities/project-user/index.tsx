import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ProjectUser from './project-user';
import ProjectUserDetail from './project-user-detail';
import ProjectUserUpdate from './project-user-update';
import ProjectUserDeleteDialog from './project-user-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ProjectUserUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ProjectUserUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ProjectUserDetail} />
      <ErrorBoundaryRoute path={match.url} component={ProjectUser} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ProjectUserDeleteDialog} />
  </>
);

export default Routes;
