import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IContact } from 'app/shared/model/contact.model';
import { getEntities as getContacts } from 'app/entities/contact/contact.reducer';
import { IPhoto } from 'app/shared/model/photo.model';
import { getEntities as getPhotos } from 'app/entities/photo/photo.reducer';
import { IJobTitle } from 'app/shared/model/job-title.model';
import { getEntities as getJobTitles } from 'app/entities/job-title/job-title.reducer';
import { IDepartment } from 'app/shared/model/department.model';
import { getEntities as getDepartments } from 'app/entities/department/department.reducer';
import { getEntities as getEmployees } from 'app/entities/employee/employee.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './employee.reducer';
import { IEmployee } from 'app/shared/model/employee.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IEmployeeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IEmployeeUpdateState {
  isNew: boolean;
  contactId: number;
  photoId: number;
  jobtitleId: number;
  departmentId: number;
  managerId: number;
}

export class EmployeeUpdate extends React.Component<IEmployeeUpdateProps, IEmployeeUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      contactId: 0,
      photoId: 0,
      jobtitleId: 0,
      departmentId: 0,
      managerId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getContacts();
    this.props.getPhotos();
    this.props.getJobTitles();
    this.props.getDepartments();
    this.props.getEmployees();
  }

  onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => this.props.setBlob(name, data, contentType), isAnImage);
  };

  clearBlob = name => () => {
    this.props.setBlob(name, undefined, undefined);
  };

  saveEntity = (event, errors, values) => {
    values.employeeDob = new Date(values.employeeDob);

    if (errors.length === 0) {
      const { employeeEntity } = this.props;
      const entity = {
        ...employeeEntity,
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
    this.props.history.push('/entity/employee');
  };

  contactUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        contactId: -1
      });
    } else {
      for (const i in this.props.contacts) {
        if (id === this.props.contacts[i].id.toString()) {
          this.setState({
            contactId: this.props.contacts[i].id
          });
        }
      }
    }
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

  departmentUpdate = element => {
    const departmentName = element.target.value.toString();
    if (departmentName === '') {
      this.setState({
        departmentId: -1
      });
    } else {
      for (const i in this.props.departments) {
        if (departmentName === this.props.departments[i].departmentName.toString()) {
          this.setState({
            departmentId: this.props.departments[i].id
          });
        }
      }
    }
  };

  managerUpdate = element => {
    const employeeName = element.target.value.toString();
    if (employeeName === '') {
      this.setState({
        managerId: -1
      });
    } else {
      for (const i in this.props.employees) {
        if (employeeName === this.props.employees[i].employeeName.toString()) {
          this.setState({
            managerId: this.props.employees[i].id
          });
        }
      }
    }
  };

  render() {
    const isInvalid = false;
    const { employeeEntity, contacts, photos, jobTitles, departments, employees, loading, updating } = this.props;
    const { isNew } = this.state;

    const { employeeAvatar, employeeAvatarContentType } = employeeEntity;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="riverApp.employee.home.createOrEditLabel">
              <Translate contentKey="riverApp.employee.home.createOrEditLabel">Create or edit a Employee</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : employeeEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="employee-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="employeeNameLabel" for="employeeName">
                    <Translate contentKey="riverApp.employee.employeeName">Employee Name</Translate>
                  </Label>
                  <AvField
                    id="employee-employeeName"
                    type="text"
                    name="employeeName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="employeeFirstNameLabel" for="employeeFirstName">
                    <Translate contentKey="riverApp.employee.employeeFirstName">Employee First Name</Translate>
                  </Label>
                  <AvField id="employee-employeeFirstName" type="text" name="employeeFirstName" />
                </AvGroup>
                <AvGroup>
                  <Label id="employeeLastNameLabel" for="employeeLastName">
                    <Translate contentKey="riverApp.employee.employeeLastName">Employee Last Name</Translate>
                  </Label>
                  <AvField id="employee-employeeLastName" type="text" name="employeeLastName" />
                </AvGroup>
                <AvGroup>
                  <Label id="employeeDobLabel" for="employeeDob">
                    <Translate contentKey="riverApp.employee.employeeDob">Employee Dob</Translate>
                  </Label>
                  <AvInput
                    id="employee-employeeDob"
                    type="datetime-local"
                    className="form-control"
                    name="employeeDob"
                    value={isNew ? null : convertDateTimeFromServer(this.props.employeeEntity.employeeDob)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="employeeSexLabel">
                    <Translate contentKey="riverApp.employee.employeeSex">Employee Sex</Translate>
                  </Label>
                  <AvInput
                    id="employee-employeeSex"
                    type="select"
                    className="form-control"
                    name="employeeSex"
                    value={(!isNew && employeeEntity.employeeSex) || 'MALE'}
                  >
                    <option value="MALE">MALE</option>
                    <option value="FEMALE">FEMALE</option>
                    <option value="OTHER">OTHER</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="employeeIdentityCardLabel" for="employeeIdentityCard">
                    <Translate contentKey="riverApp.employee.employeeIdentityCard">Employee Identity Card</Translate>
                  </Label>
                  <AvField id="employee-employeeIdentityCard" type="text" name="employeeIdentityCard" />
                </AvGroup>
                <AvGroup>
                  <Label id="employeePhoneLabel" for="employeePhone">
                    <Translate contentKey="riverApp.employee.employeePhone">Employee Phone</Translate>
                  </Label>
                  <AvField
                    id="employee-employeePhone"
                    type="text"
                    name="employeePhone"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="employeeEmailLabel" for="employeeEmail">
                    <Translate contentKey="riverApp.employee.employeeEmail">Employee Email</Translate>
                  </Label>
                  <AvField
                    id="employee-employeeEmail"
                    type="text"
                    name="employeeEmail"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <AvGroup>
                    <Label id="employeeAvatarLabel" for="employeeAvatar">
                      <Translate contentKey="riverApp.employee.employeeAvatar">Employee Avatar</Translate>
                    </Label>
                    <br />
                    {employeeAvatar ? (
                      <div>
                        <a onClick={openFile(employeeAvatarContentType, employeeAvatar)}>
                          <img src={`data:${employeeAvatarContentType};base64,${employeeAvatar}`} style={{ maxHeight: '100px' }} />
                        </a>
                        <br />
                        <Row>
                          <Col md="11">
                            <span>
                              {employeeAvatarContentType}, {byteSize(employeeAvatar)}
                            </span>
                          </Col>
                          <Col md="1">
                            <Button color="danger" onClick={this.clearBlob('employeeAvatar')}>
                              <FontAwesomeIcon icon="times-circle" />
                            </Button>
                          </Col>
                        </Row>
                      </div>
                    ) : null}
                    <input id="file_employeeAvatar" type="file" onChange={this.onBlobChange(true, 'employeeAvatar')} accept="image/*" />
                  </AvGroup>
                </AvGroup>
                <AvGroup>
                  <Label id="employeeFacebookLabel" for="employeeFacebook">
                    <Translate contentKey="riverApp.employee.employeeFacebook">Employee Facebook</Translate>
                  </Label>
                  <AvField id="employee-employeeFacebook" type="text" name="employeeFacebook" />
                </AvGroup>
                <AvGroup>
                  <Label id="employeeLinkedinLabel" for="employeeLinkedin">
                    <Translate contentKey="riverApp.employee.employeeLinkedin">Employee Linkedin</Translate>
                  </Label>
                  <AvField id="employee-employeeLinkedin" type="text" name="employeeLinkedin" />
                </AvGroup>
                <AvGroup>
                  <Label id="employeeInstagramLabel" for="employeeInstagram">
                    <Translate contentKey="riverApp.employee.employeeInstagram">Employee Instagram</Translate>
                  </Label>
                  <AvField id="employee-employeeInstagram" type="text" name="employeeInstagram" />
                </AvGroup>
                <AvGroup>
                  <Label id="employeeGooglePlusLabel" for="employeeGooglePlus">
                    <Translate contentKey="riverApp.employee.employeeGooglePlus">Employee Google Plus</Translate>
                  </Label>
                  <AvField id="employee-employeeGooglePlus" type="text" name="employeeGooglePlus" />
                </AvGroup>
                <AvGroup>
                  <Label id="employeeZaloLabel" for="employeeZalo">
                    <Translate contentKey="riverApp.employee.employeeZalo">Employee Zalo</Translate>
                  </Label>
                  <AvField id="employee-employeeZalo" type="text" name="employeeZalo" />
                </AvGroup>
                <AvGroup>
                  <Label id="employeeTwitterLabel" for="employeeTwitter">
                    <Translate contentKey="riverApp.employee.employeeTwitter">Employee Twitter</Translate>
                  </Label>
                  <AvField id="employee-employeeTwitter" type="text" name="employeeTwitter" />
                </AvGroup>
                <AvGroup>
                  <Label id="employeeYoutubeLabel" for="employeeYoutube">
                    <Translate contentKey="riverApp.employee.employeeYoutube">Employee Youtube</Translate>
                  </Label>
                  <AvField id="employee-employeeYoutube" type="text" name="employeeYoutube" />
                </AvGroup>
                <AvGroup>
                  <Label for="contact.id">
                    <Translate contentKey="riverApp.employee.contact">Contact</Translate>
                  </Label>
                  <AvInput id="employee-contact" type="select" className="form-control" name="contactId" onChange={this.contactUpdate}>
                    <option value="" key="0" />
                    {contacts
                      ? contacts.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="photo.id">
                    <Translate contentKey="riverApp.employee.photo">Photo</Translate>
                  </Label>
                  <AvInput id="employee-photo" type="select" className="form-control" name="photoId" onChange={this.photoUpdate}>
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
                    <Translate contentKey="riverApp.employee.jobtitle">Jobtitle</Translate>
                  </Label>
                  <AvInput id="employee-jobtitle" type="select" className="form-control" name="jobtitleId" onChange={this.jobtitleUpdate}>
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
                <AvGroup>
                  <Label for="department.departmentName">
                    <Translate contentKey="riverApp.employee.department">Department</Translate>
                  </Label>
                  <AvInput
                    id="employee-department"
                    type="select"
                    className="form-control"
                    name="departmentId"
                    onChange={this.departmentUpdate}
                  >
                    <option value="" key="0" />
                    {departments
                      ? departments.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.departmentName}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="manager.employeeName">
                    <Translate contentKey="riverApp.employee.manager">Manager</Translate>
                  </Label>
                  <AvInput id="employee-manager" type="select" className="form-control" name="managerId" onChange={this.managerUpdate}>
                    <option value="" key="0" />
                    {employees
                      ? employees.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.employeeName}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/employee" replace color="info">
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
  contacts: storeState.contact.entities,
  photos: storeState.photo.entities,
  jobTitles: storeState.jobTitle.entities,
  departments: storeState.department.entities,
  employees: storeState.employee.entities,
  employeeEntity: storeState.employee.entity,
  loading: storeState.employee.loading,
  updating: storeState.employee.updating
});

const mapDispatchToProps = {
  getContacts,
  getPhotos,
  getJobTitles,
  getDepartments,
  getEmployees,
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
)(EmployeeUpdate);
