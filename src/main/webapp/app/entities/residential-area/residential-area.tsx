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
import { getEntities } from './residential-area.reducer';
import { IResidentialArea } from 'app/shared/model/residential-area.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IResidentialAreaProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IResidentialAreaState = IPaginationBaseState;

export class ResidentialArea extends React.Component<IResidentialAreaProps, IResidentialAreaState> {
  state: IResidentialAreaState = {
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
    const { residentialAreaList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="residential-area-heading">
          <Translate contentKey="riverApp.residentialArea.home.title">Residential Areas</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="riverApp.residentialArea.home.createLabel">Create new Residential Area</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('residentialName')}>
                  <Translate contentKey="riverApp.residentialArea.residentialName">Residential Name</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('residentialAlias')}>
                  <Translate contentKey="riverApp.residentialArea.residentialAlias">Residential Alias</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('residentialDescription')}>
                  <Translate contentKey="riverApp.residentialArea.residentialDescription">Residential Description</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('residentialDetail')}>
                  <Translate contentKey="riverApp.residentialArea.residentialDetail">Residential Detail</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('residentialProvince')}>
                  <Translate contentKey="riverApp.residentialArea.residentialProvince">Residential Province</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('residentialDistrict')}>
                  <Translate contentKey="riverApp.residentialArea.residentialDistrict">Residential District</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('residentialBoundary')}>
                  <Translate contentKey="riverApp.residentialArea.residentialBoundary">Residential Boundary</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('residentialImage')}>
                  <Translate contentKey="riverApp.residentialArea.residentialImage">Residential Image</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="riverApp.residentialArea.photo">Photo</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {residentialAreaList.map((residentialArea, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${residentialArea.id}`} color="link" size="sm">
                      {residentialArea.id}
                    </Button>
                  </td>
                  <td>{residentialArea.residentialName}</td>
                  <td>{residentialArea.residentialAlias}</td>
                  <td>{residentialArea.residentialDescription}</td>
                  <td>{residentialArea.residentialDetail}</td>
                  <td>{residentialArea.residentialProvince}</td>
                  <td>{residentialArea.residentialDistrict}</td>
                  <td>{residentialArea.residentialBoundary}</td>
                  <td>
                    {residentialArea.residentialImage ? (
                      <div>
                        <a onClick={openFile(residentialArea.residentialImageContentType, residentialArea.residentialImage)}>
                          <img
                            src={`data:${residentialArea.residentialImageContentType};base64,${residentialArea.residentialImage}`}
                            style={{ maxHeight: '30px' }}
                          />
                          &nbsp;
                        </a>
                        <span>
                          {residentialArea.residentialImageContentType}, {byteSize(residentialArea.residentialImage)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{residentialArea.photoId ? <Link to={`photo/${residentialArea.photoId}`}>{residentialArea.photoId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${residentialArea.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${residentialArea.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${residentialArea.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ residentialArea }: IRootState) => ({
  residentialAreaList: residentialArea.entities,
  totalItems: residentialArea.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ResidentialArea);
