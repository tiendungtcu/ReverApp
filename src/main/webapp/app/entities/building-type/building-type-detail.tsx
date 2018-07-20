import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './building-type.reducer';
import { IBuildingType } from 'app/shared/model/building-type.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBuildingTypeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class BuildingTypeDetail extends React.Component<IBuildingTypeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { buildingTypeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="riverApp.buildingType.detail.title">BuildingType</Translate> [<b>{buildingTypeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="typeName">
                <Translate contentKey="riverApp.buildingType.typeName">Type Name</Translate>
              </span>
            </dt>
            <dd>{buildingTypeEntity.typeName}</dd>
            <dt>
              <span id="typeSelect">
                <Translate contentKey="riverApp.buildingType.typeSelect">Type Select</Translate>
              </span>
            </dt>
            <dd>{buildingTypeEntity.typeSelect}</dd>
          </dl>
          <Button tag={Link} to="/entity/building-type" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/building-type/${buildingTypeEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ buildingType }: IRootState) => ({
  buildingTypeEntity: buildingType.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(BuildingTypeDetail);
