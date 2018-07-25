import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ILocation } from 'app/shared/model/location.model';
import { getEntities as getLocations } from 'app/entities/location/location.reducer';
import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { ITag } from 'app/shared/model/tag.model';
import { getEntities as getTags } from 'app/entities/tag/tag.reducer';
import { IBuildingType } from 'app/shared/model/building-type.model';
import { getEntities as getBuildingTypes } from 'app/entities/building-type/building-type.reducer';
import { IInvestor } from 'app/shared/model/investor.model';
import { getEntities as getInvestors } from 'app/entities/investor/investor.reducer';
import { IContractor } from 'app/shared/model/contractor.model';
import { getEntities as getContractors } from 'app/entities/contractor/contractor.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './project.reducer';
import { IProject } from 'app/shared/model/project.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IProjectUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IProjectUpdateState {
  isNew: boolean;
  idstag: any[];
  idsbuildingtype: any[];
  idsinverstor: any[];
  idscontractor: any[];
  locationId: number;
  consultantId: number;
}

export class ProjectUpdate extends React.Component<IProjectUpdateProps, IProjectUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      idstag: [],
      idsbuildingtype: [],
      idsinverstor: [],
      idscontractor: [],
      locationId: 0,
      consultantId: 0,
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
    this.props.getUsers();
    this.props.getTags();
    this.props.getBuildingTypes();
    this.props.getInvestors();
    this.props.getContractors();
  }

  onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => this.props.setBlob(name, data, contentType), isAnImage);
  };

  clearBlob = name => () => {
    this.props.setBlob(name, undefined, undefined);
  };

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { projectEntity } = this.props;
      const entity = {
        ...projectEntity,
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
    this.props.history.push('/entity/project');
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

  consultantUpdate = element => {
    const login = element.target.value.toString();
    if (login === '') {
      this.setState({
        consultantId: -1
      });
    } else {
      for (const i in this.props.users) {
        if (login === this.props.users[i].login.toString()) {
          this.setState({
            consultantId: this.props.users[i].id
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

  inverstorUpdate = element => {
    const selected = Array.from(element.target.selectedOptions).map((e: any) => e.value);
    this.setState({
      idsinverstor: keysToValues(selected, this.props.investors, 'investorName')
    });
  };

  contractorUpdate = element => {
    const selected = Array.from(element.target.selectedOptions).map((e: any) => e.value);
    this.setState({
      idscontractor: keysToValues(selected, this.props.contractors, 'contractorName')
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

  displayinverstor(value: any) {
    if (this.state.idsinverstor && this.state.idsinverstor.length !== 0) {
      const list = [];
      for (const i in this.state.idsinverstor) {
        if (this.state.idsinverstor[i]) {
          list.push(this.state.idsinverstor[i].investorName);
        }
      }
      return list;
    }
    if (value.inverstors && value.inverstors.length !== 0) {
      const list = [];
      for (const i in value.inverstors) {
        if (value.inverstors[i]) {
          list.push(value.inverstors[i].investorName);
        }
      }
      this.setState({
        idsinverstor: keysToValues(list, this.props.investors, 'investorName')
      });
      return list;
    }
    return null;
  }

  displaycontractor(value: any) {
    if (this.state.idscontractor && this.state.idscontractor.length !== 0) {
      const list = [];
      for (const i in this.state.idscontractor) {
        if (this.state.idscontractor[i]) {
          list.push(this.state.idscontractor[i].contractorName);
        }
      }
      return list;
    }
    if (value.contractors && value.contractors.length !== 0) {
      const list = [];
      for (const i in value.contractors) {
        if (value.contractors[i]) {
          list.push(value.contractors[i].contractorName);
        }
      }
      this.setState({
        idscontractor: keysToValues(list, this.props.contractors, 'contractorName')
      });
      return list;
    }
    return null;
  }

  render() {
    const isInvalid = false;
    const { projectEntity, locations, users, tags, buildingTypes, investors, contractors, loading, updating } = this.props;
    const { isNew } = this.state;

    const { projectDescription } = projectEntity;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="riverApp.project.home.createOrEditLabel">
              <Translate contentKey="riverApp.project.home.createOrEditLabel">Create or edit a Project</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : projectEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="project-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="projectNameLabel" for="projectName">
                    <Translate contentKey="riverApp.project.projectName">Project Name</Translate>
                  </Label>
                  <AvField
                    id="project-projectName"
                    type="text"
                    name="projectName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 128, errorMessage: translate('entity.validation.maxlength', { max: 128 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="projectAliasLabel" for="projectAlias">
                    <Translate contentKey="riverApp.project.projectAlias">Project Alias</Translate>
                  </Label>
                  <AvField
                    id="project-projectAlias"
                    type="text"
                    name="projectAlias"
                    validate={{
                      maxLength: { value: 128, errorMessage: translate('entity.validation.maxlength', { max: 128 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="projectAvatarUrlLabel" for="projectAvatarUrl">
                    <Translate contentKey="riverApp.project.projectAvatarUrl">Project Avatar Url</Translate>
                  </Label>
                  <AvField id="project-projectAvatarUrl" type="text" name="projectAvatarUrl" />
                </AvGroup>
                <AvGroup>
                  <Label id="projectDistrictLabel" for="projectDistrict">
                    <Translate contentKey="riverApp.project.projectDistrict">Project District</Translate>
                  </Label>
                  <AvField
                    id="project-projectDistrict"
                    type="text"
                    name="projectDistrict"
                    validate={{
                      maxLength: { value: 64, errorMessage: translate('entity.validation.maxlength', { max: 64 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="projectProvinceLabel" for="projectProvince">
                    <Translate contentKey="riverApp.project.projectProvince">Project Province</Translate>
                  </Label>
                  <AvField
                    id="project-projectProvince"
                    type="text"
                    name="projectProvince"
                    validate={{
                      maxLength: { value: 64, errorMessage: translate('entity.validation.maxlength', { max: 64 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="residentialAreaIdLabel" for="residentialAreaId">
                    <Translate contentKey="riverApp.project.residentialAreaId">Residential Area Id</Translate>
                  </Label>
                  <AvField id="project-residentialAreaId" type="number" className="form-control" name="residentialAreaId" />
                </AvGroup>
                <AvGroup>
                  <Label id="projectRoadLabel" for="projectRoad">
                    <Translate contentKey="riverApp.project.projectRoad">Project Road</Translate>
                  </Label>
                  <AvField
                    id="project-projectRoad"
                    type="text"
                    name="projectRoad"
                    validate={{
                      maxLength: { value: 128, errorMessage: translate('entity.validation.maxlength', { max: 128 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="projectWardLabel" for="projectWard">
                    <Translate contentKey="riverApp.project.projectWard">Project Ward</Translate>
                  </Label>
                  <AvField
                    id="project-projectWard"
                    type="text"
                    name="projectWard"
                    validate={{
                      maxLength: { value: 64, errorMessage: translate('entity.validation.maxlength', { max: 64 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="projectStatusLabel">
                    <Translate contentKey="riverApp.project.projectStatus">Project Status</Translate>
                  </Label>
                  <AvInput
                    id="project-projectStatus"
                    type="select"
                    className="form-control"
                    name="projectStatus"
                    value={(!isNew && projectEntity.projectStatus) || 'SELLING'}
                  >
                    <option value="SELLING">SELLING</option>
                    <option value="PRESELL">PRESELL</option>
                    <option value="CLOSED">CLOSED</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="projectNoBlocksLabel" for="projectNoBlocks">
                    <Translate contentKey="riverApp.project.projectNoBlocks">Project No Blocks</Translate>
                  </Label>
                  <AvField
                    id="project-projectNoBlocks"
                    type="number"
                    className="form-control"
                    name="projectNoBlocks"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="projectNoFloorsLabel" for="projectNoFloors">
                    <Translate contentKey="riverApp.project.projectNoFloors">Project No Floors</Translate>
                  </Label>
                  <AvField
                    id="project-projectNoFloors"
                    type="number"
                    className="form-control"
                    name="projectNoFloors"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="projectNoApartmentsLabel" for="projectNoApartments">
                    <Translate contentKey="riverApp.project.projectNoApartments">Project No Apartments</Translate>
                  </Label>
                  <AvField
                    id="project-projectNoApartments"
                    type="number"
                    className="form-control"
                    name="projectNoApartments"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="projectNoShophouseLabel" for="projectNoShophouse">
                    <Translate contentKey="riverApp.project.projectNoShophouse">Project No Shophouse</Translate>
                  </Label>
                  <AvField id="project-projectNoShophouse" type="number" className="form-control" name="projectNoShophouse" />
                </AvGroup>
                <AvGroup>
                  <Label id="projectDescriptionLabel" for="projectDescription">
                    <Translate contentKey="riverApp.project.projectDescription">Project Description</Translate>
                  </Label>
                  <AvField id="project-projectDescription" type="text" name="projectDescription" />
                </AvGroup>
                <AvGroup>
                  <Label id="projectMinSellPriceLabel" for="projectMinSellPrice">
                    <Translate contentKey="riverApp.project.projectMinSellPrice">Project Min Sell Price</Translate>
                  </Label>
                  <AvField
                    id="project-projectMinSellPrice"
                    type="number"
                    className="form-control"
                    name="projectMinSellPrice"
                    validate={{
                      min: { value: 0, errorMessage: translate('entity.validation.min', { min: 0 }) },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="projectMaxSellPriceLabel" for="projectMaxSellPrice">
                    <Translate contentKey="riverApp.project.projectMaxSellPrice">Project Max Sell Price</Translate>
                  </Label>
                  <AvField id="project-projectMaxSellPrice" type="number" className="form-control" name="projectMaxSellPrice" />
                </AvGroup>
                <AvGroup>
                  <Label id="projectSellPriceUnitLabel">
                    <Translate contentKey="riverApp.project.projectSellPriceUnit">Project Sell Price Unit</Translate>
                  </Label>
                  <AvInput
                    id="project-projectSellPriceUnit"
                    type="select"
                    className="form-control"
                    name="projectSellPriceUnit"
                    value={(!isNew && projectEntity.projectSellPriceUnit) || 'THOUSAND'}
                  >
                    <option value="THOUSAND">THOUSAND</option>
                    <option value="MILLION">MILLION</option>
                    <option value="BILLION">BILLION</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="projectMinRentPriceLabel" for="projectMinRentPrice">
                    <Translate contentKey="riverApp.project.projectMinRentPrice">Project Min Rent Price</Translate>
                  </Label>
                  <AvField
                    id="project-projectMinRentPrice"
                    type="number"
                    className="form-control"
                    name="projectMinRentPrice"
                    validate={{
                      min: { value: 0, errorMessage: translate('entity.validation.min', { min: 0 }) },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="projectMaxRentPriceLabel" for="projectMaxRentPrice">
                    <Translate contentKey="riverApp.project.projectMaxRentPrice">Project Max Rent Price</Translate>
                  </Label>
                  <AvField id="project-projectMaxRentPrice" type="number" className="form-control" name="projectMaxRentPrice" />
                </AvGroup>
                <AvGroup>
                  <Label id="projectRentPriceUnitLabel">
                    <Translate contentKey="riverApp.project.projectRentPriceUnit">Project Rent Price Unit</Translate>
                  </Label>
                  <AvInput
                    id="project-projectRentPriceUnit"
                    type="select"
                    className="form-control"
                    name="projectRentPriceUnit"
                    value={(!isNew && projectEntity.projectRentPriceUnit) || 'THOUSAND'}
                  >
                    <option value="THOUSAND">THOUSAND</option>
                    <option value="MILLION">MILLION</option>
                    <option value="BILLION">BILLION</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="projectStartedDateLabel" for="projectStartedDate">
                    <Translate contentKey="riverApp.project.projectStartedDate">Project Started Date</Translate>
                  </Label>
                  <AvField id="project-projectStartedDate" type="date" className="form-control" name="projectStartedDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="projectFinishingDateLabel" for="projectFinishingDate">
                    <Translate contentKey="riverApp.project.projectFinishingDate">Project Finishing Date</Translate>
                  </Label>
                  <AvField id="project-projectFinishingDate" type="date" className="form-control" name="projectFinishingDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="projectMinApartmentSquareLabel" for="projectMinApartmentSquare">
                    <Translate contentKey="riverApp.project.projectMinApartmentSquare">Project Min Apartment Square</Translate>
                  </Label>
                  <AvField
                    id="project-projectMinApartmentSquare"
                    type="number"
                    className="form-control"
                    name="projectMinApartmentSquare"
                    validate={{
                      min: { value: 1, errorMessage: translate('entity.validation.min', { min: 1 }) },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="projectMaxApartmentSquareLabel" for="projectMaxApartmentSquare">
                    <Translate contentKey="riverApp.project.projectMaxApartmentSquare">Project Max Apartment Square</Translate>
                  </Label>
                  <AvField id="project-projectMaxApartmentSquare" type="number" className="form-control" name="projectMaxApartmentSquare" />
                </AvGroup>
                <AvGroup>
                  <Label id="projectGreenSpaceLabel" for="projectGreenSpace">
                    <Translate contentKey="riverApp.project.projectGreenSpace">Project Green Space</Translate>
                  </Label>
                  <AvField
                    id="project-projectGreenSpace"
                    type="number"
                    className="form-control"
                    name="projectGreenSpace"
                    validate={{
                      min: { value: 0, errorMessage: translate('entity.validation.min', { min: 0 }) },
                      max: { value: 100, errorMessage: translate('entity.validation.max', { max: 100 }) },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="projectBuildingDensityLabel" for="projectBuildingDensity">
                    <Translate contentKey="riverApp.project.projectBuildingDensity">Project Building Density</Translate>
                  </Label>
                  <AvField
                    id="project-projectBuildingDensity"
                    type="number"
                    className="form-control"
                    name="projectBuildingDensity"
                    validate={{
                      min: { value: 0, errorMessage: translate('entity.validation.min', { min: 0 }) },
                      max: { value: 100, errorMessage: translate('entity.validation.max', { max: 100 }) },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="projectDesignCompanyLabel" for="projectDesignCompany">
                    <Translate contentKey="riverApp.project.projectDesignCompany">Project Design Company</Translate>
                  </Label>
                  <AvField id="project-projectDesignCompany" type="text" name="projectDesignCompany" />
                </AvGroup>
                <AvGroup>
                  <Label id="projectCarParkLabel" check>
                    <AvInput id="project-projectCarPark" type="checkbox" className="form-control" name="projectCarPark" />
                    <Translate contentKey="riverApp.project.projectCarPark">Project Car Park</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="projectBbqCourtLabel" check>
                    <AvInput id="project-projectBbqCourt" type="checkbox" className="form-control" name="projectBbqCourt" />
                    <Translate contentKey="riverApp.project.projectBbqCourt">Project Bbq Court</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="projectElevatorLabel" check>
                    <AvInput id="project-projectElevator" type="checkbox" className="form-control" name="projectElevator" />
                    <Translate contentKey="riverApp.project.projectElevator">Project Elevator</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="projectShoppingCenterLabel" check>
                    <AvInput id="project-projectShoppingCenter" type="checkbox" className="form-control" name="projectShoppingCenter" />
                    <Translate contentKey="riverApp.project.projectShoppingCenter">Project Shopping Center</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="projectSwimmingPoolLabel" check>
                    <AvInput id="project-projectSwimmingPool" type="checkbox" className="form-control" name="projectSwimmingPool" />
                    <Translate contentKey="riverApp.project.projectSwimmingPool">Project Swimming Pool</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="projectCommunityRoomLabel" check>
                    <AvInput id="project-projectCommunityRoom" type="checkbox" className="form-control" name="projectCommunityRoom" />
                    <Translate contentKey="riverApp.project.projectCommunityRoom">Project Community Room</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="projectGymLabel" check>
                    <AvInput id="project-projectGym" type="checkbox" className="form-control" name="projectGym" />
                    <Translate contentKey="riverApp.project.projectGym">Project Gym</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="projectCityParkLabel" check>
                    <AvInput id="project-projectCityPark" type="checkbox" className="form-control" name="projectCityPark" />
                    <Translate contentKey="riverApp.project.projectCityPark">Project City Park</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="projectGuardLabel" check>
                    <AvInput id="project-projectGuard" type="checkbox" className="form-control" name="projectGuard" />
                    <Translate contentKey="riverApp.project.projectGuard">Project Guard</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="projectPlayGroundLabel" check>
                    <AvInput id="project-projectPlayGround" type="checkbox" className="form-control" name="projectPlayGround" />
                    <Translate contentKey="riverApp.project.projectPlayGround">Project Play Ground</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="longitudeLabel" for="longitude">
                    <Translate contentKey="riverApp.project.longitude">Longitude</Translate>
                  </Label>
                  <AvField id="project-longitude" type="number" className="form-control" name="longitude" />
                </AvGroup>
                <AvGroup>
                  <Label id="latitudeLabel" for="latitude">
                    <Translate contentKey="riverApp.project.latitude">Latitude</Translate>
                  </Label>
                  <AvField id="project-latitude" type="number" className="form-control" name="latitude" />
                </AvGroup>
                <AvGroup>
                  <Label id="projectSeenCountLabel" for="projectSeenCount">
                    <Translate contentKey="riverApp.project.projectSeenCount">Project Seen Count</Translate>
                  </Label>
                  <AvField id="project-projectSeenCount" type="number" className="form-control" name="projectSeenCount" />
                </AvGroup>
                <AvGroup>
                  <Label id="projectAvailableLabel" check>
                    <AvInput id="project-projectAvailable" type="checkbox" className="form-control" name="projectAvailable" />
                    <Translate contentKey="riverApp.project.projectAvailable">Project Available</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label for="location.id">
                    <Translate contentKey="riverApp.project.location">Location</Translate>
                  </Label>
                  <AvInput id="project-location" type="select" className="form-control" name="locationId" onChange={this.locationUpdate}>
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
                  <Label for="consultant.login">
                    <Translate contentKey="riverApp.project.consultant">Consultant</Translate>
                  </Label>
                  <AvInput
                    id="project-consultant"
                    type="select"
                    className="form-control"
                    name="consultantId"
                    onChange={this.consultantUpdate}
                  >
                    <option value="" key="0" />
                    {users
                      ? users.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.login}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="tags">
                    <Translate contentKey="riverApp.project.tag">Tag</Translate>
                  </Label>
                  <AvInput
                    id="project-tag"
                    type="select"
                    multiple
                    className="form-control"
                    name="faketags"
                    value={this.displaytag(projectEntity)}
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
                  <AvInput id="project-tag" type="hidden" name="tags" value={this.state.idstag} />
                </AvGroup>
                <AvGroup>
                  <Label for="buildingTypes">
                    <Translate contentKey="riverApp.project.buildingtype">Buildingtype</Translate>
                  </Label>
                  <AvInput
                    id="project-buildingtype"
                    type="select"
                    multiple
                    className="form-control"
                    name="fakebuildingTypes"
                    value={this.displaybuildingtype(projectEntity)}
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
                  <AvInput id="project-buildingtype" type="hidden" name="buildingtypes" value={this.state.idsbuildingtype} />
                </AvGroup>
                <AvGroup>
                  <Label for="investors">
                    <Translate contentKey="riverApp.project.inverstor">Inverstor</Translate>
                  </Label>
                  <AvInput
                    id="project-inverstor"
                    type="select"
                    multiple
                    className="form-control"
                    name="fakeinvestors"
                    value={this.displayinverstor(projectEntity)}
                    onChange={this.inverstorUpdate}
                  >
                    <option value="" key="0" />
                    {investors
                      ? investors.map(otherEntity => (
                          <option value={otherEntity.investorName} key={otherEntity.id}>
                            {otherEntity.investorName}
                          </option>
                        ))
                      : null}
                  </AvInput>
                  <AvInput id="project-inverstor" type="hidden" name="inverstors" value={this.state.idsinverstor} />
                </AvGroup>
                <AvGroup>
                  <Label for="contractors">
                    <Translate contentKey="riverApp.project.contractor">Contractor</Translate>
                  </Label>
                  <AvInput
                    id="project-contractor"
                    type="select"
                    multiple
                    className="form-control"
                    name="fakecontractors"
                    value={this.displaycontractor(projectEntity)}
                    onChange={this.contractorUpdate}
                  >
                    <option value="" key="0" />
                    {contractors
                      ? contractors.map(otherEntity => (
                          <option value={otherEntity.contractorName} key={otherEntity.id}>
                            {otherEntity.contractorName}
                          </option>
                        ))
                      : null}
                  </AvInput>
                  <AvInput id="project-contractor" type="hidden" name="contractors" value={this.state.idscontractor} />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/project" replace color="info">
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
  users: storeState.userManagement.users,
  tags: storeState.tag.entities,
  buildingTypes: storeState.buildingType.entities,
  investors: storeState.investor.entities,
  contractors: storeState.contractor.entities,
  projectEntity: storeState.project.entity,
  loading: storeState.project.loading,
  updating: storeState.project.updating
});

const mapDispatchToProps = {
  getLocations,
  getUsers,
  getTags,
  getBuildingTypes,
  getInvestors,
  getContractors,
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
)(ProjectUpdate);
