import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IPhoto } from 'app/shared/model/photo.model';
import { getEntities as getPhotos } from 'app/entities/photo/photo.reducer';
import { ITag } from 'app/shared/model/tag.model';
import { getEntities as getTags } from 'app/entities/tag/tag.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './residential-area.reducer';
import { IResidentialArea } from 'app/shared/model/residential-area.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IResidentialAreaUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IResidentialAreaUpdateState {
  isNew: boolean;
  idstag: any[];
  photoId: number;
}

export class ResidentialAreaUpdate extends React.Component<IResidentialAreaUpdateProps, IResidentialAreaUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      idstag: [],
      photoId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getPhotos();
    this.props.getTags();
  }

  onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => this.props.setBlob(name, data, contentType), isAnImage);
  };

  clearBlob = name => () => {
    this.props.setBlob(name, undefined, undefined);
  };

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { residentialAreaEntity } = this.props;
      const entity = {
        ...residentialAreaEntity,
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
    this.props.history.push('/entity/residential-area');
  };

  photoUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        photoId: -1
      });
    } else {
      for (const i in this.props.photos) {
        if (id === this.props.photos[i].id.toString()) {
          this.setState({
            photoId: this.props.photos[i].id
          });
        }
      }
    }
  };

  tagUpdate = element => {
    const selected = Array.from(element.target.selectedOptions).map((e: any) => e.value);
    this.setState({
      idstag: keysToValues(selected, this.props.tags, 'tagName')
    });
  };

  displaytag(value: any) {
    if (this.state.idstag && this.state.idstag.length !== 0) {
      const list = [];
      for (const i in this.state.idstag) {
        if (this.state.idstag[i]) {
          list.push(this.state.idstag[i].tagName);
        }
      }
      return list;
    }
    if (value.tags && value.tags.length !== 0) {
      const list = [];
      for (const i in value.tags) {
        if (value.tags[i]) {
          list.push(value.tags[i].tagName);
        }
      }
      this.setState({
        idstag: keysToValues(list, this.props.tags, 'tagName')
      });
      return list;
    }
    return null;
  }

  render() {
    const isInvalid = false;
    const { residentialAreaEntity, photos, tags, loading, updating } = this.props;
    const { isNew } = this.state;

    const {
      residentialDescription,
      residentialDetail,
      residentialBoundary,
      residentialImage,
      residentialImageContentType
    } = residentialAreaEntity;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="riverApp.residentialArea.home.createOrEditLabel">
              <Translate contentKey="riverApp.residentialArea.home.createOrEditLabel">Create or edit a ResidentialArea</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : residentialAreaEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="residential-area-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="residentialNameLabel" for="residentialName">
                    <Translate contentKey="riverApp.residentialArea.residentialName">Residential Name</Translate>
                  </Label>
                  <AvField
                    id="residential-area-residentialName"
                    type="text"
                    name="residentialName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="residentialAliasLabel" for="residentialAlias">
                    <Translate contentKey="riverApp.residentialArea.residentialAlias">Residential Alias</Translate>
                  </Label>
                  <AvField
                    id="residential-area-residentialAlias"
                    type="text"
                    name="residentialAlias"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="residentialDescriptionLabel" for="residentialDescription">
                    <Translate contentKey="riverApp.residentialArea.residentialDescription">Residential Description</Translate>
                  </Label>
                  <AvField id="residential-area-residentialDescription" type="text" name="residentialDescription" />
                </AvGroup>
                <AvGroup>
                  <Label id="residentialDetailLabel" for="residentialDetail">
                    <Translate contentKey="riverApp.residentialArea.residentialDetail">Residential Detail</Translate>
                  </Label>
                  <AvField id="residential-area-residentialDetail" type="text" name="residentialDetail" />
                </AvGroup>
                <AvGroup>
                  <Label id="residentialProvinceLabel" for="residentialProvince">
                    <Translate contentKey="riverApp.residentialArea.residentialProvince">Residential Province</Translate>
                  </Label>
                  <AvField id="residential-area-residentialProvince" type="text" name="residentialProvince" />
                </AvGroup>
                <AvGroup>
                  <Label id="residentialDistrictLabel" for="residentialDistrict">
                    <Translate contentKey="riverApp.residentialArea.residentialDistrict">Residential District</Translate>
                  </Label>
                  <AvField id="residential-area-residentialDistrict" type="text" name="residentialDistrict" />
                </AvGroup>
                <AvGroup>
                  <Label id="residentialBoundaryLabel" for="residentialBoundary">
                    <Translate contentKey="riverApp.residentialArea.residentialBoundary">Residential Boundary</Translate>
                  </Label>
                  <AvField id="residential-area-residentialBoundary" type="text" name="residentialBoundary" />
                </AvGroup>
                <AvGroup>
                  <AvGroup>
                    <Label id="residentialImageLabel" for="residentialImage">
                      <Translate contentKey="riverApp.residentialArea.residentialImage">Residential Image</Translate>
                    </Label>
                    <br />
                    {residentialImage ? (
                      <div>
                        <a onClick={openFile(residentialImageContentType, residentialImage)}>
                          <img src={`data:${residentialImageContentType};base64,${residentialImage}`} style={{ maxHeight: '100px' }} />
                        </a>
                        <br />
                        <Row>
                          <Col md="11">
                            <span>
                              {residentialImageContentType}, {byteSize(residentialImage)}
                            </span>
                          </Col>
                          <Col md="1">
                            <Button color="danger" onClick={this.clearBlob('residentialImage')}>
                              <FontAwesomeIcon icon="times-circle" />
                            </Button>
                          </Col>
                        </Row>
                      </div>
                    ) : null}
                    <input id="file_residentialImage" type="file" onChange={this.onBlobChange(true, 'residentialImage')} accept="image/*" />
                  </AvGroup>
                </AvGroup>
                <AvGroup>
                  <Label for="photo.id">
                    <Translate contentKey="riverApp.residentialArea.photo">Photo</Translate>
                  </Label>
                  <AvInput id="residential-area-photo" type="select" className="form-control" name="photoId" onChange={this.photoUpdate}>
                    <option value="" key="0" />
                    {photos
                      ? photos.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="tags">
                    <Translate contentKey="riverApp.residentialArea.tag">Tag</Translate>
                  </Label>
                  <AvInput
                    id="residential-area-tag"
                    type="select"
                    multiple
                    className="form-control"
                    name="faketags"
                    value={this.displaytag(residentialAreaEntity)}
                    onChange={this.tagUpdate}
                  >
                    <option value="" key="0" />
                    {tags
                      ? tags.map(otherEntity => (
                          <option value={otherEntity.tagName} key={otherEntity.id}>
                            {otherEntity.tagName}
                          </option>
                        ))
                      : null}
                  </AvInput>
                  <AvInput id="residential-area-tag" type="hidden" name="tags" value={this.state.idstag} />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/residential-area" replace color="info">
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
  photos: storeState.photo.entities,
  tags: storeState.tag.entities,
  residentialAreaEntity: storeState.residentialArea.entity,
  loading: storeState.residentialArea.loading,
  updating: storeState.residentialArea.updating
});

const mapDispatchToProps = {
  getPhotos,
  getTags,
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
)(ResidentialAreaUpdate);