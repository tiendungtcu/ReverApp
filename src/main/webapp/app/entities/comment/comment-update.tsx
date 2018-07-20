import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { IBlogPost } from 'app/shared/model/blog-post.model';
import { getEntities as getBlogPosts } from 'app/entities/blog-post/blog-post.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './comment.reducer';
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

  onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => this.props.setBlob(name, data, contentType), isAnImage);
  };

  clearBlob = name => () => {
    this.props.setBlob(name, undefined, undefined);
  };

  saveEntity = (event, errors, values) => {
    values.commentCreatedDate = new Date(values.commentCreatedDate);
    values.commentUpdateDate = new Date(values.commentUpdateDate);

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
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        postId: -1
      });
    } else {
      for (const i in this.props.blogPosts) {
        if (id === this.props.blogPosts[i].id.toString()) {
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

    const { commentContent } = commentEntity;

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
                  <Label id="commentTitleLabel" for="commentTitle">
                    <Translate contentKey="riverApp.comment.commentTitle">Comment Title</Translate>
                  </Label>
                  <AvField
                    id="comment-commentTitle"
                    type="text"
                    name="commentTitle"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="commentContentLabel" for="commentContent">
                    <Translate contentKey="riverApp.comment.commentContent">Comment Content</Translate>
                  </Label>
                  <AvField id="comment-commentContent" type="text" name="commentContent" />
                </AvGroup>
                <AvGroup>
                  <Label id="commentCreatedDateLabel" for="commentCreatedDate">
                    <Translate contentKey="riverApp.comment.commentCreatedDate">Comment Created Date</Translate>
                  </Label>
                  <AvInput
                    id="comment-commentCreatedDate"
                    type="datetime-local"
                    className="form-control"
                    name="commentCreatedDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.commentEntity.commentCreatedDate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="commentUpdateDateLabel" for="commentUpdateDate">
                    <Translate contentKey="riverApp.comment.commentUpdateDate">Comment Update Date</Translate>
                  </Label>
                  <AvInput
                    id="comment-commentUpdateDate"
                    type="datetime-local"
                    className="form-control"
                    name="commentUpdateDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.commentEntity.commentUpdateDate)}
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
                  <Label for="post.id">
                    <Translate contentKey="riverApp.comment.post">Post</Translate>
                  </Label>
                  <AvInput id="comment-post" type="select" className="form-control" name="postId" onChange={this.postUpdate}>
                    <option value="" key="0" />
                    {blogPosts
                      ? blogPosts.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
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
  setBlob,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CommentUpdate);
