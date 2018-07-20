import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IProject } from 'app/shared/model/project.model';
import { getEntities as getProjects } from 'app/entities/project/project.reducer';
import { IProperty } from 'app/shared/model/property.model';
import { getEntities as getProperties } from 'app/entities/property/property.reducer';
import { getEntity, updateEntity, createEntity, reset } from './building-type.reducer';
import { IBuildingType } from 'app/shared/model/building-type.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IBuildingTypeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IBuildingTypeUpdateState {
  isNew: boolean;
  projectId: number;
  propertyId: number;
}

export class BuildingTypeUpdate extends React.Component<IBuildingTypeUpdateProps, IBuildingTypeUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      projectId: 0,
      propertyId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getProjects();
    this.props.getProperties();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { buildingTypeEntity } = this.props;
      const entity = {
        ...buildingTypeEntity,
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
    this.props.history.push('/entity/building-type');
  };

  render() {
    const isInvalid = false;
    const { buildingTypeEntity, projects, properties, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="riverApp.buildingType.home.createOrEditLabel">
              <Translate contentKey="riverApp.buildingType.home.createOrEditLabel">Create or edit a BuildingType</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : buildingTypeEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="building-type-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="typeNameLabel" for="typeName">
                    <Translate contentKey="riverApp.buildingType.typeName">Type Name</Translate>
                  </Label>
                  <AvField
                    id="building-type-typeName"
                    type="text"
                    name="typeName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="typeSelectLabel">
                    <Translate contentKey="riverApp.buildingType.typeSelect">Type Select</Translate>
                  </Label>
                  <AvInput
                    id="building-type-typeSelect"
                    type="select"
                    className="form-control"
                    name="typeSelect"
                    value={(!isNew && buildingTypeEntity.typeSelect) || 'PROJECT'}
                  >
                    <option value="PROJECT">PROJECT</option>
                    <option value="PROPERTY">PROPERTY</option>
                    <option value="LAND">LAND</option>
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/building-type" replace color="info">
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
  projects: storeState.project.entities,
  properties: storeState.property.entities,
  buildingTypeEntity: storeState.buildingType.entity,
  loading: storeState.buildingType.loading,
  updating: storeState.buildingType.updating
});

const mapDispatchToProps = {
  getProjects,
  getProperties,
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
)(BuildingTypeUpdate);
