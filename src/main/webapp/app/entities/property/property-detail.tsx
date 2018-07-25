import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './property.reducer';
import { IProperty } from 'app/shared/model/property.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPropertyDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class PropertyDetail extends React.Component<IPropertyDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { propertyEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="riverApp.property.detail.title">Property</Translate> [<b>{propertyEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="propertyCode">
                <Translate contentKey="riverApp.property.propertyCode">Property Code</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyCode}</dd>
            <dt>
              <span id="propertyName">
                <Translate contentKey="riverApp.property.propertyName">Property Name</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyName}</dd>
            <dt>
              <span id="propertyAlias">
                <Translate contentKey="riverApp.property.propertyAlias">Property Alias</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyAlias}</dd>
            <dt>
              <span id="propertyTransaction">
                <Translate contentKey="riverApp.property.propertyTransaction">Property Transaction</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyTransaction}</dd>
            <dt>
              <span id="propertyNumber">
                <Translate contentKey="riverApp.property.propertyNumber">Property Number</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyNumber}</dd>
            <dt>
              <span id="propertyRoad">
                <Translate contentKey="riverApp.property.propertyRoad">Property Road</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyRoad}</dd>
            <dt>
              <span id="propertyWard">
                <Translate contentKey="riverApp.property.propertyWard">Property Ward</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyWard}</dd>
            <dt>
              <span id="propertyDistrict">
                <Translate contentKey="riverApp.property.propertyDistrict">Property District</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyDistrict}</dd>
            <dt>
              <span id="propertyProvince">
                <Translate contentKey="riverApp.property.propertyProvince">Property Province</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyProvince}</dd>
            <dt>
              <span id="propertyDescription">
                <Translate contentKey="riverApp.property.propertyDescription">Property Description</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyDescription}</dd>
            <dt>
              <span id="propertyBedRooms">
                <Translate contentKey="riverApp.property.propertyBedRooms">Property Bed Rooms</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyBedRooms}</dd>
            <dt>
              <span id="propertyBathRooms">
                <Translate contentKey="riverApp.property.propertyBathRooms">Property Bath Rooms</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyBathRooms}</dd>
            <dt>
              <span id="propertySquare">
                <Translate contentKey="riverApp.property.propertySquare">Property Square</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertySquare}</dd>
            <dt>
              <span id="propertyUsePurpose">
                <Translate contentKey="riverApp.property.propertyUsePurpose">Property Use Purpose</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyUsePurpose}</dd>
            <dt>
              <span id="propertyOwnerType">
                <Translate contentKey="riverApp.property.propertyOwnerType">Property Owner Type</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyOwnerType}</dd>
            <dt>
              <span id="propertyTower">
                <Translate contentKey="riverApp.property.propertyTower">Property Tower</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyTower}</dd>
            <dt>
              <span id="propertyRentPrice">
                <Translate contentKey="riverApp.property.propertyRentPrice">Property Rent Price</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyRentPrice}</dd>
            <dt>
              <span id="propertyRentUnit">
                <Translate contentKey="riverApp.property.propertyRentUnit">Property Rent Unit</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyRentUnit}</dd>
            <dt>
              <span id="propertyRentStartedDate">
                <Translate contentKey="riverApp.property.propertyRentStartedDate">Property Rent Started Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={propertyEntity.propertyRentStartedDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="propertySellPrice">
                <Translate contentKey="riverApp.property.propertySellPrice">Property Sell Price</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertySellPrice}</dd>
            <dt>
              <span id="propertySellUnit">
                <Translate contentKey="riverApp.property.propertySellUnit">Property Sell Unit</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertySellUnit}</dd>
            <dt>
              <span id="propertySellStartedDate">
                <Translate contentKey="riverApp.property.propertySellStartedDate">Property Sell Started Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={propertyEntity.propertySellStartedDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="propertySofa">
                <Translate contentKey="riverApp.property.propertySofa">Property Sofa</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertySofa ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertyDiningTable">
                <Translate contentKey="riverApp.property.propertyDiningTable">Property Dining Table</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyDiningTable ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertyKitchen">
                <Translate contentKey="riverApp.property.propertyKitchen">Property Kitchen</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyKitchen ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertyCabinetKitchen">
                <Translate contentKey="riverApp.property.propertyCabinetKitchen">Property Cabinet Kitchen</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyCabinetKitchen ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertyKitchenEquipment">
                <Translate contentKey="riverApp.property.propertyKitchenEquipment">Property Kitchen Equipment</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyKitchenEquipment ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertyWardrobe">
                <Translate contentKey="riverApp.property.propertyWardrobe">Property Wardrobe</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyWardrobe ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertyMakeupTable">
                <Translate contentKey="riverApp.property.propertyMakeupTable">Property Makeup Table</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyMakeupTable ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertyDesk">
                <Translate contentKey="riverApp.property.propertyDesk">Property Desk</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyDesk ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertyTivi">
                <Translate contentKey="riverApp.property.propertyTivi">Property Tivi</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyTivi ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertyWashingMachine">
                <Translate contentKey="riverApp.property.propertyWashingMachine">Property Washing Machine</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyWashingMachine ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertyMicrowave">
                <Translate contentKey="riverApp.property.propertyMicrowave">Property Microwave</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyMicrowave ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertyWaterHeater">
                <Translate contentKey="riverApp.property.propertyWaterHeater">Property Water Heater</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyWaterHeater ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertyBed">
                <Translate contentKey="riverApp.property.propertyBed">Property Bed</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyBed ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertyHeater">
                <Translate contentKey="riverApp.property.propertyHeater">Property Heater</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyHeater ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertyAudioEquipment">
                <Translate contentKey="riverApp.property.propertyAudioEquipment">Property Audio Equipment</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyAudioEquipment ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertyInternet">
                <Translate contentKey="riverApp.property.propertyInternet">Property Internet</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyInternet ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertyCableTivi">
                <Translate contentKey="riverApp.property.propertyCableTivi">Property Cable Tivi</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyCableTivi ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertyPetPermission">
                <Translate contentKey="riverApp.property.propertyPetPermission">Property Pet Permission</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyPetPermission ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertyElevator">
                <Translate contentKey="riverApp.property.propertyElevator">Property Elevator</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyElevator ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertySwimmingPool">
                <Translate contentKey="riverApp.property.propertySwimmingPool">Property Swimming Pool</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertySwimmingPool ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertyGym">
                <Translate contentKey="riverApp.property.propertyGym">Property Gym</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyGym ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertyFunctionalArea">
                <Translate contentKey="riverApp.property.propertyFunctionalArea">Property Functional Area</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyFunctionalArea ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertyOpen24h">
                <Translate contentKey="riverApp.property.propertyOpen24h">Property Open 24 H</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyOpen24h ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertyCarPark">
                <Translate contentKey="riverApp.property.propertyCarPark">Property Car Park</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyCarPark ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertyBalcony">
                <Translate contentKey="riverApp.property.propertyBalcony">Property Balcony</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyBalcony ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertySauna">
                <Translate contentKey="riverApp.property.propertySauna">Property Sauna</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertySauna ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertySteamSauna">
                <Translate contentKey="riverApp.property.propertySteamSauna">Property Steam Sauna</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertySteamSauna ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertyAttraction">
                <Translate contentKey="riverApp.property.propertyAttraction">Property Attraction</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyAttraction ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertySpecialFeature">
                <Translate contentKey="riverApp.property.propertySpecialFeature">Property Special Feature</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertySpecialFeature}</dd>
            <dt>
              <span id="propertyFurnitureOverview">
                <Translate contentKey="riverApp.property.propertyFurnitureOverview">Property Furniture Overview</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyFurnitureOverview}</dd>
            <dt>
              <span id="propertyLocationOverview">
                <Translate contentKey="riverApp.property.propertyLocationOverview">Property Location Overview</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyLocationOverview}</dd>
            <dt>
              <span id="propertyResidentialCommunity">
                <Translate contentKey="riverApp.property.propertyResidentialCommunity">Property Residential Community</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyResidentialCommunity}</dd>
            <dt>
              <span id="propertyEducationalAspect">
                <Translate contentKey="riverApp.property.propertyEducationalAspect">Property Educational Aspect</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyEducationalAspect}</dd>
            <dt>
              <span id="propertyExtraInfo">
                <Translate contentKey="riverApp.property.propertyExtraInfo">Property Extra Info</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyExtraInfo}</dd>
            <dt>
              <span id="propertyDraftUrl">
                <Translate contentKey="riverApp.property.propertyDraftUrl">Property Draft Url</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyDraftUrl}</dd>
            <dt>
              <span id="longitude">
                <Translate contentKey="riverApp.property.longitude">Longitude</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.longitude}</dd>
            <dt>
              <span id="latitude">
                <Translate contentKey="riverApp.property.latitude">Latitude</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.latitude}</dd>
            <dt>
              <span id="propertyGoodPrice">
                <Translate contentKey="riverApp.property.propertyGoodPrice">Property Good Price</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyGoodPrice ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertySeenCount">
                <Translate contentKey="riverApp.property.propertySeenCount">Property Seen Count</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertySeenCount}</dd>
            <dt>
              <span id="propertyIsSold">
                <Translate contentKey="riverApp.property.propertyIsSold">Property Is Sold</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyIsSold ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertyIsRent">
                <Translate contentKey="riverApp.property.propertyIsRent">Property Is Rent</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyIsRent ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertyAvailable">
                <Translate contentKey="riverApp.property.propertyAvailable">Property Available</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyAvailable ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertyRefrigerator">
                <Translate contentKey="riverApp.property.propertyRefrigerator">Property Refrigerator</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyRefrigerator ? 'true' : 'false'}</dd>
            <dt>
              <span id="propertyAirconditioner">
                <Translate contentKey="riverApp.property.propertyAirconditioner">Property Airconditioner</Translate>
              </span>
            </dt>
            <dd>{propertyEntity.propertyAirconditioner ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="riverApp.property.location">Location</Translate>
            </dt>
            <dd>{propertyEntity.locationId ? propertyEntity.locationId : ''}</dd>
            <dt>
              <Translate contentKey="riverApp.property.consultant">Consultant</Translate>
            </dt>
            <dd>{propertyEntity.consultantLogin ? propertyEntity.consultantLogin : ''}</dd>
            <dt>
              <Translate contentKey="riverApp.property.tag">Tag</Translate>
            </dt>
            <dd>
              {propertyEntity.tags
                ? propertyEntity.tags.map((val, i) => (
                    <span key={val.id}>
                      <a>{val.tagName}</a>
                      {i === propertyEntity.tags.length - 1 ? '' : ', '}
                    </span>
                  ))
                : null}
            </dd>
          </dl>
          <Button tag={Link} to="/entity/property" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/property/${propertyEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ property }: IRootState) => ({
  propertyEntity: property.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PropertyDetail);
