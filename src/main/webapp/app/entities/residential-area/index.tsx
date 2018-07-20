import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ResidentialArea from './residential-area';
import ResidentialAreaDetail from './residential-area-detail';
import ResidentialAreaUpdate from './residential-area-update';
import ResidentialAreaDeleteDialog from './residential-area-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ResidentialAreaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ResidentialAreaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ResidentialAreaDetail} />
      <ErrorBoundaryRoute path={match.url} component={ResidentialArea} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ResidentialAreaDeleteDialog} />
  </>
);

export default Routes;
