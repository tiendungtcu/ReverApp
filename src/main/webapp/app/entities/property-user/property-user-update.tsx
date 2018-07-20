import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { IProperty } from 'app/shared/model/property.model';
import { getEntities as getProperties } from 'app/entities/property/property.reducer';
import { getEntity, updateEntity, createEntity, reset } from './property-user.reducer';
import { IPropertyUser } from 'app/shared/model/property-user.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IPropertyUserUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IPropertyUserUpdateState {
  isNew: boolean;
  userId: number;
  propertyId: number;
}

export class PropertyUserUpdate extends React.Component<IPropertyUserUpdateProps, IPropertyUserUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      userId: 0,
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

    this.props.getUsers();
    this.props.getProperties();
  }

  saveEntity = (event, errors, values) => {
    values.likedDate = new Date(values.likedDate);
    values.sharedDate = new Date(values.sharedDate);

    if (errors.length === 0) {
      const { propertyUserEntity } = this.props;
      const entity = {
        ...propertyUserEntity,
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
    this.props.history.push('/entity/property-user');
  };

  userUpdate = element => {
    const login = element.target.value.toString();
    if (login === '') {
      this.setState({
        userId: -1
      });
    } else {
      for (const i in this.props.users) {
        if (login === this.props.users[i].login.toString()) {
          this.setState({
            userId: this.props.users[i].id
          });
        }
      }
    }
  };

  propertyUpdate = element => {
    const propertyName = element.target.value.toString();
    if (propertyName === '') {
      this.setState({
        propertyId: -1
      });
    } else {
      for (const i in this.props.properties) {
        if (propertyName === this.props.properties[i].propertyName.toString()) {
          this.setState({
            propertyId: this.props.properties[i].id
          });
        }
      }
    }
  };

  render() {
    const isInvalid = false;
    const { propertyUserEntity, users, properties, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="riverApp.propertyUser.home.createOrEditLabel">
              <Translate contentKey="riverApp.propertyUser.home.createOrEditLabel">Create or edit a PropertyUser</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : propertyUserEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="property-user-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="likedLabel" check>
                    <AvInput id="property-user-liked" type="checkbox" className="form-control" name="liked" />
                    <Translate contentKey="riverApp.propertyUser.liked">Liked</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="sharedLabel" check>
                    <AvInput id="property-user-shared" type="checkbox" className="form-control" name="shared" />
                    <Translate contentKey="riverApp.propertyUser.shared">Shared</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="likedDateLabel" for="likedDate">
                    <Translate contentKey="riverApp.propertyUser.likedDate">Liked Date</Translate>
                  </Label>
                  <AvInput
                    id="property-user-likedDate"
                    type="datetime-local"
                    className="form-control"
                    name="likedDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.propertyUserEntity.likedDate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="sharedDateLabel" for="sharedDate">
                    <Translate contentKey="riverApp.propertyUser.sharedDate">Shared Date</Translate>
                  </Label>
                  <AvInput
                    id="property-user-sharedDate"
                    type="datetime-local"
                    className="form-control"
                    name="sharedDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.propertyUserEntity.sharedDate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="user.login">
                    <Translate contentKey="riverApp.propertyUser.user">User</Translate>
                  </Label>
                  <AvInput id="property-user-user" type="select" className="form-control" name="userId" onChange={this.userUpdate}>
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
                  <Label for="property.propertyName">
                    <Translate contentKey="riverApp.propertyUser.property">Property</Translate>
                  </Label>
                  <AvInput
                    id="property-user-property"
                    type="select"
                    className="form-control"
                    name="propertyId"
                    onChange={this.propertyUpdate}
                  >
                    <option value="" key="0" />
                    {properties
                      ? properties.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.propertyName}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/property-user" replace color="info">
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
  users: storeState.userManagement.users,
  properties: storeState.property.entities,
  propertyUserEntity: storeState.propertyUser.entity,
  loading: storeState.propertyUser.loading,
  updating: storeState.propertyUser.updating
});

const mapDispatchToProps = {
  getUsers,
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
)(PropertyUserUpdate);
