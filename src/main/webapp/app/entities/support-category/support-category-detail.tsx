import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './support-category.reducer';
import { ISupportCategory } from 'app/shared/model/support-category.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISupportCategoryDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class SupportCategoryDetail extends React.Component<ISupportCategoryDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { supportCategoryEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="riverApp.supportCategory.detail.title">SupportCategory</Translate> [<b>{supportCategoryEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="categoryName">
                <Translate contentKey="riverApp.supportCategory.categoryName">Category Name</Translate>
              </span>
            </dt>
            <dd>{supportCategoryEntity.categoryName}</dd>
            <dt>
              <span id="categoryDescription">
                <Translate contentKey="riverApp.supportCategory.categoryDescription">Category Description</Translate>
              </span>
            </dt>
            <dd>{supportCategoryEntity.categoryDescription}</dd>
            <dt>
              <span id="categorySupportType">
                <Translate contentKey="riverApp.supportCategory.categorySupportType">Category Support Type</Translate>
              </span>
            </dt>
            <dd>{supportCategoryEntity.categorySupportType}</dd>
          </dl>
          <Button tag={Link} to="/entity/support-category" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/support-category/${supportCategoryEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ supportCategory }: IRootState) => ({
  supportCategoryEntity: supportCategory.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SupportCategoryDetail);
