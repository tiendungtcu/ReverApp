import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './photo.reducer';
import { IPhoto } from 'app/shared/model/photo.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IPhotoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IPhotoUpdateState {
  isNew: boolean;
}

export class PhotoUpdate extends React.Component<IPhotoUpdateProps, IPhotoUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    values.photoDate = new Date(values.photoDate);

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
    const { photoEntity, loading, updating } = this.props;
    const { isNew } = this.state;

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
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 256, errorMessage: translate('entity.validation.maxlength', { max: 256 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="photoDateLabel" for="photoDate">
                    <Translate contentKey="riverApp.photo.photoDate">Photo Date</Translate>
                  </Label>
                  <AvInput
                    id="photo-photoDate"
                    type="datetime-local"
                    className="form-control"
                    name="photoDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.photoEntity.photoDate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="photoUrlLabel" for="photoUrl">
                    <Translate contentKey="riverApp.photo.photoUrl">Photo Url</Translate>
                  </Label>
                  <AvField id="photo-photoUrl" type="text" name="photoUrl" />
                </AvGroup>
                <AvGroup>
                  <Label id="photoMimeTypeLabel" for="photoMimeType">
                    <Translate contentKey="riverApp.photo.photoMimeType">Photo Mime Type</Translate>
                  </Label>
                  <AvField id="photo-photoMimeType" type="text" name="photoMimeType" />
                </AvGroup>
                <AvGroup>
                  <Label id="resourceIdLabel" for="resourceId">
                    <Translate contentKey="riverApp.photo.resourceId">Resource Id</Translate>
                  </Label>
                  <AvField id="photo-resourceId" type="number" className="form-control" name="resourceId" />
                </AvGroup>
                <AvGroup>
                  <Label id="resourceTypeLabel">
                    <Translate contentKey="riverApp.photo.resourceType">Resource Type</Translate>
                  </Label>
                  <AvInput
                    id="photo-resourceType"
                    type="select"
                    className="form-control"
                    name="resourceType"
                    value={(!isNew && photoEntity.resourceType) || 'PROJECT'}
                  >
                    <option value="PROJECT">PROJECT</option>
                    <option value="PROPERTY">PROPERTY</option>
                    <option value="EMPLOYEE">EMPLOYEE</option>
                    <option value="RESIDENTIAL_AREA">RESIDENTIAL_AREA</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="photoSizeLabel" for="photoSize">
                    <Translate contentKey="riverApp.photo.photoSize">Photo Size</Translate>
                  </Label>
                  <AvField id="photo-photoSize" type="number" className="form-control" name="photoSize" />
                </AvGroup>
                <AvGroup>
                  <Label id="photoAltTextLabel" for="photoAltText">
                    <Translate contentKey="riverApp.photo.photoAltText">Photo Alt Text</Translate>
                  </Label>
                  <AvField id="photo-photoAltText" type="text" name="photoAltText" />
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
  photoEntity: storeState.photo.entity,
  loading: storeState.photo.loading,
  updating: storeState.photo.updating
});

const mapDispatchToProps = {
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
)(PhotoUpdate);
