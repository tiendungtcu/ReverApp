import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './notification.reducer';
import { INotification } from 'app/shared/model/notification.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INotificationDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class NotificationDetail extends React.Component<INotificationDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { notificationEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="riverApp.notification.detail.title">Notification</Translate> [<b>{notificationEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="notificationTitle">
                <Translate contentKey="riverApp.notification.notificationTitle">Notification Title</Translate>
              </span>
            </dt>
            <dd>{notificationEntity.notificationTitle}</dd>
            <dt>
              <span id="notificationContent">
                <Translate contentKey="riverApp.notification.notificationContent">Notification Content</Translate>
              </span>
            </dt>
            <dd>{notificationEntity.notificationContent}</dd>
            <dt>
              <span id="notificationSeen">
                <Translate contentKey="riverApp.notification.notificationSeen">Notification Seen</Translate>
              </span>
            </dt>
            <dd>{notificationEntity.notificationSeen ? 'true' : 'false'}</dd>
            <dt>
              <span id="notificationDate">
                <Translate contentKey="riverApp.notification.notificationDate">Notification Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={notificationEntity.notificationDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="notificationType">
                <Translate contentKey="riverApp.notification.notificationType">Notification Type</Translate>
              </span>
            </dt>
            <dd>{notificationEntity.notificationType}</dd>
            <dt>
              <span id="notificationReference">
                <Translate contentKey="riverApp.notification.notificationReference">Notification Reference</Translate>
              </span>
            </dt>
            <dd>{notificationEntity.notificationReference}</dd>
            <dt>
              <Translate contentKey="riverApp.notification.user">User</Translate>
            </dt>
            <dd>{notificationEntity.userLogin ? notificationEntity.userLogin : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/notification" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/notification/${notificationEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ notification }: IRootState) => ({
  notificationEntity: notification.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(NotificationDetail);
