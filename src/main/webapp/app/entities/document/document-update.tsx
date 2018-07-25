import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './document.reducer';
import { IDocument } from 'app/shared/model/document.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IDocumentUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IDocumentUpdateState {
  isNew: boolean;
}

export class DocumentUpdate extends React.Component<IDocumentUpdateProps, IDocumentUpdateState> {
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

  render() {
    const isInvalid = false;
    const { documentEntity, loading, updating } = this.props;
    const { isNew } = this.state;

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
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 256, errorMessage: translate('entity.validation.maxlength', { max: 256 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="documentUrlLabel" for="documentUrl">
                    <Translate contentKey="riverApp.document.documentUrl">Document Url</Translate>
                  </Label>
                  <AvField
                    id="document-documentUrl"
                    type="text"
                    name="documentUrl"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
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
                  <Label id="documentMimeTypeLabel" for="documentMimeType">
                    <Translate contentKey="riverApp.document.documentMimeType">Document Mime Type</Translate>
                  </Label>
                  <AvField id="document-documentMimeType" type="text" name="documentMimeType" />
                </AvGroup>
                <AvGroup>
                  <Label id="documentSizeLabel" for="documentSize">
                    <Translate contentKey="riverApp.document.documentSize">Document Size</Translate>
                  </Label>
                  <AvField id="document-documentSize" type="text" name="documentSize" />
                </AvGroup>
                <AvGroup>
                  <Label id="resourceTypeLabel">
                    <Translate contentKey="riverApp.document.resourceType">Resource Type</Translate>
                  </Label>
                  <AvInput
                    id="document-resourceType"
                    type="select"
                    className="form-control"
                    name="resourceType"
                    value={(!isNew && documentEntity.resourceType) || 'PROJECT'}
                  >
                    <option value="PROJECT">PROJECT</option>
                    <option value="PROPERTY">PROPERTY</option>
                    <option value="EMPLOYEE">EMPLOYEE</option>
                    <option value="RESIDENTIAL_AREA">RESIDENTIAL_AREA</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="resourceIdLabel" for="resourceId">
                    <Translate contentKey="riverApp.document.resourceId">Resource Id</Translate>
                  </Label>
                  <AvField id="document-resourceId" type="number" className="form-control" name="resourceId" />
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
  documentEntity: storeState.document.entity,
  loading: storeState.document.loading,
  updating: storeState.document.updating
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
)(DocumentUpdate);
