import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import BuildingType from './building-type';
import BuildingTypeDetail from './building-type-detail';
import BuildingTypeUpdate from './building-type-update';
import BuildingTypeDeleteDialog from './building-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BuildingTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BuildingTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BuildingTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={BuildingType} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={BuildingTypeDeleteDialog} />
  </>
);

export default Routes;
