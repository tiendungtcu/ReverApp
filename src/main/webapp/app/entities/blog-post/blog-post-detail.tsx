import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './blog-post.reducer';
import { IBlogPost } from 'app/shared/model/blog-post.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBlogPostDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class BlogPostDetail extends React.Component<IBlogPostDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { blogPostEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="riverApp.blogPost.detail.title">BlogPost</Translate> [<b>{blogPostEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="postTitle">
                <Translate contentKey="riverApp.blogPost.postTitle">Post Title</Translate>
              </span>
            </dt>
            <dd>{blogPostEntity.postTitle}</dd>
            <dt>
              <span id="postStatus">
                <Translate contentKey="riverApp.blogPost.postStatus">Post Status</Translate>
              </span>
            </dt>
            <dd>{blogPostEntity.postStatus}</dd>
            <dt>
              <span id="postCreatedDate">
                <Translate contentKey="riverApp.blogPost.postCreatedDate">Post Created Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={blogPostEntity.postCreatedDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="postSeenCount">
                <Translate contentKey="riverApp.blogPost.postSeenCount">Post Seen Count</Translate>
              </span>
            </dt>
            <dd>{blogPostEntity.postSeenCount}</dd>
            <dt>
              <span id="postContent">
                <Translate contentKey="riverApp.blogPost.postContent">Post Content</Translate>
              </span>
            </dt>
            <dd>{blogPostEntity.postContent}</dd>
            <dt>
              <Translate contentKey="riverApp.blogPost.category">Category</Translate>
            </dt>
            <dd>{blogPostEntity.categoryCategoryName ? blogPostEntity.categoryCategoryName : ''}</dd>
            <dt>
              <Translate contentKey="riverApp.blogPost.user">User</Translate>
            </dt>
            <dd>{blogPostEntity.userLogin ? blogPostEntity.userLogin : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/blog-post" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/blog-post/${blogPostEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ blogPost }: IRootState) => ({
  blogPostEntity: blogPost.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(BlogPostDetail);
