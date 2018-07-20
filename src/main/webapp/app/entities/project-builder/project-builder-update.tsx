import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IProject } from 'app/shared/model/project.model';
import { getEntities as getProjects } from 'app/entities/project/project.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './project-builder.reducer';
import { IProjectBuilder } from 'app/shared/model/project-builder.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IProjectBuilderUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IProjectBuilderUpdateState {
  isNew: boolean;
  projectId: number;
}

export class ProjectBuilderUpdate extends React.Component<IProjectBuilderUpdateProps, IProjectBuilderUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
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

    this.props.getProjects();
  }

  onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => this.props.setBlob(name, data, contentType), isAnImage);
  };

  clearBlob = name => () => {
    this.props.setBlob(name, undefined, undefined);
  };

  saveEntity = (event, errors, values) => {
    values.builderDate = new Date(values.builderDate);

    if (errors.length === 0) {
      const { projectBuilderEntity } = this.props;
      const entity = {
        ...projectBuilderEntity,
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
    this.props.history.push('/entity/project-builder');
  };

  render() {
    const isInvalid = false;
    const { projectBuilderEntity, projects, loading, updating } = this.props;
    const { isNew } = this.state;

    const { builderPhoto, builderPhotoContentType } = projectBuilderEntity;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="riverApp.projectBuilder.home.createOrEditLabel">
              <Translate contentKey="riverApp.projectBuilder.home.createOrEditLabel">Create or edit a ProjectBuilder</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : projectBuilderEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="project-builder-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="builderNameLabel" for="builderName">
                    <Translate contentKey="riverApp.projectBuilder.builderName">Builder Name</Translate>
                  </Label>
                  <AvField
                    id="project-builder-builderName"
                    type="text"
                    name="builderName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="builderTitleLabel" for="builderTitle">
                    <Translate contentKey="riverApp.projectBuilder.builderTitle">Builder Title</Translate>
                  </Label>
                  <AvField id="project-builder-builderTitle" type="text" name="builderTitle" />
                </AvGroup>
                <AvGroup>
                  <Label id="builderDateLabel" for="builderDate">
                    <Translate contentKey="riverApp.projectBuilder.builderDate">Builder Date</Translate>
                  </Label>
                  <AvInput
                    id="project-builder-builderDate"
                    type="datetime-local"
                    className="form-control"
                    name="builderDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.projectBuilderEntity.builderDate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="builderDescriptionLabel" for="builderDescription">
                    <Translate contentKey="riverApp.projectBuilder.builderDescription">Builder Description</Translate>
                  </Label>
                  <AvField id="project-builder-builderDescription" type="text" name="builderDescription" />
                </AvGroup>
                <AvGroup>
                  <Label id="builderAddressLabel" for="builderAddress">
                    <Translate contentKey="riverApp.projectBuilder.builderAddress">Builder Address</Translate>
                  </Label>
                  <AvField id="project-builder-builderAddress" type="text" name="builderAddress" />
                </AvGroup>
                <AvGroup>
                  <Label id="builderWebsiteLabel" for="builderWebsite">
                    <Translate contentKey="riverApp.projectBuilder.builderWebsite">Builder Website</Translate>
                  </Label>
                  <AvField id="project-builder-builderWebsite" type="text" name="builderWebsite" />
                </AvGroup>
                <AvGroup>
                  <Label id="builderPhoneLabel" for="builderPhone">
                    <Translate contentKey="riverApp.projectBuilder.builderPhone">Builder Phone</Translate>
                  </Label>
                  <AvField id="project-builder-builderPhone" type="text" name="builderPhone" />
                </AvGroup>
                <AvGroup>
                  <AvGroup>
                    <Label id="builderPhotoLabel" for="builderPhoto">
                      <Translate contentKey="riverApp.projectBuilder.builderPhoto">Builder Photo</Translate>
                    </Label>
                    <br />
                    {builderPhoto ? (
                      <div>
                        <a onClick={openFile(builderPhotoContentType, builderPhoto)}>
                          <img src={`data:${builderPhotoContentType};base64,${builderPhoto}`} style={{ maxHeight: '100px' }} />
                        </a>
                        <br />
                        <Row>
                          <Col md="11">
                            <span>
                              {builderPhotoContentType}, {byteSize(builderPhoto)}
                            </span>
                          </Col>
                          <Col md="1">
                            <Button color="danger" onClick={this.clearBlob('builderPhoto')}>
                              <FontAwesomeIcon icon="times-circle" />
                            </Button>
                          </Col>
                        </Row>
                      </div>
                    ) : null}
                    <input id="file_builderPhoto" type="file" onChange={this.onBlobChange(true, 'builderPhoto')} accept="image/*" />
                  </AvGroup>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/project-builder" replace color="info">
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
  projects: storeState.project.entities,
  projectBuilderEntity: storeState.projectBuilder.entity,
  loading: storeState.projectBuilder.loading,
  updating: storeState.projectBuilder.updating
});

const mapDispatchToProps = {
  getProjects,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ProjectBuilderUpdate);
