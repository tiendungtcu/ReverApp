import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './location.reducer';
import { ILocation } from 'app/shared/model/location.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface ILocationUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface ILocationUpdateState {
  isNew: boolean;
}

export class LocationUpdate extends React.Component<ILocationUpdateProps, ILocationUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { locationEntity } = this.props;
      const entity = {
        ...locationEntity,
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
    this.props.history.push('/entity/location');
  };

  render() {
    const isInvalid = false;
    const { locationEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="riverApp.location.home.createOrEditLabel">
              <Translate contentKey="riverApp.location.home.createOrEditLabel">Create or edit a Location</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : locationEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="location-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="locationNameLabel" for="locationName">
                    <Translate contentKey="riverApp.location.locationName">Location Name</Translate>
                  </Label>
                  <AvField id="location-locationName" type="text" name="locationName" />
                </AvGroup>
                <AvGroup>
                  <Label id="locationFullAddressLabel" for="locationFullAddress">
                    <Translate contentKey="riverApp.location.locationFullAddress">Location Full Address</Translate>
                  </Label>
                  <AvField id="location-locationFullAddress" type="text" name="locationFullAddress" />
                </AvGroup>
                <AvGroup>
                  <Label id="locationNumberLabel" for="locationNumber">
                    <Translate contentKey="riverApp.location.locationNumber">Location Number</Translate>
                  </Label>
                  <AvField id="location-locationNumber" type="text" name="locationNumber" />
                </AvGroup>
                <AvGroup>
                  <Label id="locationRoadLabel" for="locationRoad">
                    <Translate contentKey="riverApp.location.locationRoad">Location Road</Translate>
                  </Label>
                  <AvField id="location-locationRoad" type="text" name="locationRoad" />
                </AvGroup>
                <AvGroup>
                  <Label id="locationWardLabel" for="locationWard">
                    <Translate contentKey="riverApp.location.locationWard">Location Ward</Translate>
                  </Label>
                  <AvField id="location-locationWard" type="text" name="locationWard" />
                </AvGroup>
                <AvGroup>
                  <Label id="locationDistrictLabel" for="locationDistrict">
                    <Translate contentKey="riverApp.location.locationDistrict">Location District</Translate>
                  </Label>
                  <AvField id="location-locationDistrict" type="text" name="locationDistrict" />
                </AvGroup>
                <AvGroup>
                  <Label id="locationProvinceLabel" for="locationProvince">
                    <Translate contentKey="riverApp.location.locationProvince">Location Province</Translate>
                  </Label>
                  <AvField id="location-locationProvince" type="text" name="locationProvince" />
                </AvGroup>
                <AvGroup>
                  <Label id="locationCountryLabel" for="locationCountry">
                    <Translate contentKey="riverApp.location.locationCountry">Location Country</Translate>
                  </Label>
                  <AvField id="location-locationCountry" type="text" name="locationCountry" />
                </AvGroup>
                <AvGroup>
                  <Label id="locationGmapUrlLabel" for="locationGmapUrl">
                    <Translate contentKey="riverApp.location.locationGmapUrl">Location Gmap Url</Translate>
                  </Label>
                  <AvField id="location-locationGmapUrl" type="text" name="locationGmapUrl" />
                </AvGroup>
                <AvGroup>
                  <Label id="longitudeLabel" for="longitude">
                    <Translate contentKey="riverApp.location.longitude">Longitude</Translate>
                  </Label>
                  <AvField
                    id="location-longitude"
                    type="number"
                    className="form-control"
                    name="longitude"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="latitudeLabel" for="latitude">
                    <Translate contentKey="riverApp.location.latitude">Latitude</Translate>
                  </Label>
                  <AvField
                    id="location-latitude"
                    type="number"
                    className="form-control"
                    name="latitude"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="locationHideLabel" check>
                    <AvInput id="location-locationHide" type="checkbox" className="form-control" name="locationHide" />
                    <Translate contentKey="riverApp.location.locationHide">Location Hide</Translate>
                  </Label>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/location" replace color="info">
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
  locationEntity: storeState.location.entity,
  loading: storeState.location.loading,
  updating: storeState.location.updating
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LocationUpdate);
