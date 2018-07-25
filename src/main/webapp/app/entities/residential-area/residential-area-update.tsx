import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITag } from 'app/shared/model/tag.model';
import { getEntities as getTags } from 'app/entities/tag/tag.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './residential-area.reducer';
import { IResidentialArea } from 'app/shared/model/residential-area.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IResidentialAreaUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IResidentialAreaUpdateState {
  isNew: boolean;
  idstag: any[];
}

export class ResidentialAreaUpdate extends React.Component<IResidentialAreaUpdateProps, IResidentialAreaUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      idstag: [],
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getTags();
  }

  onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => this.props.setBlob(name, data, contentType), isAnImage);
  };

  clearBlob = name => () => {
    this.props.setBlob(name, undefined, undefined);
  };

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { residentialAreaEntity } = this.props;
      const entity = {
        ...residentialAreaEntity,
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
    this.props.history.push('/entity/residential-area');
  };

  tagUpdate = element => {
    const selected = Array.from(element.target.selectedOptions).map((e: any) => e.value);
    this.setState({
      idstag: keysToValues(selected, this.props.tags, 'tagName')
    });
  };

  displaytag(value: any) {
    if (this.state.idstag && this.state.idstag.length !== 0) {
      const list = [];
      for (const i in this.state.idstag) {
        if (this.state.idstag[i]) {
          list.push(this.state.idstag[i].tagName);
        }
      }
      return list;
    }
    if (value.tags && value.tags.length !== 0) {
      const list = [];
      for (const i in value.tags) {
        if (value.tags[i]) {
          list.push(value.tags[i].tagName);
        }
      }
      this.setState({
        idstag: keysToValues(list, this.props.tags, 'tagName')
      });
      return list;
    }
    return null;
  }

  render() {
    const isInvalid = false;
    const { residentialAreaEntity, tags, loading, updating } = this.props;
    const { isNew } = this.state;

    const { residentialDescription, residentialDetail, residentialBoundary } = residentialAreaEntity;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="riverApp.residentialArea.home.createOrEditLabel">
              <Translate contentKey="riverApp.residentialArea.home.createOrEditLabel">Create or edit a ResidentialArea</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : residentialAreaEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="residential-area-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="residentialNameLabel" for="residentialName">
                    <Translate contentKey="riverApp.residentialArea.residentialName">Residential Name</Translate>
                  </Label>
                  <AvField
                    id="residential-area-residentialName"
                    type="text"
                    name="residentialName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 128, errorMessage: translate('entity.validation.maxlength', { max: 128 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="residentialAliasLabel" for="residentialAlias">
                    <Translate contentKey="riverApp.residentialArea.residentialAlias">Residential Alias</Translate>
                  </Label>
                  <AvField
                    id="residential-area-residentialAlias"
                    type="text"
                    name="residentialAlias"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="residentialDescriptionLabel" for="residentialDescription">
                    <Translate contentKey="riverApp.residentialArea.residentialDescription">Residential Description</Translate>
                  </Label>
                  <AvField id="residential-area-residentialDescription" type="text" name="residentialDescription" />
                </AvGroup>
                <AvGroup>
                  <Label id="residentialDetailLabel" for="residentialDetail">
                    <Translate contentKey="riverApp.residentialArea.residentialDetail">Residential Detail</Translate>
                  </Label>
                  <AvField id="residential-area-residentialDetail" type="text" name="residentialDetail" />
                </AvGroup>
                <AvGroup>
                  <Label id="residentialProvinceLabel" for="residentialProvince">
                    <Translate contentKey="riverApp.residentialArea.residentialProvince">Residential Province</Translate>
                  </Label>
                  <AvField id="residential-area-residentialProvince" type="text" name="residentialProvince" />
                </AvGroup>
                <AvGroup>
                  <Label id="residentialDistrictLabel" for="residentialDistrict">
                    <Translate contentKey="riverApp.residentialArea.residentialDistrict">Residential District</Translate>
                  </Label>
                  <AvField id="residential-area-residentialDistrict" type="text" name="residentialDistrict" />
                </AvGroup>
                <AvGroup>
                  <Label id="residentialBoundaryLabel" for="residentialBoundary">
                    <Translate contentKey="riverApp.residentialArea.residentialBoundary">Residential Boundary</Translate>
                  </Label>
                  <AvField id="residential-area-residentialBoundary" type="text" name="residentialBoundary" />
                </AvGroup>
                <AvGroup>
                  <Label id="residentialAvatarLabel" for="residentialAvatar">
                    <Translate contentKey="riverApp.residentialArea.residentialAvatar">Residential Avatar</Translate>
                  </Label>
                  <AvField id="residential-area-residentialAvatar" type="text" name="residentialAvatar" />
                </AvGroup>
                <AvGroup>
                  <Label for="tags">
                    <Translate contentKey="riverApp.residentialArea.tag">Tag</Translate>
                  </Label>
                  <AvInput
                    id="residential-area-tag"
                    type="select"
                    multiple
                    className="form-control"
                    name="faketags"
                    value={this.displaytag(residentialAreaEntity)}
                    onChange={this.tagUpdate}
                  >
                    <option value="" key="0" />
                    {tags
                      ? tags.map(otherEntity => (
                          <option value={otherEntity.tagName} key={otherEntity.id}>
                            {otherEntity.tagName}
                          </option>
                        ))
                      : null}
                  </AvInput>
                  <AvInput id="residential-area-tag" type="hidden" name="tags" value={this.state.idstag} />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/residential-area" replace color="info">
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
  tags: storeState.tag.entities,
  residentialAreaEntity: storeState.residentialArea.entity,
  loading: storeState.residentialArea.loading,
  updating: storeState.residentialArea.updating
});

const mapDispatchToProps = {
  getTags,
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
)(ResidentialAreaUpdate);
