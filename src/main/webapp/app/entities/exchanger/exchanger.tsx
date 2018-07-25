import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './exchanger.reducer';
import { IExchanger } from 'app/shared/model/exchanger.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IExchangerProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Exchanger extends React.Component<IExchangerProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { exchangerList, match } = this.props;
    return (
      <div>
        <h2 id="exchanger-heading">
          <Translate contentKey="riverApp.exchanger.home.title">Exchangers</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="riverApp.exchanger.home.createLabel">Create new Exchanger</Translate>
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
                  <Translate contentKey="riverApp.exchanger.exchangerName">Exchanger Name</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.exchanger.exchangerAddress">Exchanger Address</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.exchanger.exchangerPhone">Exchanger Phone</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.exchanger.exchangerAvatarUrl">Exchanger Avatar Url</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {exchangerList.map((exchanger, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${exchanger.id}`} color="link" size="sm">
                      {exchanger.id}
                    </Button>
                  </td>
                  <td>{exchanger.exchangerName}</td>
                  <td>{exchanger.exchangerAddress}</td>
                  <td>{exchanger.exchangerPhone}</td>
                  <td>{exchanger.exchangerAvatarUrl}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${exchanger.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${exchanger.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${exchanger.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ exchanger }: IRootState) => ({
  exchangerList: exchanger.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Exchanger);
