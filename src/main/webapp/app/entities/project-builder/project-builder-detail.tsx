import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './project-builder.reducer';
import { IProjectBuilder } from 'app/shared/model/project-builder.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProjectBuilderDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class ProjectBuilderDetail extends React.Component<IProjectBuilderDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { projectBuilderEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="riverApp.projectBuilder.detail.title">ProjectBuilder</Translate> [<b>{projectBuilderEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="builderName">
                <Translate contentKey="riverApp.projectBuilder.builderName">Builder Name</Translate>
              </span>
            </dt>
            <dd>{projectBuilderEntity.builderName}</dd>
            <dt>
              <span id="builderTitle">
                <Translate contentKey="riverApp.projectBuilder.builderTitle">Builder Title</Translate>
              </span>
            </dt>
            <dd>{projectBuilderEntity.builderTitle}</dd>
            <dt>
              <span id="builderDate">
                <Translate contentKey="riverApp.projectBuilder.builderDate">Builder Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={projectBuilderEntity.builderDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="builderDescription">
                <Translate contentKey="riverApp.projectBuilder.builderDescription">Builder Description</Translate>
              </span>
            </dt>
            <dd>{projectBuilderEntity.builderDescription}</dd>
            <dt>
              <span id="builderAddress">
                <Translate contentKey="riverApp.projectBuilder.builderAddress">Builder Address</Translate>
              </span>
            </dt>
            <dd>{projectBuilderEntity.builderAddress}</dd>
            <dt>
              <span id="builderWebsite">
                <Translate contentKey="riverApp.projectBuilder.builderWebsite">Builder Website</Translate>
              </span>
            </dt>
            <dd>{projectBuilderEntity.builderWebsite}</dd>
            <dt>
              <span id="builderPhone">
                <Translate contentKey="riverApp.projectBuilder.builderPhone">Builder Phone</Translate>
              </span>
            </dt>
            <dd>{projectBuilderEntity.builderPhone}</dd>
            <dt>
              <span id="builderPhoto">
                <Translate contentKey="riverApp.projectBuilder.builderPhoto">Builder Photo</Translate>
              </span>
            </dt>
            <dd>
              {projectBuilderEntity.builderPhoto ? (
                <div>
                  <a onClick={openFile(projectBuilderEntity.builderPhotoContentType, projectBuilderEntity.builderPhoto)}>
                    <img
                      src={`data:${projectBuilderEntity.builderPhotoContentType};base64,${projectBuilderEntity.builderPhoto}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                  <span>
                    {projectBuilderEntity.builderPhotoContentType}, {byteSize(projectBuilderEntity.builderPhoto)}
                  </span>
                </div>
              ) : null}
            </dd>
          </dl>
          <Button tag={Link} to="/entity/project-builder" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/project-builder/${projectBuilderEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ projectBuilder }: IRootState) => ({
  projectBuilderEntity: projectBuilder.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ProjectBuilderDetail);
