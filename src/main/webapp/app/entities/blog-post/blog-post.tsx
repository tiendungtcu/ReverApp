import React from 'react';
import InfiniteScroll from 'react-infinite-scroller';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { byteSize, Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities, reset } from './blog-post.reducer';
import { IBlogPost } from 'app/shared/model/blog-post.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IBlogPostProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IBlogPostState = IPaginationBaseState;

export class BlogPost extends React.Component<IBlogPostProps, IBlogPostState> {
  state: IBlogPostState = {
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
    const { blogPostList, match } = this.props;
    return (
      <div>
        <h2 id="blog-post-heading">
          <Translate contentKey="riverApp.blogPost.home.title">Blog Posts</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="riverApp.blogPost.home.createLabel">Create new Blog Post</Translate>
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
                  <th className="hand" onClick={this.sort('postTitle')}>
                    <Translate contentKey="riverApp.blogPost.postTitle">Post Title</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('postStatus')}>
                    <Translate contentKey="riverApp.blogPost.postStatus">Post Status</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('postCreatedDate')}>
                    <Translate contentKey="riverApp.blogPost.postCreatedDate">Post Created Date</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('postPublishDate')}>
                    <Translate contentKey="riverApp.blogPost.postPublishDate">Post Publish Date</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('postUpdateDate')}>
                    <Translate contentKey="riverApp.blogPost.postUpdateDate">Post Update Date</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('postSeenCount')}>
                    <Translate contentKey="riverApp.blogPost.postSeenCount">Post Seen Count</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('postContent')}>
                    <Translate contentKey="riverApp.blogPost.postContent">Post Content</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="riverApp.blogPost.category">Category</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="riverApp.blogPost.user">User</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="riverApp.blogPost.project">Project</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {blogPostList.map((blogPost, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${blogPost.id}`} color="link" size="sm">
                        {blogPost.id}
                      </Button>
                    </td>
                    <td>{blogPost.postTitle}</td>
                    <td>{blogPost.postStatus}</td>
                    <td>
                      <TextFormat type="date" value={blogPost.postCreatedDate} format={APP_DATE_FORMAT} />
                    </td>
                    <td>
                      <TextFormat type="date" value={blogPost.postPublishDate} format={APP_DATE_FORMAT} />
                    </td>
                    <td>
                      <TextFormat type="date" value={blogPost.postUpdateDate} format={APP_DATE_FORMAT} />
                    </td>
                    <td>{blogPost.postSeenCount}</td>
                    <td>{blogPost.postContent}</td>
                    <td>{blogPost.categoryId ? <Link to={`category/${blogPost.categoryId}`}>{blogPost.categoryId}</Link> : ''}</td>
                    <td>{blogPost.userLogin ? blogPost.userLogin : ''}</td>
                    <td>
                      {blogPost.projectProjectName ? <Link to={`project/${blogPost.projectId}`}>{blogPost.projectProjectName}</Link> : ''}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${blogPost.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${blogPost.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${blogPost.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ blogPost }: IRootState) => ({
  blogPostList: blogPost.entities,
  totalItems: blogPost.totalItems,
  links: blogPost.links
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
)(BlogPost);
