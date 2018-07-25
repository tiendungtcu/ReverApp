import React from 'react';
import InfiniteScroll from 'react-infinite-scroller';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { byteSize, Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities, reset } from './project.reducer';
import { IProject } from 'app/shared/model/project.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IProjectProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IProjectState = IPaginationBaseState;

export class Project extends React.Component<IProjectProps, IProjectState> {
  state: IProjectState = {
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
    const { projectList, match } = this.props;
    return (
      <div>
        <h2 id="project-heading">
          <Translate contentKey="riverApp.project.home.title">Projects</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="riverApp.project.home.createLabel">Create new Project</Translate>
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
                  <th className="hand" onClick={this.sort('projectName')}>
                    <Translate contentKey="riverApp.project.projectName">Project Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectAlias')}>
                    <Translate contentKey="riverApp.project.projectAlias">Project Alias</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectAvatarUrl')}>
                    <Translate contentKey="riverApp.project.projectAvatarUrl">Project Avatar Url</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectDistrict')}>
                    <Translate contentKey="riverApp.project.projectDistrict">Project District</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectProvince')}>
                    <Translate contentKey="riverApp.project.projectProvince">Project Province</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('residentialAreaId')}>
                    <Translate contentKey="riverApp.project.residentialAreaId">Residential Area Id</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectRoad')}>
                    <Translate contentKey="riverApp.project.projectRoad">Project Road</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectWard')}>
                    <Translate contentKey="riverApp.project.projectWard">Project Ward</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectStatus')}>
                    <Translate contentKey="riverApp.project.projectStatus">Project Status</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectNoBlocks')}>
                    <Translate contentKey="riverApp.project.projectNoBlocks">Project No Blocks</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectNoFloors')}>
                    <Translate contentKey="riverApp.project.projectNoFloors">Project No Floors</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectNoApartments')}>
                    <Translate contentKey="riverApp.project.projectNoApartments">Project No Apartments</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectNoShophouse')}>
                    <Translate contentKey="riverApp.project.projectNoShophouse">Project No Shophouse</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectDescription')}>
                    <Translate contentKey="riverApp.project.projectDescription">Project Description</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectMinSellPrice')}>
                    <Translate contentKey="riverApp.project.projectMinSellPrice">Project Min Sell Price</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectMaxSellPrice')}>
                    <Translate contentKey="riverApp.project.projectMaxSellPrice">Project Max Sell Price</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectSellPriceUnit')}>
                    <Translate contentKey="riverApp.project.projectSellPriceUnit">Project Sell Price Unit</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectMinRentPrice')}>
                    <Translate contentKey="riverApp.project.projectMinRentPrice">Project Min Rent Price</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectMaxRentPrice')}>
                    <Translate contentKey="riverApp.project.projectMaxRentPrice">Project Max Rent Price</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectRentPriceUnit')}>
                    <Translate contentKey="riverApp.project.projectRentPriceUnit">Project Rent Price Unit</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectStartedDate')}>
                    <Translate contentKey="riverApp.project.projectStartedDate">Project Started Date</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectFinishingDate')}>
                    <Translate contentKey="riverApp.project.projectFinishingDate">Project Finishing Date</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectMinApartmentSquare')}>
                    <Translate contentKey="riverApp.project.projectMinApartmentSquare">Project Min Apartment Square</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectMaxApartmentSquare')}>
                    <Translate contentKey="riverApp.project.projectMaxApartmentSquare">Project Max Apartment Square</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectGreenSpace')}>
                    <Translate contentKey="riverApp.project.projectGreenSpace">Project Green Space</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectBuildingDensity')}>
                    <Translate contentKey="riverApp.project.projectBuildingDensity">Project Building Density</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectDesignCompany')}>
                    <Translate contentKey="riverApp.project.projectDesignCompany">Project Design Company</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectCarPark')}>
                    <Translate contentKey="riverApp.project.projectCarPark">Project Car Park</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectBbqCourt')}>
                    <Translate contentKey="riverApp.project.projectBbqCourt">Project Bbq Court</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectElevator')}>
                    <Translate contentKey="riverApp.project.projectElevator">Project Elevator</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectShoppingCenter')}>
                    <Translate contentKey="riverApp.project.projectShoppingCenter">Project Shopping Center</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectSwimmingPool')}>
                    <Translate contentKey="riverApp.project.projectSwimmingPool">Project Swimming Pool</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectCommunityRoom')}>
                    <Translate contentKey="riverApp.project.projectCommunityRoom">Project Community Room</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectGym')}>
                    <Translate contentKey="riverApp.project.projectGym">Project Gym</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectCityPark')}>
                    <Translate contentKey="riverApp.project.projectCityPark">Project City Park</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectGuard')}>
                    <Translate contentKey="riverApp.project.projectGuard">Project Guard</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectPlayGround')}>
                    <Translate contentKey="riverApp.project.projectPlayGround">Project Play Ground</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('longitude')}>
                    <Translate contentKey="riverApp.project.longitude">Longitude</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('latitude')}>
                    <Translate contentKey="riverApp.project.latitude">Latitude</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectSeenCount')}>
                    <Translate contentKey="riverApp.project.projectSeenCount">Project Seen Count</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('projectAvailable')}>
                    <Translate contentKey="riverApp.project.projectAvailable">Project Available</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="riverApp.project.location">Location</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="riverApp.project.consultant">Consultant</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {projectList.map((project, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${project.id}`} color="link" size="sm">
                        {project.id}
                      </Button>
                    </td>
                    <td>{project.projectName}</td>
                    <td>{project.projectAlias}</td>
                    <td>{project.projectAvatarUrl}</td>
                    <td>{project.projectDistrict}</td>
                    <td>{project.projectProvince}</td>
                    <td>{project.residentialAreaId}</td>
                    <td>{project.projectRoad}</td>
                    <td>{project.projectWard}</td>
                    <td>{project.projectStatus}</td>
                    <td>{project.projectNoBlocks}</td>
                    <td>{project.projectNoFloors}</td>
                    <td>{project.projectNoApartments}</td>
                    <td>{project.projectNoShophouse}</td>
                    <td>{project.projectDescription}</td>
                    <td>{project.projectMinSellPrice}</td>
                    <td>{project.projectMaxSellPrice}</td>
                    <td>{project.projectSellPriceUnit}</td>
                    <td>{project.projectMinRentPrice}</td>
                    <td>{project.projectMaxRentPrice}</td>
                    <td>{project.projectRentPriceUnit}</td>
                    <td>
                      <TextFormat type="date" value={project.projectStartedDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>
                      <TextFormat type="date" value={project.projectFinishingDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{project.projectMinApartmentSquare}</td>
                    <td>{project.projectMaxApartmentSquare}</td>
                    <td>{project.projectGreenSpace}</td>
                    <td>{project.projectBuildingDensity}</td>
                    <td>{project.projectDesignCompany}</td>
                    <td>{project.projectCarPark ? 'true' : 'false'}</td>
                    <td>{project.projectBbqCourt ? 'true' : 'false'}</td>
                    <td>{project.projectElevator ? 'true' : 'false'}</td>
                    <td>{project.projectShoppingCenter ? 'true' : 'false'}</td>
                    <td>{project.projectSwimmingPool ? 'true' : 'false'}</td>
                    <td>{project.projectCommunityRoom ? 'true' : 'false'}</td>
                    <td>{project.projectGym ? 'true' : 'false'}</td>
                    <td>{project.projectCityPark ? 'true' : 'false'}</td>
                    <td>{project.projectGuard ? 'true' : 'false'}</td>
                    <td>{project.projectPlayGround ? 'true' : 'false'}</td>
                    <td>{project.longitude}</td>
                    <td>{project.latitude}</td>
                    <td>{project.projectSeenCount}</td>
                    <td>{project.projectAvailable ? 'true' : 'false'}</td>
                    <td>{project.locationId ? <Link to={`location/${project.locationId}`}>{project.locationId}</Link> : ''}</td>
                    <td>{project.consultantLogin ? project.consultantLogin : ''}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${project.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${project.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${project.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ project }: IRootState) => ({
  projectList: project.entities,
  totalItems: project.totalItems,
  links: project.links
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
)(Project);
