import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IPhoto } from 'app/shared/model/photo.model';
import { getEntities as getPhotos } from 'app/entities/photo/photo.reducer';
import { IJobTitle } from 'app/shared/model/job-title.model';
import { getEntities as getJobTitles } from 'app/entities/job-title/job-title.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './recruitment-info.reducer';
import { IRecruitmentInfo } from 'app/shared/model/recruitment-info.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IRecruitmentInfoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IRecruitmentInfoUpdateState {
  isNew: boolean;
  photoId: number;
  jobtitleId: number;
}

export class RecruitmentInfoUpdate extends React.Component<IRecruitmentInfoUpdateProps, IRecruitmentInfoUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      photoId: 0,
      jobtitleId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getPhotos();
    this.props.getJobTitles();
  }

  onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => this.props.setBlob(name, data, contentType), isAnImage);
  };

  clearBlob = name => () => {
    this.props.setBlob(name, undefined, undefined);
  };

  saveEntity = (event, errors, values) => {
    values.recruitmentDate = new Date(values.recruitmentDate);

    if (errors.length === 0) {
      const { recruitmentInfoEntity } = this.props;
      const entity = {
        ...recruitmentInfoEntity,
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
    this.props.history.push('/entity/recruitment-info');
  };

  photoUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        photoId: -1
      });
    } else {
      for (const i in this.props.photos) {
        if (id === this.props.photos[i].id.toString()) {
          this.setState({
            photoId: this.props.photos[i].id
          });
        }
      }
    }
  };

  jobtitleUpdate = element => {
    const titleName = element.target.value.toString();
    if (titleName === '') {
      this.setState({
        jobtitleId: -1
      });
    } else {
      for (const i in this.props.jobTitles) {
        if (titleName === this.props.jobTitles[i].titleName.toString()) {
          this.setState({
            jobtitleId: this.props.jobTitles[i].id
          });
        }
      }
    }
  };

  render() {
    const isInvalid = false;
    const { recruitmentInfoEntity, photos, jobTitles, loading, updating } = this.props;
    const { isNew } = this.state;

    const { recruitmentImage, recruitmentImageContentType, recruitmentContent } = recruitmentInfoEntity;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="riverApp.recruitmentInfo.home.createOrEditLabel">
              <Translate contentKey="riverApp.recruitmentInfo.home.createOrEditLabel">Create or edit a RecruitmentInfo</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : recruitmentInfoEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="recruitment-info-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="recruitmentTitleLabel" for="recruitmentTitle">
                    <Translate contentKey="riverApp.recruitmentInfo.recruitmentTitle">Recruitment Title</Translate>
                  </Label>
                  <AvField
                    id="recruitment-info-recruitmentTitle"
                    type="text"
                    name="recruitmentTitle"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <AvGroup>
                    <Label id="recruitmentImageLabel" for="recruitmentImage">
                      <Translate contentKey="riverApp.recruitmentInfo.recruitmentImage">Recruitment Image</Translate>
                    </Label>
                    <br />
                    {recruitmentImage ? (
                      <div>
                        <a onClick={openFile(recruitmentImageContentType, recruitmentImage)}>
                          <img src={`data:${recruitmentImageContentType};base64,${recruitmentImage}`} style={{ maxHeight: '100px' }} />
                        </a>
                        <br />
                        <Row>
                          <Col md="11">
                            <span>
                              {recruitmentImageContentType}, {byteSize(recruitmentImage)}
                            </span>
                          </Col>
                          <Col md="1">
                            <Button color="danger" onClick={this.clearBlob('recruitmentImage')}>
                              <FontAwesomeIcon icon="times-circle" />
                            </Button>
                          </Col>
                        </Row>
                      </div>
                    ) : null}
                    <input id="file_recruitmentImage" type="file" onChange={this.onBlobChange(true, 'recruitmentImage')} accept="image/*" />
                  </AvGroup>
                </AvGroup>
                <AvGroup>
                  <Label id="recruitmentContentLabel" for="recruitmentContent">
                    <Translate contentKey="riverApp.recruitmentInfo.recruitmentContent">Recruitment Content</Translate>
                  </Label>
                  <AvField id="recruitment-info-recruitmentContent" type="text" name="recruitmentContent" />
                </AvGroup>
                <AvGroup>
                  <Label id="recruitmentNotesLabel" for="recruitmentNotes">
                    <Translate contentKey="riverApp.recruitmentInfo.recruitmentNotes">Recruitment Notes</Translate>
                  </Label>
                  <AvField id="recruitment-info-recruitmentNotes" type="text" name="recruitmentNotes" />
                </AvGroup>
                <AvGroup>
                  <Label id="recruitmentDateLabel" for="recruitmentDate">
                    <Translate contentKey="riverApp.recruitmentInfo.recruitmentDate">Recruitment Date</Translate>
                  </Label>
                  <AvInput
                    id="recruitment-info-recruitmentDate"
                    type="datetime-local"
                    className="form-control"
                    name="recruitmentDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.recruitmentInfoEntity.recruitmentDate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="recruitmentSeenCountLabel" for="recruitmentSeenCount">
                    <Translate contentKey="riverApp.recruitmentInfo.recruitmentSeenCount">Recruitment Seen Count</Translate>
                  </Label>
                  <AvField id="recruitment-info-recruitmentSeenCount" type="number" className="form-control" name="recruitmentSeenCount" />
                </AvGroup>
                <AvGroup>
                  <Label id="recruitmentStatusLabel" check>
                    <AvInput id="recruitment-info-recruitmentStatus" type="checkbox" className="form-control" name="recruitmentStatus" />
                    <Translate contentKey="riverApp.recruitmentInfo.recruitmentStatus">Recruitment Status</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label for="photo.id">
                    <Translate contentKey="riverApp.recruitmentInfo.photo">Photo</Translate>
                  </Label>
                  <AvInput id="recruitment-info-photo" type="select" className="form-control" name="photoId" onChange={this.photoUpdate}>
                    <option value="" key="0" />
                    {photos
                      ? photos.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="jobtitle.titleName">
                    <Translate contentKey="riverApp.recruitmentInfo.jobtitle">Jobtitle</Translate>
                  </Label>
                  <AvInput
                    id="recruitment-info-jobtitle"
                    type="select"
                    className="form-control"
                    name="jobtitleId"
                    onChange={this.jobtitleUpdate}
                  >
                    <option value="" key="0" />
                    {jobTitles
                      ? jobTitles.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.titleName}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/recruitment-info" replace color="info">
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
  photos: storeState.photo.entities,
  jobTitles: storeState.jobTitle.entities,
  recruitmentInfoEntity: storeState.recruitmentInfo.entity,
  loading: storeState.recruitmentInfo.loading,
  updating: storeState.recruitmentInfo.updating
});

const mapDispatchToProps = {
  getPhotos,
  getJobTitles,
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
)(RecruitmentInfoUpdate);
