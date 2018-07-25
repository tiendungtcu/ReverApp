import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './support-category.reducer';
import { ISupportCategory } from 'app/shared/model/support-category.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface ISupportCategoryUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface ISupportCategoryUpdateState {
  isNew: boolean;
}

export class SupportCategoryUpdate extends React.Component<ISupportCategoryUpdateProps, ISupportCategoryUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { supportCategoryEntity } = this.props;
      const entity = {
        ...supportCategoryEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
      this.handleClose();
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/support-category');
  };

  render() {
    const isInvalid = false;
    const { supportCategoryEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="riverApp.supportCategory.home.createOrEditLabel">
              <Translate contentKey="riverApp.supportCategory.home.createOrEditLabel">Create or edit a SupportCategory</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : supportCategoryEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="support-category-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="categoryNameLabel" for="categoryName">
                    <Translate contentKey="riverApp.supportCategory.categoryName">Category Name</Translate>
                  </Label>
                  <AvField
                    id="support-category-categoryName"
                    type="text"
                    name="categoryName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 128, errorMessage: translate('entity.validation.maxlength', { max: 128 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="categoryDescriptionLabel" for="categoryDescription">
                    <Translate contentKey="riverApp.supportCategory.categoryDescription">Category Description</Translate>
                  </Label>
                  <AvField id="support-category-categoryDescription" type="text" name="categoryDescription" />
                </AvGroup>
                <AvGroup>
                  <Label id="categorySupportTypeLabel">
                    <Translate contentKey="riverApp.supportCategory.categorySupportType">Category Support Type</Translate>
                  </Label>
                  <AvInput
                    id="support-category-categorySupportType"
                    type="select"
                    className="form-control"
                    name="categorySupportType"
                    value={(!isNew && supportCategoryEntity.categorySupportType) || 'ACCOUNT'}
                  >
                    <option value="ACCOUNT">ACCOUNT</option>
                    <option value="SELL">SELL</option>
                    <option value="RENT">RENT</option>
                    <option value="BUY">BUY</option>
                    <option value="HOUSE">HOUSE</option>
                    <option value="PROJECT">PROJECT</option>
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/support-category" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={isInvalid || updating}>
                  <FontAwesomeIcon icon="save" />&nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  supportCategoryEntity: storeState.supportCategory.entity,
  loading: storeState.supportCategory.loading,
  updating: storeState.supportCategory.updating
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SupportCategoryUpdate);
