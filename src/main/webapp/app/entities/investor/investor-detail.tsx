import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './investor.reducer';
import { IInvestor } from 'app/shared/model/investor.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInvestorDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class InvestorDetail extends React.Component<IInvestorDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { investorEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="riverApp.investor.detail.title">Investor</Translate> [<b>{investorEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="investorName">
                <Translate contentKey="riverApp.investor.investorName">Investor Name</Translate>
              </span>
            </dt>
            <dd>{investorEntity.investorName}</dd>
            <dt>
              <span id="investorTitle">
                <Translate contentKey="riverApp.investor.investorTitle">Investor Title</Translate>
              </span>
            </dt>
            <dd>{investorEntity.investorTitle}</dd>
            <dt>
              <span id="investorDate">
                <Translate contentKey="riverApp.investor.investorDate">Investor Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={investorEntity.investorDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="investorDescription">
                <Translate contentKey="riverApp.investor.investorDescription">Investor Description</Translate>
              </span>
            </dt>
            <dd>{investorEntity.investorDescription}</dd>
            <dt>
              <span id="investorAddress">
                <Translate contentKey="riverApp.investor.investorAddress">Investor Address</Translate>
              </span>
            </dt>
            <dd>{investorEntity.investorAddress}</dd>
            <dt>
              <span id="investorWebsite">
                <Translate contentKey="riverApp.investor.investorWebsite">Investor Website</Translate>
              </span>
            </dt>
            <dd>{investorEntity.investorWebsite}</dd>
            <dt>
              <span id="investorPhone">
                <Translate contentKey="riverApp.investor.investorPhone">Investor Phone</Translate>
              </span>
            </dt>
            <dd>{investorEntity.investorPhone}</dd>
            <dt>
              <span id="investorPhoto">
                <Translate contentKey="riverApp.investor.investorPhoto">Investor Photo</Translate>
              </span>
            </dt>
            <dd>
              {investorEntity.investorPhoto ? (
                <div>
                  <a onClick={openFile(investorEntity.investorPhotoContentType, investorEntity.investorPhoto)}>
                    <img
                      src={`data:${investorEntity.investorPhotoContentType};base64,${investorEntity.investorPhoto}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                  <span>
                    {investorEntity.investorPhotoContentType}, {byteSize(investorEntity.investorPhoto)}
                  </span>
                </div>
              ) : null}
            </dd>
          </dl>
          <Button tag={Link} to="/entity/investor" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/investor/${investorEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ investor }: IRootState) => ({
  investorEntity: investor.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(InvestorDetail);
