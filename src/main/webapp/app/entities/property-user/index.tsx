import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PropertyUser from './property-user';
import PropertyUserDetail from './property-user-detail';
import PropertyUserUpdate from './property-user-update';
import PropertyUserDeleteDialog from './property-user-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PropertyUserUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PropertyUserUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PropertyUserDetail} />
      <ErrorBoundaryRoute path={match.url} component={PropertyUser} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PropertyUserDeleteDialog} />
  </>
);

export default Routes;
