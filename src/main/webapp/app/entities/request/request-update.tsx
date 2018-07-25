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
import { getEntity, updateEntity, createEntity, reset } from './request.reducer';
import { IRequest } from 'app/shared/model/request.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IRequestUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IRequestUpdateState {
  isNew: boolean;
  senderId: number;
  receiverId: number;
}

export class RequestUpdate extends React.Component<IRequestUpdateProps, IRequestUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      senderId: 0,
      receiverId: 0,
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
  }

  saveEntity = (event, errors, values) => {
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

  senderUpdate = element => {
    const login = element.target.value.toString();
    if (login === '') {
      this.setState({
        senderId: -1
      });
    } else {
      for (const i in this.props.users) {
        if (login === this.props.users[i].login.toString()) {
          this.setState({
            senderId: this.props.users[i].id
          });
        }
      }
    }
  };

  receiverUpdate = element => {
    const login = element.target.value.toString();
    if (login === '') {
      this.setState({
        receiverId: -1
      });
    } else {
      for (const i in this.props.users) {
        if (login === this.props.users[i].login.toString()) {
          this.setState({
            receiverId: this.props.users[i].id
          });
        }
      }
    }
  };

  render() {
    const isInvalid = false;
    const { requestEntity, users, loading, updating } = this.props;
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
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 128, errorMessage: translate('entity.validation.maxlength', { max: 128 }) }
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
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 128, errorMessage: translate('entity.validation.maxlength', { max: 128 }) }
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
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 16, errorMessage: translate('entity.validation.maxlength', { max: 16 }) }
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
                  <Label id="resourceIdLabel" for="resourceId">
                    <Translate contentKey="riverApp.request.resourceId">Resource Id</Translate>
                  </Label>
                  <AvField id="request-resourceId" type="number" className="form-control" name="resourceId" />
                </AvGroup>
                <AvGroup>
                  <Label id="resourceTypeLabel">
                    <Translate contentKey="riverApp.request.resourceType">Resource Type</Translate>
                  </Label>
                  <AvInput
                    id="request-resourceType"
                    type="select"
                    className="form-control"
                    name="resourceType"
                    value={(!isNew && requestEntity.resourceType) || 'PROJECT'}
                  >
                    <option value="PROJECT">PROJECT</option>
                    <option value="PROPERTY">PROPERTY</option>
                    <option value="EMPLOYEE">EMPLOYEE</option>
                    <option value="RESIDENTIAL_AREA">RESIDENTIAL_AREA</option>
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
                    <option value="OTHER">OTHER</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="requestMeetingDateLabel" for="requestMeetingDate">
                    <Translate contentKey="riverApp.request.requestMeetingDate">Request Meeting Date</Translate>
                  </Label>
                  <AvField id="request-requestMeetingDate" type="date" className="form-control" name="requestMeetingDate" />
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
                  <AvField
                    id="request-requestPrice"
                    type="number"
                    className="form-control"
                    name="requestPrice"
                    validate={{
                      min: { value: 0, errorMessage: translate('entity.validation.min', { min: 0 }) },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="sender.login">
                    <Translate contentKey="riverApp.request.sender">Sender</Translate>
                  </Label>
                  <AvInput id="request-sender" type="select" className="form-control" name="senderId" onChange={this.senderUpdate}>
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
                  <Label for="receiver.login">
                    <Translate contentKey="riverApp.request.receiver">Receiver</Translate>
                  </Label>
                  <AvInput id="request-receiver" type="select" className="form-control" name="receiverId" onChange={this.receiverUpdate}>
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
  requestEntity: storeState.request.entity,
  loading: storeState.request.loading,
  updating: storeState.request.updating
});

const mapDispatchToProps = {
  getUsers,
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
