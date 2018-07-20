import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { IProperty } from 'app/shared/model/property.model';
import { getEntities as getProperties } from 'app/entities/property/property.reducer';
import { IProject } from 'app/shared/model/project.model';
import { getEntities as getProjects } from 'app/entities/project/project.reducer';
import { getEntity, updateEntity, createEntity, reset } from './request.reducer';
import { IRequest } from 'app/shared/model/request.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IRequestUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IRequestUpdateState {
  isNew: boolean;
  userId: number;
  propertyId: number;
  projectId: number;
}

export class RequestUpdate extends React.Component<IRequestUpdateProps, IRequestUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      userId: 0,
      propertyId: 0,
      projectId: 0,
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
    this.props.getProjects();
  }

  saveEntity = (event, errors, values) => {
    values.requestMeetingDate = new Date(values.requestMeetingDate);
    values.requestCreatedDate = new Date(values.requestCreatedDate);

    if (errors.length === 0) {
      const { requestEntity } = this.props;
      const entity = {
        ...requestEntity,
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
    this.props.history.push('/entity/request');
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

  projectUpdate = element => {
    const projectName = element.target.value.toString();
    if (projectName === '') {
      this.setState({
        projectId: -1
      });
    } else {
      for (const i in this.props.projects) {
        if (projectName === this.props.projects[i].projectName.toString()) {
          this.setState({
            projectId: this.props.projects[i].id
          });
        }
      }
    }
  };

  render() {
    const isInvalid = false;
    const { requestEntity, users, properties, projects, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="riverApp.request.home.createOrEditLabel">
              <Translate contentKey="riverApp.request.home.createOrEditLabel">Create or edit a Request</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : requestEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="request-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="requestFirstNameLabel" for="requestFirstName">
                    <Translate contentKey="riverApp.request.requestFirstName">Request First Name</Translate>
                  </Label>
                  <AvField
                    id="request-requestFirstName"
                    type="text"
                    name="requestFirstName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="requestLastNameLabel" for="requestLastName">
                    <Translate contentKey="riverApp.request.requestLastName">Request Last Name</Translate>
                  </Label>
                  <AvField
                    id="request-requestLastName"
                    type="text"
                    name="requestLastName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="requestEmailLabel" for="requestEmail">
                    <Translate contentKey="riverApp.request.requestEmail">Request Email</Translate>
                  </Label>
                  <AvField
                    id="request-requestEmail"
                    type="text"
                    name="requestEmail"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="requestPhoneLabel" for="requestPhone">
                    <Translate contentKey="riverApp.request.requestPhone">Request Phone</Translate>
                  </Label>
                  <AvField
                    id="request-requestPhone"
                    type="text"
                    name="requestPhone"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="requestGetAnalysisLabel" check>
                    <AvInput id="request-requestGetAnalysis" type="checkbox" className="form-control" name="requestGetAnalysis" />
                    <Translate contentKey="riverApp.request.requestGetAnalysis">Request Get Analysis</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="requestGetPriceLabel" check>
                    <AvInput id="request-requestGetPrice" type="checkbox" className="form-control" name="requestGetPrice" />
                    <Translate contentKey="riverApp.request.requestGetPrice">Request Get Price</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="requestPageUrlLabel" for="requestPageUrl">
                    <Translate contentKey="riverApp.request.requestPageUrl">Request Page Url</Translate>
                  </Label>
                  <AvField id="request-requestPageUrl" type="text" name="requestPageUrl" />
                </AvGroup>
                <AvGroup>
                  <Label id="requestPageNameLabel" for="requestPageName">
                    <Translate contentKey="riverApp.request.requestPageName">Request Page Name</Translate>
                  </Label>
                  <AvField id="request-requestPageName" type="text" name="requestPageName" />
                </AvGroup>
                <AvGroup>
                  <Label id="requestPropertyIdLabel" for="requestPropertyId">
                    <Translate contentKey="riverApp.request.requestPropertyId">Request Property Id</Translate>
                  </Label>
                  <AvField id="request-requestPropertyId" type="number" className="form-control" name="requestPropertyId" />
                </AvGroup>
                <AvGroup>
                  <Label id="requestPropertyTypeLabel">
                    <Translate contentKey="riverApp.request.requestPropertyType">Request Property Type</Translate>
                  </Label>
                  <AvInput
                    id="request-requestPropertyType"
                    type="select"
                    className="form-control"
                    name="requestPropertyType"
                    value={(!isNew && requestEntity.requestPropertyType) || 'PROJECT'}
                  >
                    <option value="PROJECT">PROJECT</option>
                    <option value="PROPERTY">PROPERTY</option>
                    <option value="LAND">LAND</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="requestTypeLabel">
                    <Translate contentKey="riverApp.request.requestType">Request Type</Translate>
                  </Label>
                  <AvInput
                    id="request-requestType"
                    type="select"
                    className="form-control"
                    name="requestType"
                    value={(!isNew && requestEntity.requestType) || 'MEETING'}
                  >
                    <option value="MEETING">MEETING</option>
                    <option value="PRICE">PRICE</option>
                    <option value="QUESTION">QUESTION</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="requestMeetingDateLabel" for="requestMeetingDate">
                    <Translate contentKey="riverApp.request.requestMeetingDate">Request Meeting Date</Translate>
                  </Label>
                  <AvInput
                    id="request-requestMeetingDate"
                    type="datetime-local"
                    className="form-control"
                    name="requestMeetingDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.requestEntity.requestMeetingDate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="requestQuestionLabel" for="requestQuestion">
                    <Translate contentKey="riverApp.request.requestQuestion">Request Question</Translate>
                  </Label>
                  <AvField id="request-requestQuestion" type="text" name="requestQuestion" />
                </AvGroup>
                <AvGroup>
                  <Label id="requestPriceLabel" for="requestPrice">
                    <Translate contentKey="riverApp.request.requestPrice">Request Price</Translate>
                  </Label>
                  <AvField id="request-requestPrice" type="number" className="form-control" name="requestPrice" />
                </AvGroup>
                <AvGroup>
                  <Label id="requestCreatedDateLabel" for="requestCreatedDate">
                    <Translate contentKey="riverApp.request.requestCreatedDate">Request Created Date</Translate>
                  </Label>
                  <AvInput
                    id="request-requestCreatedDate"
                    type="datetime-local"
                    className="form-control"
                    name="requestCreatedDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.requestEntity.requestCreatedDate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="requestConsultantIdLabel" for="requestConsultantId">
                    <Translate contentKey="riverApp.request.requestConsultantId">Request Consultant Id</Translate>
                  </Label>
                  <AvField id="request-requestConsultantId" type="number" className="form-control" name="requestConsultantId" />
                </AvGroup>
                <AvGroup>
                  <Label for="user.login">
                    <Translate contentKey="riverApp.request.user">User</Translate>
                  </Label>
                  <AvInput id="request-user" type="select" className="form-control" name="userId" onChange={this.userUpdate}>
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
                    <Translate contentKey="riverApp.request.property">Property</Translate>
                  </Label>
                  <AvInput id="request-property" type="select" className="form-control" name="propertyId" onChange={this.propertyUpdate}>
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
                <AvGroup>
                  <Label for="project.projectName">
                    <Translate contentKey="riverApp.request.project">Project</Translate>
                  </Label>
                  <AvInput id="request-project" type="select" className="form-control" name="projectId" onChange={this.projectUpdate}>
                    <option value="" key="0" />
                    {projects
                      ? projects.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.projectName}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/request" replace color="info">
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
  projects: storeState.project.entities,
  requestEntity: storeState.request.entity,
  loading: storeState.request.loading,
  updating: storeState.request.updating
});

const mapDispatchToProps = {
  getUsers,
  getProperties,
  getProjects,
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
)(RequestUpdate);
