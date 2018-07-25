import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
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
  userId: number;
  jobtitleId: number;
}

export class RecruitmentInfoUpdate extends React.Component<IRecruitmentInfoUpdateProps, IRecruitmentInfoUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      userId: 0,
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

    this.props.getUsers();
    this.props.getJobTitles();
  }

  onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => this.props.setBlob(name, data, contentType), isAnImage);
  };

  clearBlob = name => () => {
    this.props.setBlob(name, undefined, undefined);
  };

  saveEntity = (event, errors, values) => {
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

  userUpdate = element => {
    const login = element.target.value.toString();
    if (login === '') {
      this.setState({
        userId: -1
      });
    } else {
      for (const i in this.props.users) {
        if (login === this.props.users[i].login.toString()) {
          this.setState({
            userId: this.props.users[i].id
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
    const { recruitmentInfoEntity, users, jobTitles, loading, updating } = this.props;
    const { isNew } = this.state;

    const { recruitmentContent } = recruitmentInfoEntity;

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
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 128, errorMessage: translate('entity.validation.maxlength', { max: 128 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="recruitmentAvatarUrlLabel" for="recruitmentAvatarUrl">
                    <Translate contentKey="riverApp.recruitmentInfo.recruitmentAvatarUrl">Recruitment Avatar Url</Translate>
                  </Label>
                  <AvField id="recruitment-info-recruitmentAvatarUrl" type="text" name="recruitmentAvatarUrl" />
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
                  <AvField id="recruitment-info-recruitmentDate" type="date" className="form-control" name="recruitmentDate" />
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
                  <Label for="user.login">
                    <Translate contentKey="riverApp.recruitmentInfo.user">User</Translate>
                  </Label>
                  <AvInput id="recruitment-info-user" type="select" className="form-control" name="userId" onChange={this.userUpdate}>
                    <option value="" key="0" />
                    {users
                      ? users.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.login}
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
  users: storeState.userManagement.users,
  jobTitles: storeState.jobTitle.entities,
  recruitmentInfoEntity: storeState.recruitmentInfo.entity,
  loading: storeState.recruitmentInfo.loading,
  updating: storeState.recruitmentInfo.updating
});

const mapDispatchToProps = {
  getUsers,
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
