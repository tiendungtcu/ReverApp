import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './company.reducer';
import { ICompany } from 'app/shared/model/company.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICompanyDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class CompanyDetail extends React.Component<ICompanyDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { companyEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="riverApp.company.detail.title">Company</Translate> [<b>{companyEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="companyName">
                <Translate contentKey="riverApp.company.companyName">Company Name</Translate>
              </span>
            </dt>
            <dd>{companyEntity.companyName}</dd>
            <dt>
              <span id="companyPhone">
                <Translate contentKey="riverApp.company.companyPhone">Company Phone</Translate>
              </span>
            </dt>
            <dd>{companyEntity.companyPhone}</dd>
            <dt>
              <span id="companyAddress">
                <Translate contentKey="riverApp.company.companyAddress">Company Address</Translate>
              </span>
            </dt>
            <dd>{companyEntity.companyAddress}</dd>
            <dt>
              <span id="companyLogo">
                <Translate contentKey="riverApp.company.companyLogo">Company Logo</Translate>
              </span>
            </dt>
            <dd>
              {companyEntity.companyLogo ? (
                <div>
                  <a onClick={openFile(companyEntity.companyLogoContentType, companyEntity.companyLogo)}>
                    <img
                      src={`data:${companyEntity.companyLogoContentType};base64,${companyEntity.companyLogo}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                  <span>
                    {companyEntity.companyLogoContentType}, {byteSize(companyEntity.companyLogo)}
                  </span>
                </div>
              ) : null}
            </dd>
            <dt>
              <span id="companyWebsite">
                <Translate contentKey="riverApp.company.companyWebsite">Company Website</Translate>
              </span>
            </dt>
            <dd>{companyEntity.companyWebsite}</dd>
            <dt>
              <span id="companyFacebook">
                <Translate contentKey="riverApp.company.companyFacebook">Company Facebook</Translate>
              </span>
            </dt>
            <dd>{companyEntity.companyFacebook}</dd>
            <dt>
              <span id="companyTwitter">
                <Translate contentKey="riverApp.company.companyTwitter">Company Twitter</Translate>
              </span>
            </dt>
            <dd>{companyEntity.companyTwitter}</dd>
            <dt>
              <span id="companyInstagram">
                <Translate contentKey="riverApp.company.companyInstagram">Company Instagram</Translate>
              </span>
            </dt>
            <dd>{companyEntity.companyInstagram}</dd>
            <dt>
              <span id="companyLinkedin">
                <Translate contentKey="riverApp.company.companyLinkedin">Company Linkedin</Translate>
              </span>
            </dt>
            <dd>{companyEntity.companyLinkedin}</dd>
            <dt>
              <span id="companyGooglePlus">
                <Translate contentKey="riverApp.company.companyGooglePlus">Company Google Plus</Translate>
              </span>
            </dt>
            <dd>{companyEntity.companyGooglePlus}</dd>
            <dt>
              <span id="companyYoutube">
                <Translate contentKey="riverApp.company.companyYoutube">Company Youtube</Translate>
              </span>
            </dt>
            <dd>{companyEntity.companyYoutube}</dd>
            <dt>
              <span id="companyDescription">
                <Translate contentKey="riverApp.company.companyDescription">Company Description</Translate>
              </span>
            </dt>
            <dd>{companyEntity.companyDescription}</dd>
          </dl>
          <Button tag={Link} to="/entity/company" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/company/${companyEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ company }: IRootState) => ({
  companyEntity: company.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CompanyDetail);
