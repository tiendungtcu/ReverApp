import React from 'react';
import InfiniteScroll from 'react-infinite-scroller';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { byteSize, Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities, reset } from './property.reducer';
import { IProperty } from 'app/shared/model/property.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IPropertyProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IPropertyState = IPaginationBaseState;

export class Property extends React.Component<IPropertyProps, IPropertyState> {
  state: IPropertyState = {
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
    const { propertyList, match } = this.props;
    return (
      <div>
        <h2 id="property-heading">
          <Translate contentKey="riverApp.property.home.title">Properties</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="riverApp.property.home.createLabel">Create new Property</Translate>
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
                  <th className="hand" onClick={this.sort('propertyCode')}>
                    <Translate contentKey="riverApp.property.propertyCode">Property Code</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyName')}>
                    <Translate contentKey="riverApp.property.propertyName">Property Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyAlias')}>
                    <Translate contentKey="riverApp.property.propertyAlias">Property Alias</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyTransaction')}>
                    <Translate contentKey="riverApp.property.propertyTransaction">Property Transaction</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyNumber')}>
                    <Translate contentKey="riverApp.property.propertyNumber">Property Number</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyRoad')}>
                    <Translate contentKey="riverApp.property.propertyRoad">Property Road</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyWard')}>
                    <Translate contentKey="riverApp.property.propertyWard">Property Ward</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyDistrict')}>
                    <Translate contentKey="riverApp.property.propertyDistrict">Property District</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyProvince')}>
                    <Translate contentKey="riverApp.property.propertyProvince">Property Province</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyDescription')}>
                    <Translate contentKey="riverApp.property.propertyDescription">Property Description</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyBedRooms')}>
                    <Translate contentKey="riverApp.property.propertyBedRooms">Property Bed Rooms</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyBathRooms')}>
                    <Translate contentKey="riverApp.property.propertyBathRooms">Property Bath Rooms</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertySquare')}>
                    <Translate contentKey="riverApp.property.propertySquare">Property Square</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyUsePurpose')}>
                    <Translate contentKey="riverApp.property.propertyUsePurpose">Property Use Purpose</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyOwnerType')}>
                    <Translate contentKey="riverApp.property.propertyOwnerType">Property Owner Type</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyTower')}>
                    <Translate contentKey="riverApp.property.propertyTower">Property Tower</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyRentPrice')}>
                    <Translate contentKey="riverApp.property.propertyRentPrice">Property Rent Price</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyRentUnit')}>
                    <Translate contentKey="riverApp.property.propertyRentUnit">Property Rent Unit</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyRentStartedDate')}>
                    <Translate contentKey="riverApp.property.propertyRentStartedDate">Property Rent Started Date</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertySellPrice')}>
                    <Translate contentKey="riverApp.property.propertySellPrice">Property Sell Price</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertySellUnit')}>
                    <Translate contentKey="riverApp.property.propertySellUnit">Property Sell Unit</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertySellStartedDate')}>
                    <Translate contentKey="riverApp.property.propertySellStartedDate">Property Sell Started Date</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertySofa')}>
                    <Translate contentKey="riverApp.property.propertySofa">Property Sofa</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyDiningTable')}>
                    <Translate contentKey="riverApp.property.propertyDiningTable">Property Dining Table</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyKitchen')}>
                    <Translate contentKey="riverApp.property.propertyKitchen">Property Kitchen</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyCabinetKitchen')}>
                    <Translate contentKey="riverApp.property.propertyCabinetKitchen">Property Cabinet Kitchen</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyKitchenEquipment')}>
                    <Translate contentKey="riverApp.property.propertyKitchenEquipment">Property Kitchen Equipment</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyWardrobe')}>
                    <Translate contentKey="riverApp.property.propertyWardrobe">Property Wardrobe</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyMakeupTable')}>
                    <Translate contentKey="riverApp.property.propertyMakeupTable">Property Makeup Table</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyDesk')}>
                    <Translate contentKey="riverApp.property.propertyDesk">Property Desk</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyTivi')}>
                    <Translate contentKey="riverApp.property.propertyTivi">Property Tivi</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyWashingMachine')}>
                    <Translate contentKey="riverApp.property.propertyWashingMachine">Property Washing Machine</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyMicrowave')}>
                    <Translate contentKey="riverApp.property.propertyMicrowave">Property Microwave</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyWaterHeater')}>
                    <Translate contentKey="riverApp.property.propertyWaterHeater">Property Water Heater</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyBed')}>
                    <Translate contentKey="riverApp.property.propertyBed">Property Bed</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyHeater')}>
                    <Translate contentKey="riverApp.property.propertyHeater">Property Heater</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyAudioEquipment')}>
                    <Translate contentKey="riverApp.property.propertyAudioEquipment">Property Audio Equipment</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyInternet')}>
                    <Translate contentKey="riverApp.property.propertyInternet">Property Internet</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyCableTivi')}>
                    <Translate contentKey="riverApp.property.propertyCableTivi">Property Cable Tivi</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyPetPermission')}>
                    <Translate contentKey="riverApp.property.propertyPetPermission">Property Pet Permission</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyElevator')}>
                    <Translate contentKey="riverApp.property.propertyElevator">Property Elevator</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertySwimmingPool')}>
                    <Translate contentKey="riverApp.property.propertySwimmingPool">Property Swimming Pool</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyGym')}>
                    <Translate contentKey="riverApp.property.propertyGym">Property Gym</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyFunctionalArea')}>
                    <Translate contentKey="riverApp.property.propertyFunctionalArea">Property Functional Area</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyOpen24h')}>
                    <Translate contentKey="riverApp.property.propertyOpen24h">Property Open 24 H</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyCarPark')}>
                    <Translate contentKey="riverApp.property.propertyCarPark">Property Car Park</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyBalcony')}>
                    <Translate contentKey="riverApp.property.propertyBalcony">Property Balcony</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertySauna')}>
                    <Translate contentKey="riverApp.property.propertySauna">Property Sauna</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertySteamSauna')}>
                    <Translate contentKey="riverApp.property.propertySteamSauna">Property Steam Sauna</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyAttraction')}>
                    <Translate contentKey="riverApp.property.propertyAttraction">Property Attraction</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertySpecialFeature')}>
                    <Translate contentKey="riverApp.property.propertySpecialFeature">Property Special Feature</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyFurnitureOverview')}>
                    <Translate contentKey="riverApp.property.propertyFurnitureOverview">Property Furniture Overview</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyLocationOverview')}>
                    <Translate contentKey="riverApp.property.propertyLocationOverview">Property Location Overview</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyResidentialCommunity')}>
                    <Translate contentKey="riverApp.property.propertyResidentialCommunity">Property Residential Community</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyEducationalAspect')}>
                    <Translate contentKey="riverApp.property.propertyEducationalAspect">Property Educational Aspect</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyExtraInfo')}>
                    <Translate contentKey="riverApp.property.propertyExtraInfo">Property Extra Info</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyDraftUrl')}>
                    <Translate contentKey="riverApp.property.propertyDraftUrl">Property Draft Url</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('longitude')}>
                    <Translate contentKey="riverApp.property.longitude">Longitude</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('latitude')}>
                    <Translate contentKey="riverApp.property.latitude">Latitude</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyGoodPrice')}>
                    <Translate contentKey="riverApp.property.propertyGoodPrice">Property Good Price</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertySeenCount')}>
                    <Translate contentKey="riverApp.property.propertySeenCount">Property Seen Count</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyIsSold')}>
                    <Translate contentKey="riverApp.property.propertyIsSold">Property Is Sold</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyIsRent')}>
                    <Translate contentKey="riverApp.property.propertyIsRent">Property Is Rent</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyAvailable')}>
                    <Translate contentKey="riverApp.property.propertyAvailable">Property Available</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyRefrigerator')}>
                    <Translate contentKey="riverApp.property.propertyRefrigerator">Property Refrigerator</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('propertyAirconditioner')}>
                    <Translate contentKey="riverApp.property.propertyAirconditioner">Property Airconditioner</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="riverApp.property.location">Location</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="riverApp.property.consultant">Consultant</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {propertyList.map((property, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${property.id}`} color="link" size="sm">
                        {property.id}
                      </Button>
                    </td>
                    <td>{property.propertyCode}</td>
                    <td>{property.propertyName}</td>
                    <td>{property.propertyAlias}</td>
                    <td>{property.propertyTransaction}</td>
                    <td>{property.propertyNumber}</td>
                    <td>{property.propertyRoad}</td>
                    <td>{property.propertyWard}</td>
                    <td>{property.propertyDistrict}</td>
                    <td>{property.propertyProvince}</td>
                    <td>{property.propertyDescription}</td>
                    <td>{property.propertyBedRooms}</td>
                    <td>{property.propertyBathRooms}</td>
                    <td>{property.propertySquare}</td>
                    <td>{property.propertyUsePurpose}</td>
                    <td>{property.propertyOwnerType}</td>
                    <td>{property.propertyTower}</td>
                    <td>{property.propertyRentPrice}</td>
                    <td>{property.propertyRentUnit}</td>
                    <td>
                      <TextFormat type="date" value={property.propertyRentStartedDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{property.propertySellPrice}</td>
                    <td>{property.propertySellUnit}</td>
                    <td>
                      <TextFormat type="date" value={property.propertySellStartedDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{property.propertySofa ? 'true' : 'false'}</td>
                    <td>{property.propertyDiningTable ? 'true' : 'false'}</td>
                    <td>{property.propertyKitchen ? 'true' : 'false'}</td>
                    <td>{property.propertyCabinetKitchen ? 'true' : 'false'}</td>
                    <td>{property.propertyKitchenEquipment ? 'true' : 'false'}</td>
                    <td>{property.propertyWardrobe ? 'true' : 'false'}</td>
                    <td>{property.propertyMakeupTable ? 'true' : 'false'}</td>
                    <td>{property.propertyDesk ? 'true' : 'false'}</td>
                    <td>{property.propertyTivi ? 'true' : 'false'}</td>
                    <td>{property.propertyWashingMachine ? 'true' : 'false'}</td>
                    <td>{property.propertyMicrowave ? 'true' : 'false'}</td>
                    <td>{property.propertyWaterHeater ? 'true' : 'false'}</td>
                    <td>{property.propertyBed ? 'true' : 'false'}</td>
                    <td>{property.propertyHeater ? 'true' : 'false'}</td>
                    <td>{property.propertyAudioEquipment ? 'true' : 'false'}</td>
                    <td>{property.propertyInternet ? 'true' : 'false'}</td>
                    <td>{property.propertyCableTivi ? 'true' : 'false'}</td>
                    <td>{property.propertyPetPermission ? 'true' : 'false'}</td>
                    <td>{property.propertyElevator ? 'true' : 'false'}</td>
                    <td>{property.propertySwimmingPool ? 'true' : 'false'}</td>
                    <td>{property.propertyGym ? 'true' : 'false'}</td>
                    <td>{property.propertyFunctionalArea ? 'true' : 'false'}</td>
                    <td>{property.propertyOpen24h ? 'true' : 'false'}</td>
                    <td>{property.propertyCarPark ? 'true' : 'false'}</td>
                    <td>{property.propertyBalcony ? 'true' : 'false'}</td>
                    <td>{property.propertySauna ? 'true' : 'false'}</td>
                    <td>{property.propertySteamSauna ? 'true' : 'false'}</td>
                    <td>{property.propertyAttraction ? 'true' : 'false'}</td>
                    <td>{property.propertySpecialFeature}</td>
                    <td>{property.propertyFurnitureOverview}</td>
                    <td>{property.propertyLocationOverview}</td>
                    <td>{property.propertyResidentialCommunity}</td>
                    <td>{property.propertyEducationalAspect}</td>
                    <td>{property.propertyExtraInfo}</td>
                    <td>{property.propertyDraftUrl}</td>
                    <td>{property.longitude}</td>
                    <td>{property.latitude}</td>
                    <td>{property.propertyGoodPrice ? 'true' : 'false'}</td>
                    <td>{property.propertySeenCount}</td>
                    <td>{property.propertyIsSold ? 'true' : 'false'}</td>
                    <td>{property.propertyIsRent ? 'true' : 'false'}</td>
                    <td>{property.propertyAvailable ? 'true' : 'false'}</td>
                    <td>{property.propertyRefrigerator ? 'true' : 'false'}</td>
                    <td>{property.propertyAirconditioner ? 'true' : 'false'}</td>
                    <td>{property.locationId ? <Link to={`location/${property.locationId}`}>{property.locationId}</Link> : ''}</td>
                    <td>{property.consultantLogin ? property.consultantLogin : ''}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${property.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${property.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${property.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ property }: IRootState) => ({
  propertyList: property.entities,
  totalItems: property.totalItems,
  links: property.links
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
)(Property);
