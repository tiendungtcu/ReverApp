import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './project-user.reducer';
import { IProjectUser } from 'app/shared/model/project-user.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProjectUserDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class ProjectUserDetail extends React.Component<IProjectUserDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { projectUserEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="riverApp.projectUser.detail.title">ProjectUser</Translate> [<b>{projectUserEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="liked">
                <Translate contentKey="riverApp.projectUser.liked">Liked</Translate>
              </span>
            </dt>
            <dd>{projectUserEntity.liked ? 'true' : 'false'}</dd>
            <dt>
              <span id="shared">
                <Translate contentKey="riverApp.projectUser.shared">Shared</Translate>
              </span>
            </dt>
            <dd>{projectUserEntity.shared ? 'true' : 'false'}</dd>
            <dt>
              <span id="likedDate">
                <Translate contentKey="riverApp.projectUser.likedDate">Liked Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={projectUserEntity.likedDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="sharedDate">
                <Translate contentKey="riverApp.projectUser.sharedDate">Shared Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={projectUserEntity.sharedDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <Translate contentKey="riverApp.projectUser.user">User</Translate>
            </dt>
            <dd>{projectUserEntity.userLogin ? projectUserEntity.userLogin : ''}</dd>
            <dt>
              <Translate contentKey="riverApp.projectUser.project">Project</Translate>
            </dt>
            <dd>{projectUserEntity.projectProjectName ? projectUserEntity.projectProjectName : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/project-user" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/project-user/${projectUserEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ projectUser }: IRootState) => ({
  projectUserEntity: projectUser.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ProjectUserDetail);
