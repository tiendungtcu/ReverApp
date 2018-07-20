import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './project-user.reducer';
import { IProjectUser } from 'app/shared/model/project-user.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProjectUserProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class ProjectUser extends React.Component<IProjectUserProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { projectUserList, match } = this.props;
    return (
      <div>
        <h2 id="project-user-heading">
          <Translate contentKey="riverApp.projectUser.home.title">Project Users</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="riverApp.projectUser.home.createLabel">Create new Project User</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.projectUser.liked">Liked</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.projectUser.shared">Shared</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.projectUser.likedDate">Liked Date</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.projectUser.sharedDate">Shared Date</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.projectUser.user">User</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.projectUser.project">Project</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {projectUserList.map((projectUser, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${projectUser.id}`} color="link" size="sm">
                      {projectUser.id}
                    </Button>
                  </td>
                  <td>{projectUser.liked ? 'true' : 'false'}</td>
                  <td>{projectUser.shared ? 'true' : 'false'}</td>
                  <td>
                    <TextFormat type="date" value={projectUser.likedDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={projectUser.sharedDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{projectUser.userLogin ? projectUser.userLogin : ''}</td>
                  <td>
                    {projectUser.projectProjectName ? (
                      <Link to={`project/${projectUser.projectId}`}>{projectUser.projectProjectName}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${projectUser.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${projectUser.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${projectUser.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ projectUser }: IRootState) => ({
  projectUserList: projectUser.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ProjectUser);
