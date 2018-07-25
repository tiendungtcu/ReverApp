import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './support-category.reducer';
import { ISupportCategory } from 'app/shared/model/support-category.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISupportCategoryProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class SupportCategory extends React.Component<ISupportCategoryProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { supportCategoryList, match } = this.props;
    return (
      <div>
        <h2 id="support-category-heading">
          <Translate contentKey="riverApp.supportCategory.home.title">Support Categories</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="riverApp.supportCategory.home.createLabel">Create new Support Category</Translate>
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
                  <Translate contentKey="riverApp.supportCategory.categoryName">Category Name</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.supportCategory.categoryDescription">Category Description</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.supportCategory.categorySupportType">Category Support Type</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {supportCategoryList.map((supportCategory, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${supportCategory.id}`} color="link" size="sm">
                      {supportCategory.id}
                    </Button>
                  </td>
                  <td>{supportCategory.categoryName}</td>
                  <td>{supportCategory.categoryDescription}</td>
                  <td>{supportCategory.categorySupportType}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${supportCategory.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${supportCategory.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${supportCategory.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ supportCategory }: IRootState) => ({
  supportCategoryList: supportCategory.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SupportCategory);
