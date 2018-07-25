import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { getEntity, updateEntity, createEntity, reset } from './contact.reducer';
import { IContact } from 'app/shared/model/contact.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IContactUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IContactUpdateState {
  isNew: boolean;
  userId: number;
}

export class ContactUpdate extends React.Component<IContactUpdateProps, IContactUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      userId: 0,
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
  }

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

  userUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        userId: -1
      });
    } else {
      for (const i in this.props.users) {
        if (id === this.props.users[i].id.toString()) {
          this.setState({
            userId: this.props.users[i].id
          });
        }
      }
    }
  };

  render() {
    const isInvalid = false;
    const { contactEntity, users, loading, updating } = this.props;
    const { isNew } = this.state;

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
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 128, errorMessage: translate('entity.validation.maxlength', { max: 128 }) }
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
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 16, errorMessage: translate('entity.validation.maxlength', { max: 16 }) }
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
                  <Label id="contactWebsiteLabel" for="contactWebsite">
                    <Translate contentKey="riverApp.contact.contactWebsite">Contact Website</Translate>
                  </Label>
                  <AvField id="contact-contactWebsite" type="text" name="contactWebsite" />
                </AvGroup>
                <AvGroup>
                  <Label id="contactAvatarUrlLabel" for="contactAvatarUrl">
                    <Translate contentKey="riverApp.contact.contactAvatarUrl">Contact Avatar Url</Translate>
                  </Label>
                  <AvField id="contact-contactAvatarUrl" type="text" name="contactAvatarUrl" />
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
                <AvGroup>
                  <Label for="user.id">
                    <Translate contentKey="riverApp.contact.user">User</Translate>
                  </Label>
                  <AvInput id="contact-user" type="select" className="form-control" name="userId" onChange={this.userUpdate}>
                    <option value="" key="0" />
                    {users
                      ? users.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
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
  users: storeState.userManagement.users,
  contactEntity: storeState.contact.entity,
  loading: storeState.contact.loading,
  updating: storeState.contact.updating
});

const mapDispatchToProps = {
  getUsers,
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
)(ContactUpdate);
