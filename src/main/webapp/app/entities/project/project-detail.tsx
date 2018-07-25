import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './project.reducer';
import { IProject } from 'app/shared/model/project.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProjectDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class ProjectDetail extends React.Component<IProjectDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { projectEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="riverApp.project.detail.title">Project</Translate> [<b>{projectEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="projectName">
                <Translate contentKey="riverApp.project.projectName">Project Name</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectName}</dd>
            <dt>
              <span id="projectAlias">
                <Translate contentKey="riverApp.project.projectAlias">Project Alias</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectAlias}</dd>
            <dt>
              <span id="projectAvatarUrl">
                <Translate contentKey="riverApp.project.projectAvatarUrl">Project Avatar Url</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectAvatarUrl}</dd>
            <dt>
              <span id="projectDistrict">
                <Translate contentKey="riverApp.project.projectDistrict">Project District</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectDistrict}</dd>
            <dt>
              <span id="projectProvince">
                <Translate contentKey="riverApp.project.projectProvince">Project Province</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectProvince}</dd>
            <dt>
              <span id="residentialAreaId">
                <Translate contentKey="riverApp.project.residentialAreaId">Residential Area Id</Translate>
              </span>
            </dt>
            <dd>{projectEntity.residentialAreaId}</dd>
            <dt>
              <span id="projectRoad">
                <Translate contentKey="riverApp.project.projectRoad">Project Road</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectRoad}</dd>
            <dt>
              <span id="projectWard">
                <Translate contentKey="riverApp.project.projectWard">Project Ward</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectWard}</dd>
            <dt>
              <span id="projectStatus">
                <Translate contentKey="riverApp.project.projectStatus">Project Status</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectStatus}</dd>
            <dt>
              <span id="projectNoBlocks">
                <Translate contentKey="riverApp.project.projectNoBlocks">Project No Blocks</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectNoBlocks}</dd>
            <dt>
              <span id="projectNoFloors">
                <Translate contentKey="riverApp.project.projectNoFloors">Project No Floors</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectNoFloors}</dd>
            <dt>
              <span id="projectNoApartments">
                <Translate contentKey="riverApp.project.projectNoApartments">Project No Apartments</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectNoApartments}</dd>
            <dt>
              <span id="projectNoShophouse">
                <Translate contentKey="riverApp.project.projectNoShophouse">Project No Shophouse</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectNoShophouse}</dd>
            <dt>
              <span id="projectDescription">
                <Translate contentKey="riverApp.project.projectDescription">Project Description</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectDescription}</dd>
            <dt>
              <span id="projectMinSellPrice">
                <Translate contentKey="riverApp.project.projectMinSellPrice">Project Min Sell Price</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectMinSellPrice}</dd>
            <dt>
              <span id="projectMaxSellPrice">
                <Translate contentKey="riverApp.project.projectMaxSellPrice">Project Max Sell Price</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectMaxSellPrice}</dd>
            <dt>
              <span id="projectSellPriceUnit">
                <Translate contentKey="riverApp.project.projectSellPriceUnit">Project Sell Price Unit</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectSellPriceUnit}</dd>
            <dt>
              <span id="projectMinRentPrice">
                <Translate contentKey="riverApp.project.projectMinRentPrice">Project Min Rent Price</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectMinRentPrice}</dd>
            <dt>
              <span id="projectMaxRentPrice">
                <Translate contentKey="riverApp.project.projectMaxRentPrice">Project Max Rent Price</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectMaxRentPrice}</dd>
            <dt>
              <span id="projectRentPriceUnit">
                <Translate contentKey="riverApp.project.projectRentPriceUnit">Project Rent Price Unit</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectRentPriceUnit}</dd>
            <dt>
              <span id="projectStartedDate">
                <Translate contentKey="riverApp.project.projectStartedDate">Project Started Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={projectEntity.projectStartedDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="projectFinishingDate">
                <Translate contentKey="riverApp.project.projectFinishingDate">Project Finishing Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={projectEntity.projectFinishingDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="projectMinApartmentSquare">
                <Translate contentKey="riverApp.project.projectMinApartmentSquare">Project Min Apartment Square</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectMinApartmentSquare}</dd>
            <dt>
              <span id="projectMaxApartmentSquare">
                <Translate contentKey="riverApp.project.projectMaxApartmentSquare">Project Max Apartment Square</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectMaxApartmentSquare}</dd>
            <dt>
              <span id="projectGreenSpace">
                <Translate contentKey="riverApp.project.projectGreenSpace">Project Green Space</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectGreenSpace}</dd>
            <dt>
              <span id="projectBuildingDensity">
                <Translate contentKey="riverApp.project.projectBuildingDensity">Project Building Density</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectBuildingDensity}</dd>
            <dt>
              <span id="projectDesignCompany">
                <Translate contentKey="riverApp.project.projectDesignCompany">Project Design Company</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectDesignCompany}</dd>
            <dt>
              <span id="projectCarPark">
                <Translate contentKey="riverApp.project.projectCarPark">Project Car Park</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectCarPark ? 'true' : 'false'}</dd>
            <dt>
              <span id="projectBbqCourt">
                <Translate contentKey="riverApp.project.projectBbqCourt">Project Bbq Court</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectBbqCourt ? 'true' : 'false'}</dd>
            <dt>
              <span id="projectElevator">
                <Translate contentKey="riverApp.project.projectElevator">Project Elevator</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectElevator ? 'true' : 'false'}</dd>
            <dt>
              <span id="projectShoppingCenter">
                <Translate contentKey="riverApp.project.projectShoppingCenter">Project Shopping Center</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectShoppingCenter ? 'true' : 'false'}</dd>
            <dt>
              <span id="projectSwimmingPool">
                <Translate contentKey="riverApp.project.projectSwimmingPool">Project Swimming Pool</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectSwimmingPool ? 'true' : 'false'}</dd>
            <dt>
              <span id="projectCommunityRoom">
                <Translate contentKey="riverApp.project.projectCommunityRoom">Project Community Room</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectCommunityRoom ? 'true' : 'false'}</dd>
            <dt>
              <span id="projectGym">
                <Translate contentKey="riverApp.project.projectGym">Project Gym</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectGym ? 'true' : 'false'}</dd>
            <dt>
              <span id="projectCityPark">
                <Translate contentKey="riverApp.project.projectCityPark">Project City Park</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectCityPark ? 'true' : 'false'}</dd>
            <dt>
              <span id="projectGuard">
                <Translate contentKey="riverApp.project.projectGuard">Project Guard</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectGuard ? 'true' : 'false'}</dd>
            <dt>
              <span id="projectPlayGround">
                <Translate contentKey="riverApp.project.projectPlayGround">Project Play Ground</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectPlayGround ? 'true' : 'false'}</dd>
            <dt>
              <span id="longitude">
                <Translate contentKey="riverApp.project.longitude">Longitude</Translate>
              </span>
            </dt>
            <dd>{projectEntity.longitude}</dd>
            <dt>
              <span id="latitude">
                <Translate contentKey="riverApp.project.latitude">Latitude</Translate>
              </span>
            </dt>
            <dd>{projectEntity.latitude}</dd>
            <dt>
              <span id="projectSeenCount">
                <Translate contentKey="riverApp.project.projectSeenCount">Project Seen Count</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectSeenCount}</dd>
            <dt>
              <span id="projectAvailable">
                <Translate contentKey="riverApp.project.projectAvailable">Project Available</Translate>
              </span>
            </dt>
            <dd>{projectEntity.projectAvailable ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="riverApp.project.location">Location</Translate>
            </dt>
            <dd>{projectEntity.locationId ? projectEntity.locationId : ''}</dd>
            <dt>
              <Translate contentKey="riverApp.project.consultant">Consultant</Translate>
            </dt>
            <dd>{projectEntity.consultantLogin ? projectEntity.consultantLogin : ''}</dd>
            <dt>
              <Translate contentKey="riverApp.project.tag">Tag</Translate>
            </dt>
            <dd>
              {projectEntity.tags
                ? projectEntity.tags.map((val, i) => (
                    <span key={val.id}>
                      <a>{val.tagName}</a>
                      {i === projectEntity.tags.length - 1 ? '' : ', '}
                    </span>
                  ))
                : null}
            </dd>
            <dt>
              <Translate contentKey="riverApp.project.buildingtype">Buildingtype</Translate>
            </dt>
            <dd>
              {projectEntity.buildingtypes
                ? projectEntity.buildingtypes.map((val, i) => (
                    <span key={val.id}>
                      <a>{val.typeName}</a>
                      {i === projectEntity.buildingtypes.length - 1 ? '' : ', '}
                    </span>
                  ))
                : null}
            </dd>
            <dt>
              <Translate contentKey="riverApp.project.inverstor">Inverstor</Translate>
            </dt>
            <dd>
              {projectEntity.inverstors
                ? projectEntity.inverstors.map((val, i) => (
                    <span key={val.id}>
                      <a>{val.investorName}</a>
                      {i === projectEntity.inverstors.length - 1 ? '' : ', '}
                    </span>
                  ))
                : null}
            </dd>
            <dt>
              <Translate contentKey="riverApp.project.contractor">Contractor</Translate>
            </dt>
            <dd>
              {projectEntity.contractors
                ? projectEntity.contractors.map((val, i) => (
                    <span key={val.id}>
                      <a>{val.contractorName}</a>
                      {i === projectEntity.contractors.length - 1 ? '' : ', '}
                    </span>
                  ))
                : null}
            </dd>
          </dl>
          <Button tag={Link} to="/entity/project" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/project/${projectEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ project }: IRootState) => ({
  projectEntity: project.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ProjectDetail);
