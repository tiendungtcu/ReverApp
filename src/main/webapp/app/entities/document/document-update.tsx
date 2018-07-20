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
import { IProject } from 'app/shared/model/project.model';
import { getEntities as getProjects } from 'app/entities/project/project.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './document.reducer';
import { IDocument } from 'app/shared/model/document.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IDocumentUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IDocumentUpdateState {
  isNew: boolean;
  photoId: number;
  projectId: number;
}

export class DocumentUpdate extends React.Component<IDocumentUpdateProps, IDocumentUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      photoId: 0,
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

    this.props.getPhotos();
    this.props.getProjects();
  }

  onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => this.props.setBlob(name, data, contentType), isAnImage);
  };

  clearBlob = name => () => {
    this.props.setBlob(name, undefined, undefined);
  };

  saveEntity = (event, errors, values) => {
    values.documentDate = new Date(values.documentDate);

    if (errors.length === 0) {
      const { documentEntity } = this.props;
      const entity = {
        ...documentEntity,
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
    this.props.history.push('/entity/document');
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

  render() {
    const isInvalid = false;
    const { documentEntity, photos, projects, loading, updating } = this.props;
    const { isNew } = this.state;

    const { documentContent, documentPhoto, documentPhotoContentType } = documentEntity;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="riverApp.document.home.createOrEditLabel">
              <Translate contentKey="riverApp.document.home.createOrEditLabel">Create or edit a Document</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : documentEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="document-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="documentNameLabel" for="documentName">
                    <Translate contentKey="riverApp.document.documentName">Document Name</Translate>
                  </Label>
                  <AvField
                    id="document-documentName"
                    type="text"
                    name="documentName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="documentUrlLabel" for="documentUrl">
                    <Translate contentKey="riverApp.document.documentUrl">Document Url</Translate>
                  </Label>
                  <AvField id="document-documentUrl" type="text" name="documentUrl" />
                </AvGroup>
                <AvGroup>
                  <Label id="documentDateLabel" for="documentDate">
                    <Translate contentKey="riverApp.document.documentDate">Document Date</Translate>
                  </Label>
                  <AvInput
                    id="document-documentDate"
                    type="datetime-local"
                    className="form-control"
                    name="documentDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.documentEntity.documentDate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="documentContentLabel" for="documentContent">
                    <Translate contentKey="riverApp.document.documentContent">Document Content</Translate>
                  </Label>
                  <AvField id="document-documentContent" type="text" name="documentContent" />
                </AvGroup>
                <AvGroup>
                  <AvGroup>
                    <Label id="documentPhotoLabel" for="documentPhoto">
                      <Translate contentKey="riverApp.document.documentPhoto">Document Photo</Translate>
                    </Label>
                    <br />
                    {documentPhoto ? (
                      <div>
                        <a onClick={openFile(documentPhotoContentType, documentPhoto)}>
                          <img src={`data:${documentPhotoContentType};base64,${documentPhoto}`} style={{ maxHeight: '100px' }} />
                        </a>
                        <br />
                        <Row>
                          <Col md="11">
                            <span>
                              {documentPhotoContentType}, {byteSize(documentPhoto)}
                            </span>
                          </Col>
                          <Col md="1">
                            <Button color="danger" onClick={this.clearBlob('documentPhoto')}>
                              <FontAwesomeIcon icon="times-circle" />
                            </Button>
                          </Col>
                        </Row>
                      </div>
                    ) : null}
                    <input id="file_documentPhoto" type="file" onChange={this.onBlobChange(true, 'documentPhoto')} accept="image/*" />
                  </AvGroup>
                </AvGroup>
                <AvGroup>
                  <Label id="documentTypeLabel">
                    <Translate contentKey="riverApp.document.documentType">Document Type</Translate>
                  </Label>
                  <AvInput
                    id="document-documentType"
                    type="select"
                    className="form-control"
                    name="documentType"
                    value={(!isNew && documentEntity.documentType) || 'PROJECT'}
                  >
                    <option value="PROJECT">PROJECT</option>
                    <option value="PROPERTY">PROPERTY</option>
                    <option value="EMPLOYEE">EMPLOYEE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="photo.id">
                    <Translate contentKey="riverApp.document.photo">Photo</Translate>
                  </Label>
                  <AvInput id="document-photo" type="select" className="form-control" name="photoId" onChange={this.photoUpdate}>
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
                <Button tag={Link} id="cancel-save" to="/entity/document" replace color="info">
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
  projects: storeState.project.entities,
  documentEntity: storeState.document.entity,
  loading: storeState.document.loading,
  updating: storeState.document.updating
});

const mapDispatchToProps = {
  getPhotos,
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
)(DocumentUpdate);
