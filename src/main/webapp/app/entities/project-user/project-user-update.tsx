import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { IProject } from 'app/shared/model/project.model';
import { getEntities as getProjects } from 'app/entities/project/project.reducer';
import { getEntity, updateEntity, createEntity, reset } from './project-user.reducer';
import { IProjectUser } from 'app/shared/model/project-user.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IProjectUserUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IProjectUserUpdateState {
  isNew: boolean;
  userId: number;
  projectId: number;
}

export class ProjectUserUpdate extends React.Component<IProjectUserUpdateProps, IProjectUserUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      userId: 0,
      projectId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getUsers();
    this.props.getProjects();
  }

  saveEntity = (event, errors, values) => {
    values.likedDate = new Date(values.likedDate);
    values.sharedDate = new Date(values.sharedDate);

    if (errors.length === 0) {
      const { projectUserEntity } = this.props;
      const entity = {
        ...projectUserEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
      this.handleClose();
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/project-user');
  };

  userUpdate = element => {
    const login = element.target.value.toString();
    if (login === '') {
      this.setState({
        userId: -1
      });
    } else {
      for (const i in this.props.users) {
        if (login === this.props.users[i].login.toString()) {
          this.setState({
            userId: this.props.users[i].id
          });
        }
      }
    }
  };

  projectUpdate = element => {
    const projectName = element.target.value.toString();
    if (projectName === '') {
      this.setState({
        projectId: -1
      });
    } else {
      for (const i in this.props.projects) {
        if (projectName === this.props.projects[i].projectName.toString()) {
          this.setState({
            projectId: this.props.projects[i].id
          });
        }
      }
    }
  };

  render() {
    const isInvalid = false;
    const { projectUserEntity, users, projects, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="riverApp.projectUser.home.createOrEditLabel">
              <Translate contentKey="riverApp.projectUser.home.createOrEditLabel">Create or edit a ProjectUser</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : projectUserEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="project-user-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="likedLabel" check>
                    <AvInput id="project-user-liked" type="checkbox" className="form-control" name="liked" />
                    <Translate contentKey="riverApp.projectUser.liked">Liked</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="sharedLabel" check>
                    <AvInput id="project-user-shared" type="checkbox" className="form-control" name="shared" />
                    <Translate contentKey="riverApp.projectUser.shared">Shared</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="likedDateLabel" for="likedDate">
                    <Translate contentKey="riverApp.projectUser.likedDate">Liked Date</Translate>
                  </Label>
                  <AvInput
                    id="project-user-likedDate"
                    type="datetime-local"
                    className="form-control"
                    name="likedDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.projectUserEntity.likedDate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="sharedDateLabel" for="sharedDate">
                    <Translate contentKey="riverApp.projectUser.sharedDate">Shared Date</Translate>
                  </Label>
                  <AvInput
                    id="project-user-sharedDate"
                    type="datetime-local"
                    className="form-control"
                    name="sharedDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.projectUserEntity.sharedDate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="user.login">
                    <Translate contentKey="riverApp.projectUser.user">User</Translate>
                  </Label>
                  <AvInput id="project-user-user" type="select" className="form-control" name="userId" onChange={this.userUpdate}>
                    <option value="" key="0" />
                    {users
                      ? users.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.login}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="project.projectName">
                    <Translate contentKey="riverApp.projectUser.project">Project</Translate>
                  </Label>
                  <AvInput id="project-user-project" type="select" className="form-control" name="projectId" onChange={this.projectUpdate}>
                    <option value="" key="0" />
                    {projects
                      ? projects.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.projectName}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/project-user" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={isInvalid || updating}>
                  <FontAwesomeIcon icon="save" />&nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  users: storeState.userManagement.users,
  projects: storeState.project.entities,
  projectUserEntity: storeState.projectUser.entity,
  loading: storeState.projectUser.loading,
  updating: storeState.projectUser.updating
});

const mapDispatchToProps = {
  getUsers,
  getProjects,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ProjectUserUpdate);
