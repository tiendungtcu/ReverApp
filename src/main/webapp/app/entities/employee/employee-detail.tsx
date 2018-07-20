import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './employee.reducer';
import { IEmployee } from 'app/shared/model/employee.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEmployeeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class EmployeeDetail extends React.Component<IEmployeeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { employeeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="riverApp.employee.detail.title">Employee</Translate> [<b>{employeeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="employeeName">
                <Translate contentKey="riverApp.employee.employeeName">Employee Name</Translate>
              </span>
            </dt>
            <dd>{employeeEntity.employeeName}</dd>
            <dt>
              <span id="employeeFirstName">
                <Translate contentKey="riverApp.employee.employeeFirstName">Employee First Name</Translate>
              </span>
            </dt>
            <dd>{employeeEntity.employeeFirstName}</dd>
            <dt>
              <span id="employeeLastName">
                <Translate contentKey="riverApp.employee.employeeLastName">Employee Last Name</Translate>
              </span>
            </dt>
            <dd>{employeeEntity.employeeLastName}</dd>
            <dt>
              <span id="employeeDob">
                <Translate contentKey="riverApp.employee.employeeDob">Employee Dob</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={employeeEntity.employeeDob} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="employeeSex">
                <Translate contentKey="riverApp.employee.employeeSex">Employee Sex</Translate>
              </span>
            </dt>
            <dd>{employeeEntity.employeeSex}</dd>
            <dt>
              <span id="employeeIdentityCard">
                <Translate contentKey="riverApp.employee.employeeIdentityCard">Employee Identity Card</Translate>
              </span>
            </dt>
            <dd>{employeeEntity.employeeIdentityCard}</dd>
            <dt>
              <span id="employeePhone">
                <Translate contentKey="riverApp.employee.employeePhone">Employee Phone</Translate>
              </span>
            </dt>
            <dd>{employeeEntity.employeePhone}</dd>
            <dt>
              <span id="employeeEmail">
                <Translate contentKey="riverApp.employee.employeeEmail">Employee Email</Translate>
              </span>
            </dt>
            <dd>{employeeEntity.employeeEmail}</dd>
            <dt>
              <span id="employeeAvatar">
                <Translate contentKey="riverApp.employee.employeeAvatar">Employee Avatar</Translate>
              </span>
            </dt>
            <dd>
              {employeeEntity.employeeAvatar ? (
                <div>
                  <a onClick={openFile(employeeEntity.employeeAvatarContentType, employeeEntity.employeeAvatar)}>
                    <img
                      src={`data:${employeeEntity.employeeAvatarContentType};base64,${employeeEntity.employeeAvatar}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                  <span>
                    {employeeEntity.employeeAvatarContentType}, {byteSize(employeeEntity.employeeAvatar)}
                  </span>
                </div>
              ) : null}
            </dd>
            <dt>
              <span id="employeeFacebook">
                <Translate contentKey="riverApp.employee.employeeFacebook">Employee Facebook</Translate>
              </span>
            </dt>
            <dd>{employeeEntity.employeeFacebook}</dd>
            <dt>
              <span id="employeeLinkedin">
                <Translate contentKey="riverApp.employee.employeeLinkedin">Employee Linkedin</Translate>
              </span>
            </dt>
            <dd>{employeeEntity.employeeLinkedin}</dd>
            <dt>
              <span id="employeeInstagram">
                <Translate contentKey="riverApp.employee.employeeInstagram">Employee Instagram</Translate>
              </span>
            </dt>
            <dd>{employeeEntity.employeeInstagram}</dd>
            <dt>
              <span id="employeeGooglePlus">
                <Translate contentKey="riverApp.employee.employeeGooglePlus">Employee Google Plus</Translate>
              </span>
            </dt>
            <dd>{employeeEntity.employeeGooglePlus}</dd>
            <dt>
              <span id="employeeZalo">
                <Translate contentKey="riverApp.employee.employeeZalo">Employee Zalo</Translate>
              </span>
            </dt>
            <dd>{employeeEntity.employeeZalo}</dd>
            <dt>
              <span id="employeeTwitter">
                <Translate contentKey="riverApp.employee.employeeTwitter">Employee Twitter</Translate>
              </span>
            </dt>
            <dd>{employeeEntity.employeeTwitter}</dd>
            <dt>
              <span id="employeeYoutube">
                <Translate contentKey="riverApp.employee.employeeYoutube">Employee Youtube</Translate>
              </span>
            </dt>
            <dd>{employeeEntity.employeeYoutube}</dd>
            <dt>
              <Translate contentKey="riverApp.employee.contact">Contact</Translate>
            </dt>
            <dd>{employeeEntity.contactId ? employeeEntity.contactId : ''}</dd>
            <dt>
              <Translate contentKey="riverApp.employee.photo">Photo</Translate>
            </dt>
            <dd>{employeeEntity.photoId ? employeeEntity.photoId : ''}</dd>
            <dt>
              <Translate contentKey="riverApp.employee.jobtitle">Jobtitle</Translate>
            </dt>
            <dd>{employeeEntity.jobtitleTitleName ? employeeEntity.jobtitleTitleName : ''}</dd>
            <dt>
              <Translate contentKey="riverApp.employee.department">Department</Translate>
            </dt>
            <dd>{employeeEntity.departmentDepartmentName ? employeeEntity.departmentDepartmentName : ''}</dd>
            <dt>
              <Translate contentKey="riverApp.employee.manager">Manager</Translate>
            </dt>
            <dd>{employeeEntity.managerEmployeeName ? employeeEntity.managerEmployeeName : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/employee" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/employee/${employeeEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ employee }: IRootState) => ({
  employeeEntity: employee.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EmployeeDetail);
