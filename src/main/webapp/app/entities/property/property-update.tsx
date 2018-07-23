import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ILocation } from 'app/shared/model/location.model';
import { getEntities as getLocations } from 'app/entities/location/location.reducer';
import { IResidentialArea } from 'app/shared/model/residential-area.model';
import { getEntities as getResidentialAreas } from 'app/entities/residential-area/residential-area.reducer';
import { ITag } from 'app/shared/model/tag.model';
import { getEntities as getTags } from 'app/entities/tag/tag.reducer';
import { IBuildingType } from 'app/shared/model/building-type.model';
import { getEntities as getBuildingTypes } from 'app/entities/building-type/building-type.reducer';
import { IPhoto } from 'app/shared/model/photo.model';
import { getEntities as getPhotos } from 'app/entities/photo/photo.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './property.reducer';
import { IProperty } from 'app/shared/model/property.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IPropertyUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IPropertyUpdateState {
  isNew: boolean;
  idstag: any[];
  idsbuildingtype: any[];
  idsphoto: any[];
  locationId: number;
  residentialAreaId: number;
}

export class PropertyUpdate extends React.Component<IPropertyUpdateProps, IPropertyUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      idstag: [],
      idsbuildingtype: [],
      idsphoto: [],
      locationId: 0,
      residentialAreaId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getLocations();
    this.props.getResidentialAreas();
    this.props.getTags();
    this.props.getBuildingTypes();
    this.props.getPhotos();
  }

  onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => this.props.setBlob(name, data, contentType), isAnImage);
  };

  clearBlob = name => () => {
    this.props.setBlob(name, undefined, undefined);
  };

  saveEntity = (event, errors, values) => {
    values.propertyRentStartedDate = new Date(values.propertyRentStartedDate);
    values.propertySellStartedDate = new Date(values.propertySellStartedDate);

    if (errors.length === 0) {
      const { propertyEntity } = this.props;
      const entity = {
        ...propertyEntity,
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
    this.props.history.push('/entity/property');
  };

  locationUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        locationId: -1
      });
    } else {
      for (const i in this.props.locations) {
        if (id === this.props.locations[i].id.toString()) {
          this.setState({
            locationId: this.props.locations[i].id
          });
        }
      }
    }
  };

  residentialAreaUpdate = element => {
    const residentialName = element.target.value.toString();
    if (residentialName === '') {
      this.setState({
        residentialAreaId: -1
      });
    } else {
      for (const i in this.props.residentialAreas) {
        if (residentialName === this.props.residentialAreas[i].residentialName.toString()) {
          this.setState({
            residentialAreaId: this.props.residentialAreas[i].id
          });
        }
      }
    }
  };

  tagUpdate = element => {
    const selected = Array.from(element.target.selectedOptions).map((e: any) => e.value);
    this.setState({
      idstag: keysToValues(selected, this.props.tags, 'tagName')
    });
  };

  buildingtypeUpdate = element => {
    const selected = Array.from(element.target.selectedOptions).map((e: any) => e.value);
    this.setState({
      idsbuildingtype: keysToValues(selected, this.props.buildingTypes, 'typeName')
    });
  };

  photoUpdate = element => {
    const selected = Array.from(element.target.selectedOptions).map((e: any) => e.value);
    this.setState({
      idsphoto: keysToValues(selected, this.props.photos, 'id')
    });
  };

  displaytag(value: any) {
    if (this.state.idstag && this.state.idstag.length !== 0) {
      const list = [];
      for (const i in this.state.idstag) {
        if (this.state.idstag[i]) {
          list.push(this.state.idstag[i].tagName);
        }
      }
      return list;
    }
    if (value.tags && value.tags.length !== 0) {
      const list = [];
      for (const i in value.tags) {
        if (value.tags[i]) {
          list.push(value.tags[i].tagName);
        }
      }
      this.setState({
        idstag: keysToValues(list, this.props.tags, 'tagName')
      });
      return list;
    }
    return null;
  }

  displaybuildingtype(value: any) {
    if (this.state.idsbuildingtype && this.state.idsbuildingtype.length !== 0) {
      const list = [];
      for (const i in this.state.idsbuildingtype) {
        if (this.state.idsbuildingtype[i]) {
          list.push(this.state.idsbuildingtype[i].typeName);
        }
      }
      return list;
    }
    if (value.buildingtypes && value.buildingtypes.length !== 0) {
      const list = [];
      for (const i in value.buildingtypes) {
        if (value.buildingtypes[i]) {
          list.push(value.buildingtypes[i].typeName);
        }
      }
      this.setState({
        idsbuildingtype: keysToValues(list, this.props.buildingTypes, 'typeName')
      });
      return list;
    }
    return null;
  }

  displayphoto(value: any) {
    if (this.state.idsphoto && this.state.idsphoto.length !== 0) {
      const list = [];
      for (const i in this.state.idsphoto) {
        if (this.state.idsphoto[i]) {
          list.push(this.state.idsphoto[i].id);
        }
      }
      return list;
    }
    if (value.photos && value.photos.length !== 0) {
      const list = [];
      for (const i in value.photos) {
        if (value.photos[i]) {
          list.push(value.photos[i].id);
        }
      }
      this.setState({
        idsphoto: keysToValues(list, this.props.photos, 'id')
      });
      return list;
    }
    return null;
  }

  render() {
    const isInvalid = false;
    const { propertyEntity, locations, residentialAreas, tags, buildingTypes, photos, loading, updating } = this.props;
    const { isNew } = this.state;

    const { propertyDescription, propertyDraft, propertyDraftContentType } = propertyEntity;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="riverApp.property.home.createOrEditLabel">
              <Translate contentKey="riverApp.property.home.createOrEditLabel">Create or edit a Property</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : propertyEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="property-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="propertyCodeLabel" for="propertyCode">
                    <Translate contentKey="riverApp.property.propertyCode">Property Code</Translate>
                  </Label>
                  <AvField
                    id="property-propertyCode"
                    type="text"
                    name="propertyCode"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="propertyNameLabel" for="propertyName">
                    <Translate contentKey="riverApp.property.propertyName">Property Name</Translate>
                  </Label>
                  <AvField
                    id="property-propertyName"
                    type="text"
                    name="propertyName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="propertyAliasLabel" for="propertyAlias">
                    <Translate contentKey="riverApp.property.propertyAlias">Property Alias</Translate>
                  </Label>
                  <AvField id="property-propertyAlias" type="text" name="propertyAlias" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertyTransactionLabel">
                    <Translate contentKey="riverApp.property.propertyTransaction">Property Transaction</Translate>
                  </Label>
                  <AvInput
                    id="property-propertyTransaction"
                    type="select"
                    className="form-control"
                    name="propertyTransaction"
                    value={(!isNew && propertyEntity.propertyTransaction) || 'SELL'}
                  >
                    <option value="SELL">SELL</option>
                    <option value="BUY">BUY</option>
                    <option value="RENT">RENT</option>
                    <option value="SELL_RENT">SELL_RENT</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyNumberLabel" for="propertyNumber">
                    <Translate contentKey="riverApp.property.propertyNumber">Property Number</Translate>
                  </Label>
                  <AvField id="property-propertyNumber" type="text" name="propertyNumber" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertyRoadLabel" for="propertyRoad">
                    <Translate contentKey="riverApp.property.propertyRoad">Property Road</Translate>
                  </Label>
                  <AvField id="property-propertyRoad" type="text" name="propertyRoad" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertyWardLabel" for="propertyWard">
                    <Translate contentKey="riverApp.property.propertyWard">Property Ward</Translate>
                  </Label>
                  <AvField id="property-propertyWard" type="text" name="propertyWard" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertyDistrictLabel" for="propertyDistrict">
                    <Translate contentKey="riverApp.property.propertyDistrict">Property District</Translate>
                  </Label>
                  <AvField id="property-propertyDistrict" type="text" name="propertyDistrict" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertyProvinceLabel" for="propertyProvince">
                    <Translate contentKey="riverApp.property.propertyProvince">Property Province</Translate>
                  </Label>
                  <AvField id="property-propertyProvince" type="text" name="propertyProvince" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertyDescriptionLabel" for="propertyDescription">
                    <Translate contentKey="riverApp.property.propertyDescription">Property Description</Translate>
                  </Label>
                  <AvField id="property-propertyDescription" type="text" name="propertyDescription" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertyBedRoomsLabel" for="propertyBedRooms">
                    <Translate contentKey="riverApp.property.propertyBedRooms">Property Bed Rooms</Translate>
                  </Label>
                  <AvField id="property-propertyBedRooms" type="number" className="form-control" name="propertyBedRooms" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertyBathRoomsLabel" for="propertyBathRooms">
                    <Translate contentKey="riverApp.property.propertyBathRooms">Property Bath Rooms</Translate>
                  </Label>
                  <AvField id="property-propertyBathRooms" type="number" className="form-control" name="propertyBathRooms" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertySquareLabel" for="propertySquare">
                    <Translate contentKey="riverApp.property.propertySquare">Property Square</Translate>
                  </Label>
                  <AvField id="property-propertySquare" type="number" className="form-control" name="propertySquare" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertyUsePurposeLabel">
                    <Translate contentKey="riverApp.property.propertyUsePurpose">Property Use Purpose</Translate>
                  </Label>
                  <AvInput
                    id="property-propertyUsePurpose"
                    type="select"
                    className="form-control"
                    name="propertyUsePurpose"
                    value={(!isNew && propertyEntity.propertyUsePurpose) || 'LODGE'}
                  >
                    <option value="LODGE">LODGE</option>
                    <option value="PUB">PUB</option>
                    <option value="SHOP">SHOP</option>
                    <option value="RESTAURANT">RESTAURANT</option>
                    <option value="HOTEL">HOTEL</option>
                    <option value="LEASE">LEASE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyOwnerTypeLabel" for="propertyOwnerType">
                    <Translate contentKey="riverApp.property.propertyOwnerType">Property Owner Type</Translate>
                  </Label>
                  <AvField id="property-propertyOwnerType" type="text" name="propertyOwnerType" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertyTowerLabel" for="propertyTower">
                    <Translate contentKey="riverApp.property.propertyTower">Property Tower</Translate>
                  </Label>
                  <AvField id="property-propertyTower" type="text" name="propertyTower" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertyRentPriceLabel" for="propertyRentPrice">
                    <Translate contentKey="riverApp.property.propertyRentPrice">Property Rent Price</Translate>
                  </Label>
                  <AvField id="property-propertyRentPrice" type="number" className="form-control" name="propertyRentPrice" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertyRentUnitLabel">
                    <Translate contentKey="riverApp.property.propertyRentUnit">Property Rent Unit</Translate>
                  </Label>
                  <AvInput
                    id="property-propertyRentUnit"
                    type="select"
                    className="form-control"
                    name="propertyRentUnit"
                    value={(!isNew && propertyEntity.propertyRentUnit) || 'THOUSAND'}
                  >
                    <option value="THOUSAND">THOUSAND</option>
                    <option value="MILLION">MILLION</option>
                    <option value="BILLION">BILLION</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyRentStartedDateLabel" for="propertyRentStartedDate">
                    <Translate contentKey="riverApp.property.propertyRentStartedDate">Property Rent Started Date</Translate>
                  </Label>
                  <AvInput
                    id="property-propertyRentStartedDate"
                    type="datetime-local"
                    className="form-control"
                    name="propertyRentStartedDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.propertyEntity.propertyRentStartedDate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="propertySellPriceLabel" for="propertySellPrice">
                    <Translate contentKey="riverApp.property.propertySellPrice">Property Sell Price</Translate>
                  </Label>
                  <AvField id="property-propertySellPrice" type="number" className="form-control" name="propertySellPrice" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertySellUnitLabel">
                    <Translate contentKey="riverApp.property.propertySellUnit">Property Sell Unit</Translate>
                  </Label>
                  <AvInput
                    id="property-propertySellUnit"
                    type="select"
                    className="form-control"
                    name="propertySellUnit"
                    value={(!isNew && propertyEntity.propertySellUnit) || 'THOUSAND'}
                  >
                    <option value="THOUSAND">THOUSAND</option>
                    <option value="MILLION">MILLION</option>
                    <option value="BILLION">BILLION</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="propertySellStartedDateLabel" for="propertySellStartedDate">
                    <Translate contentKey="riverApp.property.propertySellStartedDate">Property Sell Started Date</Translate>
                  </Label>
                  <AvInput
                    id="property-propertySellStartedDate"
                    type="datetime-local"
                    className="form-control"
                    name="propertySellStartedDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.propertyEntity.propertySellStartedDate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="propertySofaLabel" check>
                    <AvInput id="property-propertySofa" type="checkbox" className="form-control" name="propertySofa" />
                    <Translate contentKey="riverApp.property.propertySofa">Property Sofa</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyDiningTableLabel" check>
                    <AvInput id="property-propertyDiningTable" type="checkbox" className="form-control" name="propertyDiningTable" />
                    <Translate contentKey="riverApp.property.propertyDiningTable">Property Dining Table</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyKitchenLabel" check>
                    <AvInput id="property-propertyKitchen" type="checkbox" className="form-control" name="propertyKitchen" />
                    <Translate contentKey="riverApp.property.propertyKitchen">Property Kitchen</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyCabinetKitchenLabel" check>
                    <AvInput id="property-propertyCabinetKitchen" type="checkbox" className="form-control" name="propertyCabinetKitchen" />
                    <Translate contentKey="riverApp.property.propertyCabinetKitchen">Property Cabinet Kitchen</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyKitchenEquipmentLabel" check>
                    <AvInput
                      id="property-propertyKitchenEquipment"
                      type="checkbox"
                      className="form-control"
                      name="propertyKitchenEquipment"
                    />
                    <Translate contentKey="riverApp.property.propertyKitchenEquipment">Property Kitchen Equipment</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyWardrobeLabel" check>
                    <AvInput id="property-propertyWardrobe" type="checkbox" className="form-control" name="propertyWardrobe" />
                    <Translate contentKey="riverApp.property.propertyWardrobe">Property Wardrobe</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyMakeupTableLabel" check>
                    <AvInput id="property-propertyMakeupTable" type="checkbox" className="form-control" name="propertyMakeupTable" />
                    <Translate contentKey="riverApp.property.propertyMakeupTable">Property Makeup Table</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyDeskLabel" check>
                    <AvInput id="property-propertyDesk" type="checkbox" className="form-control" name="propertyDesk" />
                    <Translate contentKey="riverApp.property.propertyDesk">Property Desk</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyTiviLabel" check>
                    <AvInput id="property-propertyTivi" type="checkbox" className="form-control" name="propertyTivi" />
                    <Translate contentKey="riverApp.property.propertyTivi">Property Tivi</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyWashingMachineLabel" check>
                    <AvInput id="property-propertyWashingMachine" type="checkbox" className="form-control" name="propertyWashingMachine" />
                    <Translate contentKey="riverApp.property.propertyWashingMachine">Property Washing Machine</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyMicrowaveLabel" check>
                    <AvInput id="property-propertyMicrowave" type="checkbox" className="form-control" name="propertyMicrowave" />
                    <Translate contentKey="riverApp.property.propertyMicrowave">Property Microwave</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyWaterHeaterLabel" check>
                    <AvInput id="property-propertyWaterHeater" type="checkbox" className="form-control" name="propertyWaterHeater" />
                    <Translate contentKey="riverApp.property.propertyWaterHeater">Property Water Heater</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyBedLabel" check>
                    <AvInput id="property-propertyBed" type="checkbox" className="form-control" name="propertyBed" />
                    <Translate contentKey="riverApp.property.propertyBed">Property Bed</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyHeaterLabel" check>
                    <AvInput id="property-propertyHeater" type="checkbox" className="form-control" name="propertyHeater" />
                    <Translate contentKey="riverApp.property.propertyHeater">Property Heater</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyAudioEquipmentLabel" check>
                    <AvInput id="property-propertyAudioEquipment" type="checkbox" className="form-control" name="propertyAudioEquipment" />
                    <Translate contentKey="riverApp.property.propertyAudioEquipment">Property Audio Equipment</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyInternetLabel" check>
                    <AvInput id="property-propertyInternet" type="checkbox" className="form-control" name="propertyInternet" />
                    <Translate contentKey="riverApp.property.propertyInternet">Property Internet</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyCableTiviLabel" check>
                    <AvInput id="property-propertyCableTivi" type="checkbox" className="form-control" name="propertyCableTivi" />
                    <Translate contentKey="riverApp.property.propertyCableTivi">Property Cable Tivi</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyPetPermissionLabel" check>
                    <AvInput id="property-propertyPetPermission" type="checkbox" className="form-control" name="propertyPetPermission" />
                    <Translate contentKey="riverApp.property.propertyPetPermission">Property Pet Permission</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyElevatorLabel" check>
                    <AvInput id="property-propertyElevator" type="checkbox" className="form-control" name="propertyElevator" />
                    <Translate contentKey="riverApp.property.propertyElevator">Property Elevator</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertySwimmingPoolLabel" check>
                    <AvInput id="property-propertySwimmingPool" type="checkbox" className="form-control" name="propertySwimmingPool" />
                    <Translate contentKey="riverApp.property.propertySwimmingPool">Property Swimming Pool</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyGymLabel" check>
                    <AvInput id="property-propertyGym" type="checkbox" className="form-control" name="propertyGym" />
                    <Translate contentKey="riverApp.property.propertyGym">Property Gym</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyFunctionalAreaLabel" check>
                    <AvInput id="property-propertyFunctionalArea" type="checkbox" className="form-control" name="propertyFunctionalArea" />
                    <Translate contentKey="riverApp.property.propertyFunctionalArea">Property Functional Area</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyOpen24hLabel" check>
                    <AvInput id="property-propertyOpen24h" type="checkbox" className="form-control" name="propertyOpen24h" />
                    <Translate contentKey="riverApp.property.propertyOpen24h">Property Open 24 H</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyCarParkLabel" check>
                    <AvInput id="property-propertyCarPark" type="checkbox" className="form-control" name="propertyCarPark" />
                    <Translate contentKey="riverApp.property.propertyCarPark">Property Car Park</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyBalconyLabel" check>
                    <AvInput id="property-propertyBalcony" type="checkbox" className="form-control" name="propertyBalcony" />
                    <Translate contentKey="riverApp.property.propertyBalcony">Property Balcony</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertySaunaLabel" check>
                    <AvInput id="property-propertySauna" type="checkbox" className="form-control" name="propertySauna" />
                    <Translate contentKey="riverApp.property.propertySauna">Property Sauna</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertySteamSaunaLabel" check>
                    <AvInput id="property-propertySteamSauna" type="checkbox" className="form-control" name="propertySteamSauna" />
                    <Translate contentKey="riverApp.property.propertySteamSauna">Property Steam Sauna</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyAttractionLabel" check>
                    <AvInput id="property-propertyAttraction" type="checkbox" className="form-control" name="propertyAttraction" />
                    <Translate contentKey="riverApp.property.propertyAttraction">Property Attraction</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertySpecialFeatureLabel" for="propertySpecialFeature">
                    <Translate contentKey="riverApp.property.propertySpecialFeature">Property Special Feature</Translate>
                  </Label>
                  <AvField id="property-propertySpecialFeature" type="text" name="propertySpecialFeature" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertyFurnitureOverviewLabel" for="propertyFurnitureOverview">
                    <Translate contentKey="riverApp.property.propertyFurnitureOverview">Property Furniture Overview</Translate>
                  </Label>
                  <AvField id="property-propertyFurnitureOverview" type="text" name="propertyFurnitureOverview" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertyLocationOverviewLabel" for="propertyLocationOverview">
                    <Translate contentKey="riverApp.property.propertyLocationOverview">Property Location Overview</Translate>
                  </Label>
                  <AvField id="property-propertyLocationOverview" type="text" name="propertyLocationOverview" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertyResidentialCommunityLabel" for="propertyResidentialCommunity">
                    <Translate contentKey="riverApp.property.propertyResidentialCommunity">Property Residential Community</Translate>
                  </Label>
                  <AvField id="property-propertyResidentialCommunity" type="text" name="propertyResidentialCommunity" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertyEducationalAspectLabel" for="propertyEducationalAspect">
                    <Translate contentKey="riverApp.property.propertyEducationalAspect">Property Educational Aspect</Translate>
                  </Label>
                  <AvField id="property-propertyEducationalAspect" type="text" name="propertyEducationalAspect" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertyExtraInfoLabel" for="propertyExtraInfo">
                    <Translate contentKey="riverApp.property.propertyExtraInfo">Property Extra Info</Translate>
                  </Label>
                  <AvField id="property-propertyExtraInfo" type="text" name="propertyExtraInfo" />
                </AvGroup>
                <AvGroup>
                  <AvGroup>
                    <Label id="propertyDraftLabel" for="propertyDraft">
                      <Translate contentKey="riverApp.property.propertyDraft">Property Draft</Translate>
                    </Label>
                    <br />
                    {propertyDraft ? (
                      <div>
                        <a onClick={openFile(propertyDraftContentType, propertyDraft)}>
                          <img src={`data:${propertyDraftContentType};base64,${propertyDraft}`} style={{ maxHeight: '100px' }} />
                        </a>
                        <br />
                        <Row>
                          <Col md="11">
                            <span>
                              {propertyDraftContentType}, {byteSize(propertyDraft)}
                            </span>
                          </Col>
                          <Col md="1">
                            <Button color="danger" onClick={this.clearBlob('propertyDraft')}>
                              <FontAwesomeIcon icon="times-circle" />
                            </Button>
                          </Col>
                        </Row>
                      </div>
                    ) : null}
                    <input id="file_propertyDraft" type="file" onChange={this.onBlobChange(true, 'propertyDraft')} accept="image/*" />
                  </AvGroup>
                </AvGroup>
                <AvGroup>
                  <Label id="longitudeLabel" for="longitude">
                    <Translate contentKey="riverApp.property.longitude">Longitude</Translate>
                  </Label>
                  <AvField id="property-longitude" type="number" className="form-control" name="longitude" />
                </AvGroup>
                <AvGroup>
                  <Label id="latitudeLabel" for="latitude">
                    <Translate contentKey="riverApp.property.latitude">Latitude</Translate>
                  </Label>
                  <AvField id="property-latitude" type="number" className="form-control" name="latitude" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertyGoodPriceLabel" check>
                    <AvInput id="property-propertyGoodPrice" type="checkbox" className="form-control" name="propertyGoodPrice" />
                    <Translate contentKey="riverApp.property.propertyGoodPrice">Property Good Price</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertySeenCountLabel" for="propertySeenCount">
                    <Translate contentKey="riverApp.property.propertySeenCount">Property Seen Count</Translate>
                  </Label>
                  <AvField id="property-propertySeenCount" type="number" className="form-control" name="propertySeenCount" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertyIsSoldLabel" check>
                    <AvInput id="property-propertyIsSold" type="checkbox" className="form-control" name="propertyIsSold" />
                    <Translate contentKey="riverApp.property.propertyIsSold">Property Is Sold</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyIsRentLabel" check>
                    <AvInput id="property-propertyIsRent" type="checkbox" className="form-control" name="propertyIsRent" />
                    <Translate contentKey="riverApp.property.propertyIsRent">Property Is Rent</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyAvailableLabel" check>
                    <AvInput id="property-propertyAvailable" type="checkbox" className="form-control" name="propertyAvailable" />
                    <Translate contentKey="riverApp.property.propertyAvailable">Property Available</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyRefrigeratorLabel" check>
                    <AvInput id="property-propertyRefrigerator" type="checkbox" className="form-control" name="propertyRefrigerator" />
                    <Translate contentKey="riverApp.property.propertyRefrigerator">Property Refrigerator</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="propertyAirconditionerLabel" check>
                    <AvInput id="property-propertyAirconditioner" type="checkbox" className="form-control" name="propertyAirconditioner" />
                    <Translate contentKey="riverApp.property.propertyAirconditioner">Property Airconditioner</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label for="location.id">
                    <Translate contentKey="riverApp.property.location">Location</Translate>
                  </Label>
                  <AvInput id="property-location" type="select" className="form-control" name="locationId" onChange={this.locationUpdate}>
                    <option value="" key="0" />
                    {locations
                      ? locations.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="residentialArea.residentialName">
                    <Translate contentKey="riverApp.property.residentialArea">Residential Area</Translate>
                  </Label>
                  <AvInput
                    id="property-residentialArea"
                    type="select"
                    className="form-control"
                    name="residentialAreaId"
                    onChange={this.residentialAreaUpdate}
                  >
                    <option value="" key="0" />
                    {residentialAreas
                      ? residentialAreas.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.residentialName}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="tags">
                    <Translate contentKey="riverApp.property.tag">Tag</Translate>
                  </Label>
                  <AvInput
                    id="property-tag"
                    type="select"
                    multiple
                    className="form-control"
                    name="faketags"
                    value={this.displaytag(propertyEntity)}
                    onChange={this.tagUpdate}
                  >
                    <option value="" key="0" />
                    {tags
                      ? tags.map(otherEntity => (
                          <option value={otherEntity.tagName} key={otherEntity.id}>
                            {otherEntity.tagName}
                          </option>
                        ))
                      : null}
                  </AvInput>
                  <AvInput id="property-tag" type="hidden" name="tags" value={this.state.idstag} />
                </AvGroup>
                <AvGroup>
                  <Label for="buildingTypes">
                    <Translate contentKey="riverApp.property.buildingtype">Buildingtype</Translate>
                  </Label>
                  <AvInput
                    id="property-buildingtype"
                    type="select"
                    multiple
                    className="form-control"
                    name="fakebuildingTypes"
                    value={this.displaybuildingtype(propertyEntity)}
                    onChange={this.buildingtypeUpdate}
                  >
                    <option value="" key="0" />
                    {buildingTypes
                      ? buildingTypes.map(otherEntity => (
                          <option value={otherEntity.typeName} key={otherEntity.id}>
                            {otherEntity.typeName}
                          </option>
                        ))
                      : null}
                  </AvInput>
                  <AvInput id="property-buildingtype" type="hidden" name="buildingtypes" value={this.state.idsbuildingtype} />
                </AvGroup>
                <AvGroup>
                  <Label for="photos">
                    <Translate contentKey="riverApp.property.photo">Photo</Translate>
                  </Label>
                  <AvInput
                    id="property-photo"
                    type="select"
                    multiple
                    className="form-control"
                    name="fakephotos"
                    value={this.displayphoto(propertyEntity)}
                    onChange={this.photoUpdate}
                  >
                    <option value="" key="0" />
                    {photos
                      ? photos.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                  <AvInput id="property-photo" type="hidden" name="photos" value={this.state.idsphoto} />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/property" replace color="info">
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
  locations: storeState.location.entities,
  residentialAreas: storeState.residentialArea.entities,
  tags: storeState.tag.entities,
  buildingTypes: storeState.buildingType.entities,
  photos: storeState.photo.entities,
  propertyEntity: storeState.property.entity,
  loading: storeState.property.loading,
  updating: storeState.property.updating
});

const mapDispatchToProps = {
  getLocations,
  getResidentialAreas,
  getTags,
  getBuildingTypes,
  getPhotos,
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
)(PropertyUpdate);
