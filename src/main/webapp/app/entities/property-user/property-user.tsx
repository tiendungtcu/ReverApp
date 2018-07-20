import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './property-user.reducer';
import { IPropertyUser } from 'app/shared/model/property-user.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPropertyUserProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class PropertyUser extends React.Component<IPropertyUserProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { propertyUserList, match } = this.props;
    return (
      <div>
        <h2 id="property-user-heading">
          <Translate contentKey="riverApp.propertyUser.home.title">Property Users</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="riverApp.propertyUser.home.createLabel">Create new Property User</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.propertyUser.liked">Liked</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.propertyUser.shared">Shared</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.propertyUser.likedDate">Liked Date</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.propertyUser.sharedDate">Shared Date</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.propertyUser.user">User</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.propertyUser.property">Property</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {propertyUserList.map((propertyUser, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${propertyUser.id}`} color="link" size="sm">
                      {propertyUser.id}
                    </Button>
                  </td>
                  <td>{propertyUser.liked ? 'true' : 'false'}</td>
                  <td>{propertyUser.shared ? 'true' : 'false'}</td>
                  <td>
                    <TextFormat type="date" value={propertyUser.likedDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={propertyUser.sharedDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{propertyUser.userLogin ? propertyUser.userLogin : ''}</td>
                  <td>
                    {propertyUser.propertyPropertyName ? (
                      <Link to={`property/${propertyUser.propertyId}`}>{propertyUser.propertyPropertyName}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${propertyUser.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${propertyUser.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${propertyUser.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ propertyUser }: IRootState) => ({
  propertyUserList: propertyUser.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PropertyUser);
