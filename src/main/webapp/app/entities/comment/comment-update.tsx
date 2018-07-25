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
import { IBlogPost } from 'app/shared/model/blog-post.model';
import { getEntities as getBlogPosts } from 'app/entities/blog-post/blog-post.reducer';
import { getEntity, updateEntity, createEntity, reset } from './comment.reducer';
import { IComment } from 'app/shared/model/comment.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface ICommentUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface ICommentUpdateState {
  isNew: boolean;
  userId: number;
  postId: number;
}

export class CommentUpdate extends React.Component<ICommentUpdateProps, ICommentUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      userId: 0,
      postId: 0,
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
    this.props.getBlogPosts();
  }

  saveEntity = (event, errors, values) => {
    values.commentTimeStamp = new Date(values.commentTimeStamp);

    if (errors.length === 0) {
      const { commentEntity } = this.props;
      const entity = {
        ...commentEntity,
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
    this.props.history.push('/entity/comment');
  };

  userUpdate = element => {
    const login = element.target.value.toString();
    if (login === '') {
      this.setState({
        userId: -1
      });
    } else {
      for (const i in this.props.users) {
        if (login === this.props.users[i].login.toString()) {
          this.setState({
            userId: this.props.users[i].id
          });
        }
      }
    }
  };

  postUpdate = element => {
    const postTitle = element.target.value.toString();
    if (postTitle === '') {
      this.setState({
        postId: -1
      });
    } else {
      for (const i in this.props.blogPosts) {
        if (postTitle === this.props.blogPosts[i].postTitle.toString()) {
          this.setState({
            postId: this.props.blogPosts[i].id
          });
        }
      }
    }
  };

  render() {
    const isInvalid = false;
    const { commentEntity, users, blogPosts, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="riverApp.comment.home.createOrEditLabel">
              <Translate contentKey="riverApp.comment.home.createOrEditLabel">Create or edit a Comment</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : commentEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="comment-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="commentContentLabel" for="commentContent">
                    <Translate contentKey="riverApp.comment.commentContent">Comment Content</Translate>
                  </Label>
                  <AvField id="comment-commentContent" type="text" name="commentContent" />
                </AvGroup>
                <AvGroup>
                  <Label id="commentTimeStampLabel" for="commentTimeStamp">
                    <Translate contentKey="riverApp.comment.commentTimeStamp">Comment Time Stamp</Translate>
                  </Label>
                  <AvInput
                    id="comment-commentTimeStamp"
                    type="datetime-local"
                    className="form-control"
                    name="commentTimeStamp"
                    value={isNew ? null : convertDateTimeFromServer(this.props.commentEntity.commentTimeStamp)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="user.login">
                    <Translate contentKey="riverApp.comment.user">User</Translate>
                  </Label>
                  <AvInput id="comment-user" type="select" className="form-control" name="userId" onChange={this.userUpdate}>
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
                  <Label for="post.postTitle">
                    <Translate contentKey="riverApp.comment.post">Post</Translate>
                  </Label>
                  <AvInput id="comment-post" type="select" className="form-control" name="postId" onChange={this.postUpdate}>
                    <option value="" key="0" />
                    {blogPosts
                      ? blogPosts.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.postTitle}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/comment" replace color="info">
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
  blogPosts: storeState.blogPost.entities,
  commentEntity: storeState.comment.entity,
  loading: storeState.comment.loading,
  updating: storeState.comment.updating
});

const mapDispatchToProps = {
  getUsers,
  getBlogPosts,
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
)(CommentUpdate);
