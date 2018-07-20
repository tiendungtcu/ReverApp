import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Exchanger from './exchanger';
import ExchangerDetail from './exchanger-detail';
import ExchangerUpdate from './exchanger-update';
import ExchangerDeleteDialog from './exchanger-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ExchangerUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ExchangerUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ExchangerDetail} />
      <ErrorBoundaryRoute path={match.url} component={Exchanger} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ExchangerDeleteDialog} />
  </>
);

export default Routes;
