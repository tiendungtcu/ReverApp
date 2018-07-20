import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './contact.reducer';
import { IContact } from 'app/shared/model/contact.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IContactDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class ContactDetail extends React.Component<IContactDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { contactEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="riverApp.contact.detail.title">Contact</Translate> [<b>{contactEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="contactName">
                <Translate contentKey="riverApp.contact.contactName">Contact Name</Translate>
              </span>
            </dt>
            <dd>{contactEntity.contactName}</dd>
            <dt>
              <span id="contactPhone">
                <Translate contentKey="riverApp.contact.contactPhone">Contact Phone</Translate>
              </span>
            </dt>
            <dd>{contactEntity.contactPhone}</dd>
            <dt>
              <span id="contactAddress">
                <Translate contentKey="riverApp.contact.contactAddress">Contact Address</Translate>
              </span>
            </dt>
            <dd>{contactEntity.contactAddress}</dd>
            <dt>
              <span id="contactEmail">
                <Translate contentKey="riverApp.contact.contactEmail">Contact Email</Translate>
              </span>
            </dt>
            <dd>{contactEntity.contactEmail}</dd>
            <dt>
              <span id="contactWebsite">
                <Translate contentKey="riverApp.contact.contactWebsite">Contact Website</Translate>
              </span>
            </dt>
            <dd>{contactEntity.contactWebsite}</dd>
            <dt>
              <span id="contactPhoto">
                <Translate contentKey="riverApp.contact.contactPhoto">Contact Photo</Translate>
              </span>
            </dt>
            <dd>
              {contactEntity.contactPhoto ? (
                <div>
                  <a onClick={openFile(contactEntity.contactPhotoContentType, contactEntity.contactPhoto)}>
                    <img
                      src={`data:${contactEntity.contactPhotoContentType};base64,${contactEntity.contactPhoto}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                  <span>
                    {contactEntity.contactPhotoContentType}, {byteSize(contactEntity.contactPhoto)}
                  </span>
                </div>
              ) : null}
            </dd>
            <dt>
              <span id="contactFacebook">
                <Translate contentKey="riverApp.contact.contactFacebook">Contact Facebook</Translate>
              </span>
            </dt>
            <dd>{contactEntity.contactFacebook}</dd>
            <dt>
              <span id="contactTwitter">
                <Translate contentKey="riverApp.contact.contactTwitter">Contact Twitter</Translate>
              </span>
            </dt>
            <dd>{contactEntity.contactTwitter}</dd>
            <dt>
              <span id="contactInstagram">
                <Translate contentKey="riverApp.contact.contactInstagram">Contact Instagram</Translate>
              </span>
            </dt>
            <dd>{contactEntity.contactInstagram}</dd>
            <dt>
              <span id="contactLinkedin">
                <Translate contentKey="riverApp.contact.contactLinkedin">Contact Linkedin</Translate>
              </span>
            </dt>
            <dd>{contactEntity.contactLinkedin}</dd>
            <dt>
              <span id="contactGooglePlus">
                <Translate contentKey="riverApp.contact.contactGooglePlus">Contact Google Plus</Translate>
              </span>
            </dt>
            <dd>{contactEntity.contactGooglePlus}</dd>
            <dt>
              <span id="contactYoutube">
                <Translate contentKey="riverApp.contact.contactYoutube">Contact Youtube</Translate>
              </span>
            </dt>
            <dd>{contactEntity.contactYoutube}</dd>
            <dt>
              <span id="contactStatus">
                <Translate contentKey="riverApp.contact.contactStatus">Contact Status</Translate>
              </span>
            </dt>
            <dd>{contactEntity.contactStatus ? 'true' : 'false'}</dd>
          </dl>
          <Button tag={Link} to="/entity/contact" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/contact/${contactEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ contact }: IRootState) => ({
  contactEntity: contact.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ContactDetail);
