import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IProject } from 'app/shared/model/project.model';
import { getEntities as getProjects } from 'app/entities/project/project.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './investor.reducer';
import { IInvestor } from 'app/shared/model/investor.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IInvestorUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IInvestorUpdateState {
  isNew: boolean;
  projectId: number;
}

export class InvestorUpdate extends React.Component<IInvestorUpdateProps, IInvestorUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      projectId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getProjects();
  }

  onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => this.props.setBlob(name, data, contentType), isAnImage);
  };

  clearBlob = name => () => {
    this.props.setBlob(name, undefined, undefined);
  };

  saveEntity = (event, errors, values) => {
    values.investorDate = new Date(values.investorDate);

    if (errors.length === 0) {
      const { investorEntity } = this.props;
      const entity = {
        ...investorEntity,
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
    this.props.history.push('/entity/investor');
  };

  render() {
    const isInvalid = false;
    const { investorEntity, projects, loading, updating } = this.props;
    const { isNew } = this.state;

    const { investorPhoto, investorPhotoContentType } = investorEntity;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="riverApp.investor.home.createOrEditLabel">
              <Translate contentKey="riverApp.investor.home.createOrEditLabel">Create or edit a Investor</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : investorEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="investor-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="investorNameLabel" for="investorName">
                    <Translate contentKey="riverApp.investor.investorName">Investor Name</Translate>
                  </Label>
                  <AvField
                    id="investor-investorName"
                    type="text"
                    name="investorName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="investorTitleLabel" for="investorTitle">
                    <Translate contentKey="riverApp.investor.investorTitle">Investor Title</Translate>
                  </Label>
                  <AvField id="investor-investorTitle" type="text" name="investorTitle" />
                </AvGroup>
                <AvGroup>
                  <Label id="investorDateLabel" for="investorDate">
                    <Translate contentKey="riverApp.investor.investorDate">Investor Date</Translate>
                  </Label>
                  <AvInput
                    id="investor-investorDate"
                    type="datetime-local"
                    className="form-control"
                    name="investorDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.investorEntity.investorDate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="investorDescriptionLabel" for="investorDescription">
                    <Translate contentKey="riverApp.investor.investorDescription">Investor Description</Translate>
                  </Label>
                  <AvField id="investor-investorDescription" type="text" name="investorDescription" />
                </AvGroup>
                <AvGroup>
                  <Label id="investorAddressLabel" for="investorAddress">
                    <Translate contentKey="riverApp.investor.investorAddress">Investor Address</Translate>
                  </Label>
                  <AvField id="investor-investorAddress" type="text" name="investorAddress" />
                </AvGroup>
                <AvGroup>
                  <Label id="investorWebsiteLabel" for="investorWebsite">
                    <Translate contentKey="riverApp.investor.investorWebsite">Investor Website</Translate>
                  </Label>
                  <AvField id="investor-investorWebsite" type="text" name="investorWebsite" />
                </AvGroup>
                <AvGroup>
                  <Label id="investorPhoneLabel" for="investorPhone">
                    <Translate contentKey="riverApp.investor.investorPhone">Investor Phone</Translate>
                  </Label>
                  <AvField id="investor-investorPhone" type="text" name="investorPhone" />
                </AvGroup>
                <AvGroup>
                  <AvGroup>
                    <Label id="investorPhotoLabel" for="investorPhoto">
                      <Translate contentKey="riverApp.investor.investorPhoto">Investor Photo</Translate>
                    </Label>
                    <br />
                    {investorPhoto ? (
                      <div>
                        <a onClick={openFile(investorPhotoContentType, investorPhoto)}>
                          <img src={`data:${investorPhotoContentType};base64,${investorPhoto}`} style={{ maxHeight: '100px' }} />
                        </a>
                        <br />
                        <Row>
                          <Col md="11">
                            <span>
                              {investorPhotoContentType}, {byteSize(investorPhoto)}
                            </span>
                          </Col>
                          <Col md="1">
                            <Button color="danger" onClick={this.clearBlob('investorPhoto')}>
                              <FontAwesomeIcon icon="times-circle" />
                            </Button>
                          </Col>
                        </Row>
                      </div>
                    ) : null}
                    <input id="file_investorPhoto" type="file" onChange={this.onBlobChange(true, 'investorPhoto')} accept="image/*" />
                  </AvGroup>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/investor" replace color="info">
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
  projects: storeState.project.entities,
  investorEntity: storeState.investor.entity,
  loading: storeState.investor.loading,
  updating: storeState.investor.updating
});

const mapDispatchToProps = {
  getProjects,
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
)(InvestorUpdate);
