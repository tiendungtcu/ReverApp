import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './exchanger.reducer';
import { IExchanger } from 'app/shared/model/exchanger.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IExchangerUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IExchangerUpdateState {
  isNew: boolean;
}

export class ExchangerUpdate extends React.Component<IExchangerUpdateProps, IExchangerUpdateState> {
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
      const { exchangerEntity } = this.props;
      const entity = {
        ...exchangerEntity,
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
    this.props.history.push('/entity/exchanger');
  };

  render() {
    const isInvalid = false;
    const { exchangerEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="riverApp.exchanger.home.createOrEditLabel">
              <Translate contentKey="riverApp.exchanger.home.createOrEditLabel">Create or edit a Exchanger</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : exchangerEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="exchanger-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="exchangerNameLabel" for="exchangerName">
                    <Translate contentKey="riverApp.exchanger.exchangerName">Exchanger Name</Translate>
                  </Label>
                  <AvField
                    id="exchanger-exchangerName"
                    type="text"
                    name="exchangerName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 128, errorMessage: translate('entity.validation.maxlength', { max: 128 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="exchangerAddressLabel" for="exchangerAddress">
                    <Translate contentKey="riverApp.exchanger.exchangerAddress">Exchanger Address</Translate>
                  </Label>
                  <AvField
                    id="exchanger-exchangerAddress"
                    type="text"
                    name="exchangerAddress"
                    validate={{
                      maxLength: { value: 256, errorMessage: translate('entity.validation.maxlength', { max: 256 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="exchangerPhoneLabel" for="exchangerPhone">
                    <Translate contentKey="riverApp.exchanger.exchangerPhone">Exchanger Phone</Translate>
                  </Label>
                  <AvField
                    id="exchanger-exchangerPhone"
                    type="text"
                    name="exchangerPhone"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 16, errorMessage: translate('entity.validation.maxlength', { max: 16 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="exchangerAvatarUrlLabel" for="exchangerAvatarUrl">
                    <Translate contentKey="riverApp.exchanger.exchangerAvatarUrl">Exchanger Avatar Url</Translate>
                  </Label>
                  <AvField id="exchanger-exchangerAvatarUrl" type="text" name="exchangerAvatarUrl" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/exchanger" replace color="info">
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
  exchangerEntity: storeState.exchanger.entity,
  loading: storeState.exchanger.loading,
  updating: storeState.exchanger.updating
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
)(ExchangerUpdate);
