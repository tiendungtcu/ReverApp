import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './request.reducer';
import { IRequest } from 'app/shared/model/request.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IRequestDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class RequestDetail extends React.Component<IRequestDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { requestEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="riverApp.request.detail.title">Request</Translate> [<b>{requestEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="requestFirstName">
                <Translate contentKey="riverApp.request.requestFirstName">Request First Name</Translate>
              </span>
            </dt>
            <dd>{requestEntity.requestFirstName}</dd>
            <dt>
              <span id="requestLastName">
                <Translate contentKey="riverApp.request.requestLastName">Request Last Name</Translate>
              </span>
            </dt>
            <dd>{requestEntity.requestLastName}</dd>
            <dt>
              <span id="requestEmail">
                <Translate contentKey="riverApp.request.requestEmail">Request Email</Translate>
              </span>
            </dt>
            <dd>{requestEntity.requestEmail}</dd>
            <dt>
              <span id="requestPhone">
                <Translate contentKey="riverApp.request.requestPhone">Request Phone</Translate>
              </span>
            </dt>
            <dd>{requestEntity.requestPhone}</dd>
            <dt>
              <span id="requestGetAnalysis">
                <Translate contentKey="riverApp.request.requestGetAnalysis">Request Get Analysis</Translate>
              </span>
            </dt>
            <dd>{requestEntity.requestGetAnalysis ? 'true' : 'false'}</dd>
            <dt>
              <span id="requestGetPrice">
                <Translate contentKey="riverApp.request.requestGetPrice">Request Get Price</Translate>
              </span>
            </dt>
            <dd>{requestEntity.requestGetPrice ? 'true' : 'false'}</dd>
            <dt>
              <span id="requestPageUrl">
                <Translate contentKey="riverApp.request.requestPageUrl">Request Page Url</Translate>
              </span>
            </dt>
            <dd>{requestEntity.requestPageUrl}</dd>
            <dt>
              <span id="resourceId">
                <Translate contentKey="riverApp.request.resourceId">Resource Id</Translate>
              </span>
            </dt>
            <dd>{requestEntity.resourceId}</dd>
            <dt>
              <span id="resourceType">
                <Translate contentKey="riverApp.request.resourceType">Resource Type</Translate>
              </span>
            </dt>
            <dd>{requestEntity.resourceType}</dd>
            <dt>
              <span id="requestType">
                <Translate contentKey="riverApp.request.requestType">Request Type</Translate>
              </span>
            </dt>
            <dd>{requestEntity.requestType}</dd>
            <dt>
              <span id="requestMeetingDate">
                <Translate contentKey="riverApp.request.requestMeetingDate">Request Meeting Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={requestEntity.requestMeetingDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="requestQuestion">
                <Translate contentKey="riverApp.request.requestQuestion">Request Question</Translate>
              </span>
            </dt>
            <dd>{requestEntity.requestQuestion}</dd>
            <dt>
              <span id="requestPrice">
                <Translate contentKey="riverApp.request.requestPrice">Request Price</Translate>
              </span>
            </dt>
            <dd>{requestEntity.requestPrice}</dd>
            <dt>
              <Translate contentKey="riverApp.request.sender">Sender</Translate>
            </dt>
            <dd>{requestEntity.senderLogin ? requestEntity.senderLogin : ''}</dd>
            <dt>
              <Translate contentKey="riverApp.request.receiver">Receiver</Translate>
            </dt>
            <dd>{requestEntity.receiverLogin ? requestEntity.receiverLogin : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/request" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/request/${requestEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ request }: IRootState) => ({
  requestEntity: request.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(RequestDetail);
