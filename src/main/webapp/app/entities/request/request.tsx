import React from 'react';
import InfiniteScroll from 'react-infinite-scroller';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities, reset } from './request.reducer';
import { IRequest } from 'app/shared/model/request.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IRequestProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IRequestState = IPaginationBaseState;

export class Request extends React.Component<IRequestProps, IRequestState> {
  state: IRequestState = {
    ...getSortState(this.props.location, ITEMS_PER_PAGE)
  };

  componentDidMount() {
    this.reset();
  }

  reset = () => {
    this.props.reset();
    this.setState({ activePage: 1 }, () => this.getEntities());
  };

  handleLoadMore = page => {
    this.setState({ activePage: this.state.activePage + 1 }, () => this.getEntities());
  };

  sort = prop => () => {
    this.setState(
      {
        order: this.state.order === 'asc' ? 'desc' : 'asc',
        sort: prop
      },
      () => this.reset()
    );
  };

  getEntities = () => {
    const { activePage, itemsPerPage, sort, order } = this.state;
    this.props.getEntities(activePage - 1, itemsPerPage, `${sort},${order}`);
  };

  render() {
    const { requestList, match } = this.props;
    return (
      <div>
        <h2 id="request-heading">
          <Translate contentKey="riverApp.request.home.title">Requests</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="riverApp.request.home.createLabel">Create new Request</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <InfiniteScroll
            pageStart={this.state.activePage}
            loadMore={this.handleLoadMore}
            hasMore={this.state.activePage <= this.props.links.last}
            loader={<div className="loader">Loading ...</div>}
            threshold={0}
            initialLoad={false}
          >
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={this.sort('id')}>
                    <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('requestFirstName')}>
                    <Translate contentKey="riverApp.request.requestFirstName">Request First Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('requestLastName')}>
                    <Translate contentKey="riverApp.request.requestLastName">Request Last Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('requestEmail')}>
                    <Translate contentKey="riverApp.request.requestEmail">Request Email</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('requestPhone')}>
                    <Translate contentKey="riverApp.request.requestPhone">Request Phone</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('requestGetAnalysis')}>
                    <Translate contentKey="riverApp.request.requestGetAnalysis">Request Get Analysis</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('requestGetPrice')}>
                    <Translate contentKey="riverApp.request.requestGetPrice">Request Get Price</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('requestPageUrl')}>
                    <Translate contentKey="riverApp.request.requestPageUrl">Request Page Url</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('requestPageName')}>
                    <Translate contentKey="riverApp.request.requestPageName">Request Page Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('requestPropertyId')}>
                    <Translate contentKey="riverApp.request.requestPropertyId">Request Property Id</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('requestPropertyType')}>
                    <Translate contentKey="riverApp.request.requestPropertyType">Request Property Type</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('requestType')}>
                    <Translate contentKey="riverApp.request.requestType">Request Type</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('requestMeetingDate')}>
                    <Translate contentKey="riverApp.request.requestMeetingDate">Request Meeting Date</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('requestQuestion')}>
                    <Translate contentKey="riverApp.request.requestQuestion">Request Question</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('requestPrice')}>
                    <Translate contentKey="riverApp.request.requestPrice">Request Price</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('requestCreatedDate')}>
                    <Translate contentKey="riverApp.request.requestCreatedDate">Request Created Date</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('requestConsultantId')}>
                    <Translate contentKey="riverApp.request.requestConsultantId">Request Consultant Id</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="riverApp.request.user">User</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="riverApp.request.property">Property</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="riverApp.request.project">Project</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {requestList.map((request, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${request.id}`} color="link" size="sm">
                        {request.id}
                      </Button>
                    </td>
                    <td>{request.requestFirstName}</td>
                    <td>{request.requestLastName}</td>
                    <td>{request.requestEmail}</td>
                    <td>{request.requestPhone}</td>
                    <td>{request.requestGetAnalysis ? 'true' : 'false'}</td>
                    <td>{request.requestGetPrice ? 'true' : 'false'}</td>
                    <td>{request.requestPageUrl}</td>
                    <td>{request.requestPageName}</td>
                    <td>{request.requestPropertyId}</td>
                    <td>{request.requestPropertyType}</td>
                    <td>{request.requestType}</td>
                    <td>
                      <TextFormat type="date" value={request.requestMeetingDate} format={APP_DATE_FORMAT} />
                    </td>
                    <td>{request.requestQuestion}</td>
                    <td>{request.requestPrice}</td>
                    <td>
                      <TextFormat type="date" value={request.requestCreatedDate} format={APP_DATE_FORMAT} />
                    </td>
                    <td>{request.requestConsultantId}</td>
                    <td>{request.userLogin ? request.userLogin : ''}</td>
                    <td>
                      {request.propertyPropertyName ? (
                        <Link to={`property/${request.propertyId}`}>{request.propertyPropertyName}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {request.projectProjectName ? <Link to={`project/${request.projectId}`}>{request.projectProjectName}</Link> : ''}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${request.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${request.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${request.id}/delete`} color="danger" size="sm">
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
          </InfiniteScroll>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ request }: IRootState) => ({
  requestList: request.entities,
  totalItems: request.totalItems,
  links: request.links
});

const mapDispatchToProps = {
  getEntities,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Request);
