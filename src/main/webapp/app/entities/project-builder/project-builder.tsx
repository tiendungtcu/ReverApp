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
import { getEntities } from './project-builder.reducer';
import { IProjectBuilder } from 'app/shared/model/project-builder.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IProjectBuilderProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IProjectBuilderState = IPaginationBaseState;

export class ProjectBuilder extends React.Component<IProjectBuilderProps, IProjectBuilderState> {
  state: IProjectBuilderState = {
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
    const { projectBuilderList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="project-builder-heading">
          <Translate contentKey="riverApp.projectBuilder.home.title">Project Builders</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="riverApp.projectBuilder.home.createLabel">Create new Project Builder</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('builderName')}>
                  <Translate contentKey="riverApp.projectBuilder.builderName">Builder Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('builderTitle')}>
                  <Translate contentKey="riverApp.projectBuilder.builderTitle">Builder Title</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('builderDate')}>
                  <Translate contentKey="riverApp.projectBuilder.builderDate">Builder Date</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('builderDescription')}>
                  <Translate contentKey="riverApp.projectBuilder.builderDescription">Builder Description</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('builderAddress')}>
                  <Translate contentKey="riverApp.projectBuilder.builderAddress">Builder Address</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('builderWebsite')}>
                  <Translate contentKey="riverApp.projectBuilder.builderWebsite">Builder Website</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('builderPhone')}>
                  <Translate contentKey="riverApp.projectBuilder.builderPhone">Builder Phone</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('builderPhoto')}>
                  <Translate contentKey="riverApp.projectBuilder.builderPhoto">Builder Photo</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {projectBuilderList.map((projectBuilder, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${projectBuilder.id}`} color="link" size="sm">
                      {projectBuilder.id}
                    </Button>
                  </td>
                  <td>{projectBuilder.builderName}</td>
                  <td>{projectBuilder.builderTitle}</td>
                  <td>
                    <TextFormat type="date" value={projectBuilder.builderDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{projectBuilder.builderDescription}</td>
                  <td>{projectBuilder.builderAddress}</td>
                  <td>{projectBuilder.builderWebsite}</td>
                  <td>{projectBuilder.builderPhone}</td>
                  <td>
                    {projectBuilder.builderPhoto ? (
                      <div>
                        <a onClick={openFile(projectBuilder.builderPhotoContentType, projectBuilder.builderPhoto)}>
                          <img
                            src={`data:${projectBuilder.builderPhotoContentType};base64,${projectBuilder.builderPhoto}`}
                            style={{ maxHeight: '30px' }}
                          />
                          &nbsp;
                        </a>
                        <span>
                          {projectBuilder.builderPhotoContentType}, {byteSize(projectBuilder.builderPhoto)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${projectBuilder.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${projectBuilder.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${projectBuilder.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ projectBuilder }: IRootState) => ({
  projectBuilderList: projectBuilder.entities,
  totalItems: projectBuilder.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ProjectBuilder);
