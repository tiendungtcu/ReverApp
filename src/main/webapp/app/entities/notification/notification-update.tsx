import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, setBlob, reset } from './notification.reducer';
import { INotification } from 'app/shared/model/notification.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface INotificationUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface INotificationUpdateState {
  isNew: boolean;
}

export class NotificationUpdate extends React.Component<INotificationUpdateProps, INotificationUpdateState> {
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

  onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => this.props.setBlob(name, data, contentType), isAnImage);
  };

  clearBlob = name => () => {
    this.props.setBlob(name, undefined, undefined);
  };

  saveEntity = (event, errors, values) => {
    values.notificationDate = new Date(values.notificationDate);

    if (errors.length === 0) {
      const { notificationEntity } = this.props;
      const entity = {
        ...notificationEntity,
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
    this.props.history.push('/entity/notification');
  };

  render() {
    const isInvalid = false;
    const { notificationEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    const { notificationContent } = notificationEntity;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="riverApp.notification.home.createOrEditLabel">
              <Translate contentKey="riverApp.notification.home.createOrEditLabel">Create or edit a Notification</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : notificationEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="notification-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="notificationTitleLabel" for="notificationTitle">
                    <Translate contentKey="riverApp.notification.notificationTitle">Notification Title</Translate>
                  </Label>
                  <AvField
                    id="notification-notificationTitle"
                    type="text"
                    name="notificationTitle"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 128, errorMessage: translate('entity.validation.maxlength', { max: 128 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="notificationContentLabel" for="notificationContent">
                    <Translate contentKey="riverApp.notification.notificationContent">Notification Content</Translate>
                  </Label>
                  <AvField id="notification-notificationContent" type="text" name="notificationContent" />
                </AvGroup>
                <AvGroup>
                  <Label id="notificationSeenLabel" check>
                    <AvInput id="notification-notificationSeen" type="checkbox" className="form-control" name="notificationSeen" />
                    <Translate contentKey="riverApp.notification.notificationSeen">Notification Seen</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="notificationDateLabel" for="notificationDate">
                    <Translate contentKey="riverApp.notification.notificationDate">Notification Date</Translate>
                  </Label>
                  <AvInput
                    id="notification-notificationDate"
                    type="datetime-local"
                    className="form-control"
                    name="notificationDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.notificationEntity.notificationDate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="notificationTypeLabel">
                    <Translate contentKey="riverApp.notification.notificationType">Notification Type</Translate>
                  </Label>
                  <AvInput
                    id="notification-notificationType"
                    type="select"
                    className="form-control"
                    name="notificationType"
                    value={(!isNew && notificationEntity.notificationType) || 'SYSTEM'}
                  >
                    <option value="SYSTEM">SYSTEM</option>
                    <option value="REQUEST">REQUEST</option>
                    <option value="ALERT">ALERT</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="notificationSenderLabel" for="notificationSender">
                    <Translate contentKey="riverApp.notification.notificationSender">Notification Sender</Translate>
                  </Label>
                  <AvField id="notification-notificationSender" type="number" className="form-control" name="notificationSender" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/notification" replace color="info">
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
  notificationEntity: storeState.notification.entity,
  loading: storeState.notification.loading,
  updating: storeState.notification.updating
});

const mapDispatchToProps = {
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
)(NotificationUpdate);
