import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import {
  openFile,
  byteSize,
  Translate,
  ICrudGetAllAction,
  TextFormat,
  getSortState,
  IPaginationBaseState,
  getPaginationItemsNumber,
  JhiPagination
} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './recruitment-info.reducer';
import { IRecruitmentInfo } from 'app/shared/model/recruitment-info.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IRecruitmentInfoProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IRecruitmentInfoState = IPaginationBaseState;

export class RecruitmentInfo extends React.Component<IRecruitmentInfoProps, IRecruitmentInfoState> {
  state: IRecruitmentInfoState = {
    ...getSortState(this.props.location, ITEMS_PER_PAGE)
  };

  componentDidMount() {
    this.getEntities();
  }

  sort = prop => () => {
    this.setState(
      {
        order: this.state.order === 'asc' ? 'desc' : 'asc',
        sort: prop
      },
      () => this.sortEntities()
    );
  };

  sortEntities() {
    this.getEntities();
    this.props.history.push(`${this.props.location.pathname}?page=${this.state.activePage}&sort=${this.state.sort},${this.state.order}`);
  }

  handlePagination = activePage => this.setState({ activePage }, () => this.sortEntities());

  getEntities = () => {
    const { activePage, itemsPerPage, sort, order } = this.state;
    this.props.getEntities(activePage - 1, itemsPerPage, `${sort},${order}`);
  };

  render() {
    const { recruitmentInfoList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="recruitment-info-heading">
          <Translate contentKey="riverApp.recruitmentInfo.home.title">Recruitment Infos</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="riverApp.recruitmentInfo.home.createLabel">Create new Recruitment Info</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('recruitmentTitle')}>
                  <Translate contentKey="riverApp.recruitmentInfo.recruitmentTitle">Recruitment Title</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('recruitmentImage')}>
                  <Translate contentKey="riverApp.recruitmentInfo.recruitmentImage">Recruitment Image</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('recruitmentContent')}>
                  <Translate contentKey="riverApp.recruitmentInfo.recruitmentContent">Recruitment Content</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('recruitmentNotes')}>
                  <Translate contentKey="riverApp.recruitmentInfo.recruitmentNotes">Recruitment Notes</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('recruitmentDate')}>
                  <Translate contentKey="riverApp.recruitmentInfo.recruitmentDate">Recruitment Date</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('recruitmentSeenCount')}>
                  <Translate contentKey="riverApp.recruitmentInfo.recruitmentSeenCount">Recruitment Seen Count</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('recruitmentStatus')}>
                  <Translate contentKey="riverApp.recruitmentInfo.recruitmentStatus">Recruitment Status</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="riverApp.recruitmentInfo.photo">Photo</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="riverApp.recruitmentInfo.jobtitle">Jobtitle</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {recruitmentInfoList.map((recruitmentInfo, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${recruitmentInfo.id}`} color="link" size="sm">
                      {recruitmentInfo.id}
                    </Button>
                  </td>
                  <td>{recruitmentInfo.recruitmentTitle}</td>
                  <td>
                    {recruitmentInfo.recruitmentImage ? (
                      <div>
                        <a onClick={openFile(recruitmentInfo.recruitmentImageContentType, recruitmentInfo.recruitmentImage)}>
                          <img
                            src={`data:${recruitmentInfo.recruitmentImageContentType};base64,${recruitmentInfo.recruitmentImage}`}
                            style={{ maxHeight: '30px' }}
                          />
                          &nbsp;
                        </a>
                        <span>
                          {recruitmentInfo.recruitmentImageContentType}, {byteSize(recruitmentInfo.recruitmentImage)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{recruitmentInfo.recruitmentContent}</td>
                  <td>{recruitmentInfo.recruitmentNotes}</td>
                  <td>
                    <TextFormat type="date" value={recruitmentInfo.recruitmentDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{recruitmentInfo.recruitmentSeenCount}</td>
                  <td>{recruitmentInfo.recruitmentStatus ? 'true' : 'false'}</td>
                  <td>{recruitmentInfo.photoId ? <Link to={`photo/${recruitmentInfo.photoId}`}>{recruitmentInfo.photoId}</Link> : ''}</td>
                  <td>
                    {recruitmentInfo.jobtitleTitleName ? (
                      <Link to={`jobTitle/${recruitmentInfo.jobtitleId}`}>{recruitmentInfo.jobtitleTitleName}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${recruitmentInfo.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${recruitmentInfo.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${recruitmentInfo.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
        <Row className="justify-content-center">
          <JhiPagination
            items={getPaginationItemsNumber(totalItems, this.state.itemsPerPage)}
            activePage={this.state.activePage}
            onSelect={this.handlePagination}
            maxButtons={5}
          />
        </Row>
      </div>
    );
  }
}

const mapStateToProps = ({ recruitmentInfo }: IRootState) => ({
  recruitmentInfoList: recruitmentInfo.entities,
  totalItems: recruitmentInfo.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(RecruitmentInfo);
