import React from 'react';
import InfiniteScroll from 'react-infinite-scroller';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { openFile, byteSize, Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities, reset } from './document.reducer';
import { IDocument } from 'app/shared/model/document.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IDocumentProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IDocumentState = IPaginationBaseState;

export class Document extends React.Component<IDocumentProps, IDocumentState> {
  state: IDocumentState = {
    ...getSortState(this.props.location, ITEMS_PER_PAGE)
  };

  componentDidMount() {
    this.reset();
  }

  reset = () => {
    this.props.reset();
    this.setState({ activePage: 1 }, () => this.getEntities());
  };

  handleLoadMore = page => {
    this.setState({ activePage: this.state.activePage + 1 }, () => this.getEntities());
  };

  sort = prop => () => {
    this.setState(
      {
        order: this.state.order === 'asc' ? 'desc' : 'asc',
        sort: prop
      },
      () => this.reset()
    );
  };

  getEntities = () => {
    const { activePage, itemsPerPage, sort, order } = this.state;
    this.props.getEntities(activePage - 1, itemsPerPage, `${sort},${order}`);
  };

  render() {
    const { documentList, match } = this.props;
    return (
      <div>
        <h2 id="document-heading">
          <Translate contentKey="riverApp.document.home.title">Documents</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="riverApp.document.home.createLabel">Create new Document</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <InfiniteScroll
            pageStart={this.state.activePage}
            loadMore={this.handleLoadMore}
            hasMore={this.state.activePage <= this.props.links.last}
            loader={<div className="loader">Loading ...</div>}
            threshold={0}
            initialLoad={false}
          >
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={this.sort('id')}>
                    <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('documentName')}>
                    <Translate contentKey="riverApp.document.documentName">Document Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('documentUrl')}>
                    <Translate contentKey="riverApp.document.documentUrl">Document Url</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('documentDate')}>
                    <Translate contentKey="riverApp.document.documentDate">Document Date</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('documentContent')}>
                    <Translate contentKey="riverApp.document.documentContent">Document Content</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('documentPhoto')}>
                    <Translate contentKey="riverApp.document.documentPhoto">Document Photo</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('documentType')}>
                    <Translate contentKey="riverApp.document.documentType">Document Type</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="riverApp.document.photo">Photo</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {documentList.map((document, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${document.id}`} color="link" size="sm">
                        {document.id}
                      </Button>
                    </td>
                    <td>{document.documentName}</td>
                    <td>{document.documentUrl}</td>
                    <td>
                      <TextFormat type="date" value={document.documentDate} format={APP_DATE_FORMAT} />
                    </td>
                    <td>{document.documentContent}</td>
                    <td>
                      {document.documentPhoto ? (
                        <div>
                          <a onClick={openFile(document.documentPhotoContentType, document.documentPhoto)}>
                            <img
                              src={`data:${document.documentPhotoContentType};base64,${document.documentPhoto}`}
                              style={{ maxHeight: '30px' }}
                            />
                            &nbsp;
                          </a>
                          <span>
                            {document.documentPhotoContentType}, {byteSize(document.documentPhoto)}
                          </span>
                        </div>
                      ) : null}
                    </td>
                    <td>{document.documentType}</td>
                    <td>{document.photoId ? <Link to={`photo/${document.photoId}`}>{document.photoId}</Link> : ''}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${document.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${document.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${document.id}/delete`} color="danger" size="sm">
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
          </InfiniteScroll>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ document }: IRootState) => ({
  documentList: document.entities,
  totalItems: document.totalItems,
  links: document.links
});

const mapDispatchToProps = {
  getEntities,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Document);
