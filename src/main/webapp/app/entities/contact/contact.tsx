import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import {
  openFile,
  byteSize,
  Translate,
  ICrudGetAllAction,
  getSortState,
  IPaginationBaseState,
  getPaginationItemsNumber,
  JhiPagination
} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './contact.reducer';
import { IContact } from 'app/shared/model/contact.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IContactProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IContactState = IPaginationBaseState;

export class Contact extends React.Component<IContactProps, IContactState> {
  state: IContactState = {
    ...getSortState(this.props.location, ITEMS_PER_PAGE)
  };

  componentDidMount() {
    this.getEntities();
  }

  sort = prop => () => {
    this.setState(
      {
        order: this.state.order === 'asc' ? 'desc' : 'asc',
        sort: prop
      },
      () => this.sortEntities()
    );
  };

  sortEntities() {
    this.getEntities();
    this.props.history.push(`${this.props.location.pathname}?page=${this.state.activePage}&sort=${this.state.sort},${this.state.order}`);
  }

  handlePagination = activePage => this.setState({ activePage }, () => this.sortEntities());

  getEntities = () => {
    const { activePage, itemsPerPage, sort, order } = this.state;
    this.props.getEntities(activePage - 1, itemsPerPage, `${sort},${order}`);
  };

  render() {
    const { contactList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="contact-heading">
          <Translate contentKey="riverApp.contact.home.title">Contacts</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="riverApp.contact.home.createLabel">Create new Contact</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('contactName')}>
                  <Translate contentKey="riverApp.contact.contactName">Contact Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('contactPhone')}>
                  <Translate contentKey="riverApp.contact.contactPhone">Contact Phone</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('contactAddress')}>
                  <Translate contentKey="riverApp.contact.contactAddress">Contact Address</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('contactEmail')}>
                  <Translate contentKey="riverApp.contact.contactEmail">Contact Email</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('contactWebsite')}>
                  <Translate contentKey="riverApp.contact.contactWebsite">Contact Website</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('contactPhoto')}>
                  <Translate contentKey="riverApp.contact.contactPhoto">Contact Photo</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('contactFacebook')}>
                  <Translate contentKey="riverApp.contact.contactFacebook">Contact Facebook</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('contactTwitter')}>
                  <Translate contentKey="riverApp.contact.contactTwitter">Contact Twitter</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('contactInstagram')}>
                  <Translate contentKey="riverApp.contact.contactInstagram">Contact Instagram</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('contactLinkedin')}>
                  <Translate contentKey="riverApp.contact.contactLinkedin">Contact Linkedin</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('contactGooglePlus')}>
                  <Translate contentKey="riverApp.contact.contactGooglePlus">Contact Google Plus</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('contactYoutube')}>
                  <Translate contentKey="riverApp.contact.contactYoutube">Contact Youtube</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('contactStatus')}>
                  <Translate contentKey="riverApp.contact.contactStatus">Contact Status</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {contactList.map((contact, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${contact.id}`} color="link" size="sm">
                      {contact.id}
                    </Button>
                  </td>
                  <td>{contact.contactName}</td>
                  <td>{contact.contactPhone}</td>
                  <td>{contact.contactAddress}</td>
                  <td>{contact.contactEmail}</td>
                  <td>{contact.contactWebsite}</td>
                  <td>
                    {contact.contactPhoto ? (
                      <div>
                        <a onClick={openFile(contact.contactPhotoContentType, contact.contactPhoto)}>
                          <img
                            src={`data:${contact.contactPhotoContentType};base64,${contact.contactPhoto}`}
                            style={{ maxHeight: '30px' }}
                          />
                          &nbsp;
                        </a>
                        <span>
                          {contact.contactPhotoContentType}, {byteSize(contact.contactPhoto)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{contact.contactFacebook}</td>
                  <td>{contact.contactTwitter}</td>
                  <td>{contact.contactInstagram}</td>
                  <td>{contact.contactLinkedin}</td>
                  <td>{contact.contactGooglePlus}</td>
                  <td>{contact.contactYoutube}</td>
                  <td>{contact.contactStatus ? 'true' : 'false'}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${contact.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${contact.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${contact.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
        <Row className="justify-content-center">
          <JhiPagination
            items={getPaginationItemsNumber(totalItems, this.state.itemsPerPage)}
            activePage={this.state.activePage}
            onSelect={this.handlePagination}
            maxButtons={5}
          />
        </Row>
      </div>
    );
  }
}

const mapStateToProps = ({ contact }: IRootState) => ({
  contactList: contact.entities,
  totalItems: contact.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Contact);
