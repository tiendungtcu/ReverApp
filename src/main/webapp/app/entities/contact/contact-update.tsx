import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, setBlob, reset } from './contact.reducer';
import { IContact } from 'app/shared/model/contact.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IContactUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IContactUpdateState {
  isNew: boolean;
}

export class ContactUpdate extends React.Component<IContactUpdateProps, IContactUpdateState> {
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

  onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => this.props.setBlob(name, data, contentType), isAnImage);
  };

  clearBlob = name => () => {
    this.props.setBlob(name, undefined, undefined);
  };

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { contactEntity } = this.props;
      const entity = {
        ...contactEntity,
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
    this.props.history.push('/entity/contact');
  };

  render() {
    const isInvalid = false;
    const { contactEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    const { contactPhoto, contactPhotoContentType } = contactEntity;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="riverApp.contact.home.createOrEditLabel">
              <Translate contentKey="riverApp.contact.home.createOrEditLabel">Create or edit a Contact</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : contactEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="contact-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="contactNameLabel" for="contactName">
                    <Translate contentKey="riverApp.contact.contactName">Contact Name</Translate>
                  </Label>
                  <AvField
                    id="contact-contactName"
                    type="text"
                    name="contactName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="contactPhoneLabel" for="contactPhone">
                    <Translate contentKey="riverApp.contact.contactPhone">Contact Phone</Translate>
                  </Label>
                  <AvField
                    id="contact-contactPhone"
                    type="text"
                    name="contactPhone"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="contactAddressLabel" for="contactAddress">
                    <Translate contentKey="riverApp.contact.contactAddress">Contact Address</Translate>
                  </Label>
                  <AvField id="contact-contactAddress" type="text" name="contactAddress" />
                </AvGroup>
                <AvGroup>
                  <Label id="contactEmailLabel" for="contactEmail">
                    <Translate contentKey="riverApp.contact.contactEmail">Contact Email</Translate>
                  </Label>
                  <AvField
                    id="contact-contactEmail"
                    type="text"
                    name="contactEmail"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="contactWebsiteLabel" for="contactWebsite">
                    <Translate contentKey="riverApp.contact.contactWebsite">Contact Website</Translate>
                  </Label>
                  <AvField id="contact-contactWebsite" type="text" name="contactWebsite" />
                </AvGroup>
                <AvGroup>
                  <AvGroup>
                    <Label id="contactPhotoLabel" for="contactPhoto">
                      <Translate contentKey="riverApp.contact.contactPhoto">Contact Photo</Translate>
                    </Label>
                    <br />
                    {contactPhoto ? (
                      <div>
                        <a onClick={openFile(contactPhotoContentType, contactPhoto)}>
                          <img src={`data:${contactPhotoContentType};base64,${contactPhoto}`} style={{ maxHeight: '100px' }} />
                        </a>
                        <br />
                        <Row>
                          <Col md="11">
                            <span>
                              {contactPhotoContentType}, {byteSize(contactPhoto)}
                            </span>
                          </Col>
                          <Col md="1">
                            <Button color="danger" onClick={this.clearBlob('contactPhoto')}>
                              <FontAwesomeIcon icon="times-circle" />
                            </Button>
                          </Col>
                        </Row>
                      </div>
                    ) : null}
                    <input id="file_contactPhoto" type="file" onChange={this.onBlobChange(true, 'contactPhoto')} accept="image/*" />
                  </AvGroup>
                </AvGroup>
                <AvGroup>
                  <Label id="contactFacebookLabel" for="contactFacebook">
                    <Translate contentKey="riverApp.contact.contactFacebook">Contact Facebook</Translate>
                  </Label>
                  <AvField id="contact-contactFacebook" type="text" name="contactFacebook" />
                </AvGroup>
                <AvGroup>
                  <Label id="contactTwitterLabel" for="contactTwitter">
                    <Translate contentKey="riverApp.contact.contactTwitter">Contact Twitter</Translate>
                  </Label>
                  <AvField id="contact-contactTwitter" type="text" name="contactTwitter" />
                </AvGroup>
                <AvGroup>
                  <Label id="contactInstagramLabel" for="contactInstagram">
                    <Translate contentKey="riverApp.contact.contactInstagram">Contact Instagram</Translate>
                  </Label>
                  <AvField id="contact-contactInstagram" type="text" name="contactInstagram" />
                </AvGroup>
                <AvGroup>
                  <Label id="contactLinkedinLabel" for="contactLinkedin">
                    <Translate contentKey="riverApp.contact.contactLinkedin">Contact Linkedin</Translate>
                  </Label>
                  <AvField id="contact-contactLinkedin" type="text" name="contactLinkedin" />
                </AvGroup>
                <AvGroup>
                  <Label id="contactGooglePlusLabel" for="contactGooglePlus">
                    <Translate contentKey="riverApp.contact.contactGooglePlus">Contact Google Plus</Translate>
                  </Label>
                  <AvField id="contact-contactGooglePlus" type="text" name="contactGooglePlus" />
                </AvGroup>
                <AvGroup>
                  <Label id="contactYoutubeLabel" for="contactYoutube">
                    <Translate contentKey="riverApp.contact.contactYoutube">Contact Youtube</Translate>
                  </Label>
                  <AvField id="contact-contactYoutube" type="text" name="contactYoutube" />
                </AvGroup>
                <AvGroup>
                  <Label id="contactStatusLabel" check>
                    <AvInput id="contact-contactStatus" type="checkbox" className="form-control" name="contactStatus" />
                    <Translate contentKey="riverApp.contact.contactStatus">Contact Status</Translate>
                  </Label>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/contact" replace color="info">
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
  contactEntity: storeState.contact.entity,
  loading: storeState.contact.loading,
  updating: storeState.contact.updating
});

const mapDispatchToProps = {
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
)(ContactUpdate);
