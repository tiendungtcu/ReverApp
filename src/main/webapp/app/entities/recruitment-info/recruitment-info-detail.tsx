import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './recruitment-info.reducer';
import { IRecruitmentInfo } from 'app/shared/model/recruitment-info.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IRecruitmentInfoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class RecruitmentInfoDetail extends React.Component<IRecruitmentInfoDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { recruitmentInfoEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="riverApp.recruitmentInfo.detail.title">RecruitmentInfo</Translate> [<b>{recruitmentInfoEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="recruitmentTitle">
                <Translate contentKey="riverApp.recruitmentInfo.recruitmentTitle">Recruitment Title</Translate>
              </span>
            </dt>
            <dd>{recruitmentInfoEntity.recruitmentTitle}</dd>
            <dt>
              <span id="recruitmentAvatarUrl">
                <Translate contentKey="riverApp.recruitmentInfo.recruitmentAvatarUrl">Recruitment Avatar Url</Translate>
              </span>
            </dt>
            <dd>{recruitmentInfoEntity.recruitmentAvatarUrl}</dd>
            <dt>
              <span id="recruitmentContent">
                <Translate contentKey="riverApp.recruitmentInfo.recruitmentContent">Recruitment Content</Translate>
              </span>
            </dt>
            <dd>{recruitmentInfoEntity.recruitmentContent}</dd>
            <dt>
              <span id="recruitmentNotes">
                <Translate contentKey="riverApp.recruitmentInfo.recruitmentNotes">Recruitment Notes</Translate>
              </span>
            </dt>
            <dd>{recruitmentInfoEntity.recruitmentNotes}</dd>
            <dt>
              <span id="recruitmentDate">
                <Translate contentKey="riverApp.recruitmentInfo.recruitmentDate">Recruitment Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={recruitmentInfoEntity.recruitmentDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="recruitmentSeenCount">
                <Translate contentKey="riverApp.recruitmentInfo.recruitmentSeenCount">Recruitment Seen Count</Translate>
              </span>
            </dt>
            <dd>{recruitmentInfoEntity.recruitmentSeenCount}</dd>
            <dt>
              <span id="recruitmentStatus">
                <Translate contentKey="riverApp.recruitmentInfo.recruitmentStatus">Recruitment Status</Translate>
              </span>
            </dt>
            <dd>{recruitmentInfoEntity.recruitmentStatus ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="riverApp.recruitmentInfo.user">User</Translate>
            </dt>
            <dd>{recruitmentInfoEntity.userLogin ? recruitmentInfoEntity.userLogin : ''}</dd>
            <dt>
              <Translate contentKey="riverApp.recruitmentInfo.jobtitle">Jobtitle</Translate>
            </dt>
            <dd>{recruitmentInfoEntity.jobtitleTitleName ? recruitmentInfoEntity.jobtitleTitleName : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/recruitment-info" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/recruitment-info/${recruitmentInfoEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ recruitmentInfo }: IRootState) => ({
  recruitmentInfoEntity: recruitmentInfo.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(RecruitmentInfoDetail);
