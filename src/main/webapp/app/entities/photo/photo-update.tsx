import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IProperty } from 'app/shared/model/property.model';
import { getEntities as getProperties } from 'app/entities/property/property.reducer';
import { IProject } from 'app/shared/model/project.model';
import { getEntities as getProjects } from 'app/entities/project/project.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './photo.reducer';
import { IPhoto } from 'app/shared/model/photo.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IPhotoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IPhotoUpdateState {
  isNew: boolean;
  propertyId: number;
  projectId: number;
}

export class PhotoUpdate extends React.Component<IPhotoUpdateProps, IPhotoUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      propertyId: 0,
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

    this.props.getProperties();
    this.props.getProjects();
  }

  onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => this.props.setBlob(name, data, contentType), isAnImage);
  };

  clearBlob = name => () => {
    this.props.setBlob(name, undefined, undefined);
  };

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { photoEntity } = this.props;
      const entity = {
        ...photoEntity,
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
    this.props.history.push('/entity/photo');
  };

  render() {
    const isInvalid = false;
    const { photoEntity, properties, projects, loading, updating } = this.props;
    const { isNew } = this.state;

    const { photoImage, photoImageContentType } = photoEntity;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="riverApp.photo.home.createOrEditLabel">
              <Translate contentKey="riverApp.photo.home.createOrEditLabel">Create or edit a Photo</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : photoEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="photo-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="photoNameLabel" for="photoName">
                    <Translate contentKey="riverApp.photo.photoName">Photo Name</Translate>
                  </Label>
                  <AvField
                    id="photo-photoName"
                    type="text"
                    name="photoName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <AvGroup>
                    <Label id="photoImageLabel" for="photoImage">
                      <Translate contentKey="riverApp.photo.photoImage">Photo Image</Translate>
                    </Label>
                    <br />
                    {photoImage ? (
                      <div>
                        <a onClick={openFile(photoImageContentType, photoImage)}>
                          <img src={`data:${photoImageContentType};base64,${photoImage}`} style={{ maxHeight: '100px' }} />
                        </a>
                        <br />
                        <Row>
                          <Col md="11">
                            <span>
                              {photoImageContentType}, {byteSize(photoImage)}
                            </span>
                          </Col>
                          <Col md="1">
                            <Button color="danger" onClick={this.clearBlob('photoImage')}>
                              <FontAwesomeIcon icon="times-circle" />
                            </Button>
                          </Col>
                        </Row>
                      </div>
                    ) : null}
                    <input id="file_photoImage" type="file" onChange={this.onBlobChange(true, 'photoImage')} accept="image/*" />
                  </AvGroup>
                </AvGroup>
                <AvGroup>
                  <Label id="photoExtensionLabel" for="photoExtension">
                    <Translate contentKey="riverApp.photo.photoExtension">Photo Extension</Translate>
                  </Label>
                  <AvField id="photo-photoExtension" type="text" name="photoExtension" />
                </AvGroup>
                <AvGroup>
                  <Label id="photoUrlLabel" for="photoUrl">
                    <Translate contentKey="riverApp.photo.photoUrl">Photo Url</Translate>
                  </Label>
                  <AvField id="photo-photoUrl" type="text" name="photoUrl" />
                </AvGroup>
                <AvGroup>
                  <Label id="photoThumbnailUrlLabel" for="photoThumbnailUrl">
                    <Translate contentKey="riverApp.photo.photoThumbnailUrl">Photo Thumbnail Url</Translate>
                  </Label>
                  <AvField id="photo-photoThumbnailUrl" type="text" name="photoThumbnailUrl" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/photo" replace color="info">
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
  properties: storeState.property.entities,
  projects: storeState.project.entities,
  photoEntity: storeState.photo.entity,
  loading: storeState.photo.loading,
  updating: storeState.photo.updating
});

const mapDispatchToProps = {
  getProperties,
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
)(PhotoUpdate);
