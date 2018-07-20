import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './residential-area.reducer';
import { IResidentialArea } from 'app/shared/model/residential-area.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IResidentialAreaDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class ResidentialAreaDetail extends React.Component<IResidentialAreaDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { residentialAreaEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="riverApp.residentialArea.detail.title">ResidentialArea</Translate> [<b>{residentialAreaEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="residentialName">
                <Translate contentKey="riverApp.residentialArea.residentialName">Residential Name</Translate>
              </span>
            </dt>
            <dd>{residentialAreaEntity.residentialName}</dd>
            <dt>
              <span id="residentialAlias">
                <Translate contentKey="riverApp.residentialArea.residentialAlias">Residential Alias</Translate>
              </span>
            </dt>
            <dd>{residentialAreaEntity.residentialAlias}</dd>
            <dt>
              <span id="residentialDescription">
                <Translate contentKey="riverApp.residentialArea.residentialDescription">Residential Description</Translate>
              </span>
            </dt>
            <dd>{residentialAreaEntity.residentialDescription}</dd>
            <dt>
              <span id="residentialDetail">
                <Translate contentKey="riverApp.residentialArea.residentialDetail">Residential Detail</Translate>
              </span>
            </dt>
            <dd>{residentialAreaEntity.residentialDetail}</dd>
            <dt>
              <span id="residentialProvince">
                <Translate contentKey="riverApp.residentialArea.residentialProvince">Residential Province</Translate>
              </span>
            </dt>
            <dd>{residentialAreaEntity.residentialProvince}</dd>
            <dt>
              <span id="residentialDistrict">
                <Translate contentKey="riverApp.residentialArea.residentialDistrict">Residential District</Translate>
              </span>
            </dt>
            <dd>{residentialAreaEntity.residentialDistrict}</dd>
            <dt>
              <span id="residentialBoundary">
                <Translate contentKey="riverApp.residentialArea.residentialBoundary">Residential Boundary</Translate>
              </span>
            </dt>
            <dd>{residentialAreaEntity.residentialBoundary}</dd>
            <dt>
              <span id="residentialImage">
                <Translate contentKey="riverApp.residentialArea.residentialImage">Residential Image</Translate>
              </span>
            </dt>
            <dd>
              {residentialAreaEntity.residentialImage ? (
                <div>
                  <a onClick={openFile(residentialAreaEntity.residentialImageContentType, residentialAreaEntity.residentialImage)}>
                    <img
                      src={`data:${residentialAreaEntity.residentialImageContentType};base64,${residentialAreaEntity.residentialImage}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                  <span>
                    {residentialAreaEntity.residentialImageContentType}, {byteSize(residentialAreaEntity.residentialImage)}
                  </span>
                </div>
              ) : null}
            </dd>
            <dt>
              <Translate contentKey="riverApp.residentialArea.photo">Photo</Translate>
            </dt>
            <dd>{residentialAreaEntity.photoId ? residentialAreaEntity.photoId : ''}</dd>
            <dt>
              <Translate contentKey="riverApp.residentialArea.tag">Tag</Translate>
            </dt>
            <dd>
              {residentialAreaEntity.tags
                ? residentialAreaEntity.tags.map((val, i) => (
                    <span key={val.id}>
                      <a>{val.tagName}</a>
                      {i === residentialAreaEntity.tags.length - 1 ? '' : ', '}
                    </span>
                  ))
                : null}
            </dd>
          </dl>
          <Button tag={Link} to="/entity/residential-area" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/residential-area/${residentialAreaEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ residentialArea }: IRootState) => ({
  residentialAreaEntity: residentialArea.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ResidentialAreaDetail);
