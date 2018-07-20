import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, setBlob, reset } from './exchanger.reducer';
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

  onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => this.props.setBlob(name, data, contentType), isAnImage);
  };

  clearBlob = name => () => {
    this.props.setBlob(name, undefined, undefined);
  };

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

    const { exchangerPhoto, exchangerPhotoContentType } = exchangerEntity;

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
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="exchangerAddressLabel" for="exchangerAddress">
                    <Translate contentKey="riverApp.exchanger.exchangerAddress">Exchanger Address</Translate>
                  </Label>
                  <AvField id="exchanger-exchangerAddress" type="text" name="exchangerAddress" />
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
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <AvGroup>
                    <Label id="exchangerPhotoLabel" for="exchangerPhoto">
                      <Translate contentKey="riverApp.exchanger.exchangerPhoto">Exchanger Photo</Translate>
                    </Label>
                    <br />
                    {exchangerPhoto ? (
                      <div>
                        <a onClick={openFile(exchangerPhotoContentType, exchangerPhoto)}>
                          <img src={`data:${exchangerPhotoContentType};base64,${exchangerPhoto}`} style={{ maxHeight: '100px' }} />
                        </a>
                        <br />
                        <Row>
                          <Col md="11">
                            <span>
                              {exchangerPhotoContentType}, {byteSize(exchangerPhoto)}
                            </span>
                          </Col>
                          <Col md="1">
                            <Button color="danger" onClick={this.clearBlob('exchangerPhoto')}>
                              <FontAwesomeIcon icon="times-circle" />
                            </Button>
                          </Col>
                        </Row>
                      </div>
                    ) : null}
                    <input id="file_exchangerPhoto" type="file" onChange={this.onBlobChange(true, 'exchangerPhoto')} accept="image/*" />
                  </AvGroup>
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
  setBlob,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ExchangerUpdate);
