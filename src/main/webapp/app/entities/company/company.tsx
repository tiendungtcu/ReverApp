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
import { getEntities } from './company.reducer';
import { ICompany } from 'app/shared/model/company.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface ICompanyProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type ICompanyState = IPaginationBaseState;

export class Company extends React.Component<ICompanyProps, ICompanyState> {
  state: ICompanyState = {
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
    const { companyList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="company-heading">
          <Translate contentKey="riverApp.company.home.title">Companies</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="riverApp.company.home.createLabel">Create new Company</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('companyName')}>
                  <Translate contentKey="riverApp.company.companyName">Company Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('companyPhone')}>
                  <Translate contentKey="riverApp.company.companyPhone">Company Phone</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('companyAddress')}>
                  <Translate contentKey="riverApp.company.companyAddress">Company Address</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('companyLogo')}>
                  <Translate contentKey="riverApp.company.companyLogo">Company Logo</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('companyWebsite')}>
                  <Translate contentKey="riverApp.company.companyWebsite">Company Website</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('companyFacebook')}>
                  <Translate contentKey="riverApp.company.companyFacebook">Company Facebook</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('companyTwitter')}>
                  <Translate contentKey="riverApp.company.companyTwitter">Company Twitter</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('companyInstagram')}>
                  <Translate contentKey="riverApp.company.companyInstagram">Company Instagram</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('companyLinkedin')}>
                  <Translate contentKey="riverApp.company.companyLinkedin">Company Linkedin</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('companyGooglePlus')}>
                  <Translate contentKey="riverApp.company.companyGooglePlus">Company Google Plus</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('companyYoutube')}>
                  <Translate contentKey="riverApp.company.companyYoutube">Company Youtube</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('companyDescription')}>
                  <Translate contentKey="riverApp.company.companyDescription">Company Description</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {companyList.map((company, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${company.id}`} color="link" size="sm">
                      {company.id}
                    </Button>
                  </td>
                  <td>{company.companyName}</td>
                  <td>{company.companyPhone}</td>
                  <td>{company.companyAddress}</td>
                  <td>
                    {company.companyLogo ? (
                      <div>
                        <a onClick={openFile(company.companyLogoContentType, company.companyLogo)}>
                          <img src={`data:${company.companyLogoContentType};base64,${company.companyLogo}`} style={{ maxHeight: '30px' }} />
                          &nbsp;
                        </a>
                        <span>
                          {company.companyLogoContentType}, {byteSize(company.companyLogo)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{company.companyWebsite}</td>
                  <td>{company.companyFacebook}</td>
                  <td>{company.companyTwitter}</td>
                  <td>{company.companyInstagram}</td>
                  <td>{company.companyLinkedin}</td>
                  <td>{company.companyGooglePlus}</td>
                  <td>{company.companyYoutube}</td>
                  <td>{company.companyDescription}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${company.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${company.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${company.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ company }: IRootState) => ({
  companyList: company.entities,
  totalItems: company.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Company);
