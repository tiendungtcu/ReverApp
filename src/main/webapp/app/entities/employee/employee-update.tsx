import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { IDepartment } from 'app/shared/model/department.model';
import { getEntities as getDepartments } from 'app/entities/department/department.reducer';
import { IJobTitle } from 'app/shared/model/job-title.model';
import { getEntities as getJobTitles } from 'app/entities/job-title/job-title.reducer';
import { getEntity, updateEntity, createEntity, reset } from './employee.reducer';
import { IEmployee } from 'app/shared/model/employee.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IEmployeeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IEmployeeUpdateState {
  isNew: boolean;
  accountId: number;
  departmentId: number;
  jobtitleId: number;
}

export class EmployeeUpdate extends React.Component<IEmployeeUpdateProps, IEmployeeUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      accountId: 0,
      departmentId: 0,
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
    this.props.getDepartments();
    this.props.getJobTitles();
  }

  saveEntity = (event, errors, values) => {
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

  accountUpdate = element => {
    const login = element.target.value.toString();
    if (login === '') {
      this.setState({
        accountId: -1
      });
    } else {
      for (const i in this.props.users) {
        if (login === this.props.users[i].login.toString()) {
          this.setState({
            accountId: this.props.users[i].id
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
    const { employeeEntity, users, departments, jobTitles, loading, updating } = this.props;
    const { isNew } = this.state;

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
                  <Label id="employeeFirstNameLabel" for="employeeFirstName">
                    <Translate contentKey="riverApp.employee.employeeFirstName">Employee First Name</Translate>
                  </Label>
                  <AvField
                    id="employee-employeeFirstName"
                    type="text"
                    name="employeeFirstName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 128, errorMessage: translate('entity.validation.maxlength', { max: 128 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="employeeLastNameLabel" for="employeeLastName">
                    <Translate contentKey="riverApp.employee.employeeLastName">Employee Last Name</Translate>
                  </Label>
                  <AvField
                    id="employee-employeeLastName"
                    type="text"
                    name="employeeLastName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 128, errorMessage: translate('entity.validation.maxlength', { max: 128 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="employeeDobLabel" for="employeeDob">
                    <Translate contentKey="riverApp.employee.employeeDob">Employee Dob</Translate>
                  </Label>
                  <AvField id="employee-employeeDob" type="date" className="form-control" name="employeeDob" />
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
                  <AvField
                    id="employee-employeeIdentityCard"
                    type="text"
                    name="employeeIdentityCard"
                    validate={{
                      maxLength: { value: 16, errorMessage: translate('entity.validation.maxlength', { max: 16 }) }
                    }}
                  />
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
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 16, errorMessage: translate('entity.validation.maxlength', { max: 16 }) }
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
                  <Label for="account.login">
                    <Translate contentKey="riverApp.employee.account">Account</Translate>
                  </Label>
                  <AvInput id="employee-account" type="select" className="form-control" name="accountId" onChange={this.accountUpdate}>
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
  users: storeState.userManagement.users,
  departments: storeState.department.entities,
  jobTitles: storeState.jobTitle.entities,
  employeeEntity: storeState.employee.entity,
  loading: storeState.employee.loading,
  updating: storeState.employee.updating
});

const mapDispatchToProps = {
  getUsers,
  getDepartments,
  getJobTitles,
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
)(EmployeeUpdate);
