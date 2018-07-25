import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './exchanger.reducer';
import { IExchanger } from 'app/shared/model/exchanger.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IExchangerDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class ExchangerDetail extends React.Component<IExchangerDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { exchangerEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="riverApp.exchanger.detail.title">Exchanger</Translate> [<b>{exchangerEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="exchangerName">
                <Translate contentKey="riverApp.exchanger.exchangerName">Exchanger Name</Translate>
              </span>
            </dt>
            <dd>{exchangerEntity.exchangerName}</dd>
            <dt>
              <span id="exchangerAddress">
                <Translate contentKey="riverApp.exchanger.exchangerAddress">Exchanger Address</Translate>
              </span>
            </dt>
            <dd>{exchangerEntity.exchangerAddress}</dd>
            <dt>
              <span id="exchangerPhone">
                <Translate contentKey="riverApp.exchanger.exchangerPhone">Exchanger Phone</Translate>
              </span>
            </dt>
            <dd>{exchangerEntity.exchangerPhone}</dd>
            <dt>
              <span id="exchangerAvatarUrl">
                <Translate contentKey="riverApp.exchanger.exchangerAvatarUrl">Exchanger Avatar Url</Translate>
              </span>
            </dt>
            <dd>{exchangerEntity.exchangerAvatarUrl}</dd>
          </dl>
          <Button tag={Link} to="/entity/exchanger" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/exchanger/${exchangerEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ exchanger }: IRootState) => ({
  exchangerEntity: exchanger.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ExchangerDetail);
