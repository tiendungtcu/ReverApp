import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './location.reducer';
import { ILocation } from 'app/shared/model/location.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILocationDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class LocationDetail extends React.Component<ILocationDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { locationEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="riverApp.location.detail.title">Location</Translate> [<b>{locationEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="locationName">
                <Translate contentKey="riverApp.location.locationName">Location Name</Translate>
              </span>
            </dt>
            <dd>{locationEntity.locationName}</dd>
            <dt>
              <span id="locationFullAddress">
                <Translate contentKey="riverApp.location.locationFullAddress">Location Full Address</Translate>
              </span>
            </dt>
            <dd>{locationEntity.locationFullAddress}</dd>
            <dt>
              <span id="locationNumber">
                <Translate contentKey="riverApp.location.locationNumber">Location Number</Translate>
              </span>
            </dt>
            <dd>{locationEntity.locationNumber}</dd>
            <dt>
              <span id="locationRoad">
                <Translate contentKey="riverApp.location.locationRoad">Location Road</Translate>
              </span>
            </dt>
            <dd>{locationEntity.locationRoad}</dd>
            <dt>
              <span id="locationWard">
                <Translate contentKey="riverApp.location.locationWard">Location Ward</Translate>
              </span>
            </dt>
            <dd>{locationEntity.locationWard}</dd>
            <dt>
              <span id="locationDistrict">
                <Translate contentKey="riverApp.location.locationDistrict">Location District</Translate>
              </span>
            </dt>
            <dd>{locationEntity.locationDistrict}</dd>
            <dt>
              <span id="locationProvince">
                <Translate contentKey="riverApp.location.locationProvince">Location Province</Translate>
              </span>
            </dt>
            <dd>{locationEntity.locationProvince}</dd>
            <dt>
              <span id="locationCountry">
                <Translate contentKey="riverApp.location.locationCountry">Location Country</Translate>
              </span>
            </dt>
            <dd>{locationEntity.locationCountry}</dd>
            <dt>
              <span id="locationGmapUrl">
                <Translate contentKey="riverApp.location.locationGmapUrl">Location Gmap Url</Translate>
              </span>
            </dt>
            <dd>{locationEntity.locationGmapUrl}</dd>
            <dt>
              <span id="longitude">
                <Translate contentKey="riverApp.location.longitude">Longitude</Translate>
              </span>
            </dt>
            <dd>{locationEntity.longitude}</dd>
            <dt>
              <span id="latitude">
                <Translate contentKey="riverApp.location.latitude">Latitude</Translate>
              </span>
            </dt>
            <dd>{locationEntity.latitude}</dd>
            <dt>
              <span id="locationHide">
                <Translate contentKey="riverApp.location.locationHide">Location Hide</Translate>
              </span>
            </dt>
            <dd>{locationEntity.locationHide ? 'true' : 'false'}</dd>
          </dl>
          <Button tag={Link} to="/entity/location" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/location/${locationEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ location }: IRootState) => ({
  locationEntity: location.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LocationDetail);
