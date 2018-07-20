import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './job-title.reducer';
import { IJobTitle } from 'app/shared/model/job-title.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IJobTitleDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class JobTitleDetail extends React.Component<IJobTitleDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { jobTitleEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="riverApp.jobTitle.detail.title">JobTitle</Translate> [<b>{jobTitleEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="titleName">
                <Translate contentKey="riverApp.jobTitle.titleName">Title Name</Translate>
              </span>
            </dt>
            <dd>{jobTitleEntity.titleName}</dd>
            <dt>
              <span id="salaryFactor">
                <Translate contentKey="riverApp.jobTitle.salaryFactor">Salary Factor</Translate>
              </span>
            </dt>
            <dd>{jobTitleEntity.salaryFactor}</dd>
          </dl>
          <Button tag={Link} to="/entity/job-title" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/job-title/${jobTitleEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ jobTitle }: IRootState) => ({
  jobTitleEntity: jobTitle.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(JobTitleDetail);
