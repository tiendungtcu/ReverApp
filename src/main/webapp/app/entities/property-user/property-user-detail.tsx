import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './property-user.reducer';
import { IPropertyUser } from 'app/shared/model/property-user.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPropertyUserDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class PropertyUserDetail extends React.Component<IPropertyUserDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { propertyUserEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="riverApp.propertyUser.detail.title">PropertyUser</Translate> [<b>{propertyUserEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="liked">
                <Translate contentKey="riverApp.propertyUser.liked">Liked</Translate>
              </span>
            </dt>
            <dd>{propertyUserEntity.liked ? 'true' : 'false'}</dd>
            <dt>
              <span id="shared">
                <Translate contentKey="riverApp.propertyUser.shared">Shared</Translate>
              </span>
            </dt>
            <dd>{propertyUserEntity.shared ? 'true' : 'false'}</dd>
            <dt>
              <span id="likedDate">
                <Translate contentKey="riverApp.propertyUser.likedDate">Liked Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={propertyUserEntity.likedDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="sharedDate">
                <Translate contentKey="riverApp.propertyUser.sharedDate">Shared Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={propertyUserEntity.sharedDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <Translate contentKey="riverApp.propertyUser.user">User</Translate>
            </dt>
            <dd>{propertyUserEntity.userLogin ? propertyUserEntity.userLogin : ''}</dd>
            <dt>
              <Translate contentKey="riverApp.propertyUser.property">Property</Translate>
            </dt>
            <dd>{propertyUserEntity.propertyPropertyName ? propertyUserEntity.propertyPropertyName : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/property-user" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/property-user/${propertyUserEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ propertyUser }: IRootState) => ({
  propertyUserEntity: propertyUser.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PropertyUserDetail);
