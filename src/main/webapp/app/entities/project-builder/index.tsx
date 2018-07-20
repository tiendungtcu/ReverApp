import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ProjectBuilder from './project-builder';
import ProjectBuilderDetail from './project-builder-detail';
import ProjectBuilderUpdate from './project-builder-update';
import ProjectBuilderDeleteDialog from './project-builder-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ProjectBuilderUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ProjectBuilderUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ProjectBuilderDetail} />
      <ErrorBoundaryRoute path={match.url} component={ProjectBuilder} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ProjectBuilderDeleteDialog} />
  </>
);

export default Routes;
