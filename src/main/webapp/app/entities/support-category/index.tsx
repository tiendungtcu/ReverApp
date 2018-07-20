import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SupportCategory from './support-category';
import SupportCategoryDetail from './support-category-detail';
import SupportCategoryUpdate from './support-category-update';
import SupportCategoryDeleteDialog from './support-category-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SupportCategoryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SupportCategoryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SupportCategoryDetail} />
      <ErrorBoundaryRoute path={match.url} component={SupportCategory} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={SupportCategoryDeleteDialog} />
  </>
);

export default Routes;
