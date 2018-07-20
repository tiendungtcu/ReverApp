import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './department.reducer';
import { IDepartment } from 'app/shared/model/department.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDepartmentDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class DepartmentDetail extends React.Component<IDepartmentDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { departmentEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="riverApp.department.detail.title">Department</Translate> [<b>{departmentEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="departmentName">
                <Translate contentKey="riverApp.department.departmentName">Department Name</Translate>
              </span>
            </dt>
            <dd>{departmentEntity.departmentName}</dd>
            <dt>
              <span id="departmentPhoto">
                <Translate contentKey="riverApp.department.departmentPhoto">Department Photo</Translate>
              </span>
            </dt>
            <dd>
              {departmentEntity.departmentPhoto ? (
                <div>
                  <a onClick={openFile(departmentEntity.departmentPhotoContentType, departmentEntity.departmentPhoto)}>
                    <img
                      src={`data:${departmentEntity.departmentPhotoContentType};base64,${departmentEntity.departmentPhoto}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                  <span>
                    {departmentEntity.departmentPhotoContentType}, {byteSize(departmentEntity.departmentPhoto)}
                  </span>
                </div>
              ) : null}
            </dd>
            <dt>
              <span id="departmentPhone">
                <Translate contentKey="riverApp.department.departmentPhone">Department Phone</Translate>
              </span>
            </dt>
            <dd>{departmentEntity.departmentPhone}</dd>
          </dl>
          <Button tag={Link} to="/entity/department" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/department/${departmentEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ department }: IRootState) => ({
  departmentEntity: department.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DepartmentDetail);
