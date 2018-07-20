import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, setBlob, reset } from './company.reducer';
import { ICompany } from 'app/shared/model/company.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface ICompanyUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface ICompanyUpdateState {
  isNew: boolean;
}

export class CompanyUpdate extends React.Component<ICompanyUpdateProps, ICompanyUpdateState> {
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
      const { companyEntity } = this.props;
      const entity = {
        ...companyEntity,
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
    this.props.history.push('/entity/company');
  };

  render() {
    const isInvalid = false;
    const { companyEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    const { companyLogo, companyLogoContentType, companyDescription } = companyEntity;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="riverApp.company.home.createOrEditLabel">
              <Translate contentKey="riverApp.company.home.createOrEditLabel">Create or edit a Company</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : companyEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="company-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="companyNameLabel" for="companyName">
                    <Translate contentKey="riverApp.company.companyName">Company Name</Translate>
                  </Label>
                  <AvField
                    id="company-companyName"
                    type="text"
                    name="companyName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="companyPhoneLabel" for="companyPhone">
                    <Translate contentKey="riverApp.company.companyPhone">Company Phone</Translate>
                  </Label>
                  <AvField id="company-companyPhone" type="text" name="companyPhone" />
                </AvGroup>
                <AvGroup>
                  <Label id="companyAddressLabel" for="companyAddress">
                    <Translate contentKey="riverApp.company.companyAddress">Company Address</Translate>
                  </Label>
                  <AvField id="company-companyAddress" type="text" name="companyAddress" />
                </AvGroup>
                <AvGroup>
                  <AvGroup>
                    <Label id="companyLogoLabel" for="companyLogo">
                      <Translate contentKey="riverApp.company.companyLogo">Company Logo</Translate>
                    </Label>
                    <br />
                    {companyLogo ? (
                      <div>
                        <a onClick={openFile(companyLogoContentType, companyLogo)}>
                          <img src={`data:${companyLogoContentType};base64,${companyLogo}`} style={{ maxHeight: '100px' }} />
                        </a>
                        <br />
                        <Row>
                          <Col md="11">
                            <span>
                              {companyLogoContentType}, {byteSize(companyLogo)}
                            </span>
                          </Col>
                          <Col md="1">
                            <Button color="danger" onClick={this.clearBlob('companyLogo')}>
                              <FontAwesomeIcon icon="times-circle" />
                            </Button>
                          </Col>
                        </Row>
                      </div>
                    ) : null}
                    <input id="file_companyLogo" type="file" onChange={this.onBlobChange(true, 'companyLogo')} accept="image/*" />
                  </AvGroup>
                </AvGroup>
                <AvGroup>
                  <Label id="companyWebsiteLabel" for="companyWebsite">
                    <Translate contentKey="riverApp.company.companyWebsite">Company Website</Translate>
                  </Label>
                  <AvField id="company-companyWebsite" type="text" name="companyWebsite" />
                </AvGroup>
                <AvGroup>
                  <Label id="companyFacebookLabel" for="companyFacebook">
                    <Translate contentKey="riverApp.company.companyFacebook">Company Facebook</Translate>
                  </Label>
                  <AvField id="company-companyFacebook" type="text" name="companyFacebook" />
                </AvGroup>
                <AvGroup>
                  <Label id="companyTwitterLabel" for="companyTwitter">
                    <Translate contentKey="riverApp.company.companyTwitter">Company Twitter</Translate>
                  </Label>
                  <AvField id="company-companyTwitter" type="text" name="companyTwitter" />
                </AvGroup>
                <AvGroup>
                  <Label id="companyInstagramLabel" for="companyInstagram">
                    <Translate contentKey="riverApp.company.companyInstagram">Company Instagram</Translate>
                  </Label>
                  <AvField id="company-companyInstagram" type="text" name="companyInstagram" />
                </AvGroup>
                <AvGroup>
                  <Label id="companyLinkedinLabel" for="companyLinkedin">
                    <Translate contentKey="riverApp.company.companyLinkedin">Company Linkedin</Translate>
                  </Label>
                  <AvField id="company-companyLinkedin" type="text" name="companyLinkedin" />
                </AvGroup>
                <AvGroup>
                  <Label id="companyGooglePlusLabel" for="companyGooglePlus">
                    <Translate contentKey="riverApp.company.companyGooglePlus">Company Google Plus</Translate>
                  </Label>
                  <AvField id="company-companyGooglePlus" type="text" name="companyGooglePlus" />
                </AvGroup>
                <AvGroup>
                  <Label id="companyYoutubeLabel" for="companyYoutube">
                    <Translate contentKey="riverApp.company.companyYoutube">Company Youtube</Translate>
                  </Label>
                  <AvField id="company-companyYoutube" type="text" name="companyYoutube" />
                </AvGroup>
                <AvGroup>
                  <Label id="companyDescriptionLabel" for="companyDescription">
                    <Translate contentKey="riverApp.company.companyDescription">Company Description</Translate>
                  </Label>
                  <AvField id="company-companyDescription" type="text" name="companyDescription" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/company" replace color="info">
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
  companyEntity: storeState.company.entity,
  loading: storeState.company.loading,
  updating: storeState.company.updating
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
)(CompanyUpdate);
