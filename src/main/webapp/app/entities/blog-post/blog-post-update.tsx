import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ICategory } from 'app/shared/model/category.model';
import { getEntities as getCategories } from 'app/entities/category/category.reducer';
import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { IProject } from 'app/shared/model/project.model';
import { getEntities as getProjects } from 'app/entities/project/project.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './blog-post.reducer';
import { IBlogPost } from 'app/shared/model/blog-post.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IBlogPostUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IBlogPostUpdateState {
  isNew: boolean;
  categoryId: number;
  userId: number;
  projectId: number;
}

export class BlogPostUpdate extends React.Component<IBlogPostUpdateProps, IBlogPostUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      categoryId: 0,
      userId: 0,
      projectId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getCategories();
    this.props.getUsers();
    this.props.getProjects();
  }

  onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => this.props.setBlob(name, data, contentType), isAnImage);
  };

  clearBlob = name => () => {
    this.props.setBlob(name, undefined, undefined);
  };

  saveEntity = (event, errors, values) => {
    values.postCreatedDate = new Date(values.postCreatedDate);
    values.postPublishDate = new Date(values.postPublishDate);
    values.postUpdateDate = new Date(values.postUpdateDate);

    if (errors.length === 0) {
      const { blogPostEntity } = this.props;
      const entity = {
        ...blogPostEntity,
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
    this.props.history.push('/entity/blog-post');
  };

  categoryUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        categoryId: -1
      });
    } else {
      for (const i in this.props.categories) {
        if (id === this.props.categories[i].id.toString()) {
          this.setState({
            categoryId: this.props.categories[i].id
          });
        }
      }
    }
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

  projectUpdate = element => {
    const projectName = element.target.value.toString();
    if (projectName === '') {
      this.setState({
        projectId: -1
      });
    } else {
      for (const i in this.props.projects) {
        if (projectName === this.props.projects[i].projectName.toString()) {
          this.setState({
            projectId: this.props.projects[i].id
          });
        }
      }
    }
  };

  render() {
    const isInvalid = false;
    const { blogPostEntity, categories, users, projects, loading, updating } = this.props;
    const { isNew } = this.state;

    const { postContent } = blogPostEntity;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="riverApp.blogPost.home.createOrEditLabel">
              <Translate contentKey="riverApp.blogPost.home.createOrEditLabel">Create or edit a BlogPost</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : blogPostEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="blog-post-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="postTitleLabel" for="postTitle">
                    <Translate contentKey="riverApp.blogPost.postTitle">Post Title</Translate>
                  </Label>
                  <AvField
                    id="blog-post-postTitle"
                    type="text"
                    name="postTitle"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="postStatusLabel">
                    <Translate contentKey="riverApp.blogPost.postStatus">Post Status</Translate>
                  </Label>
                  <AvInput
                    id="blog-post-postStatus"
                    type="select"
                    className="form-control"
                    name="postStatus"
                    value={(!isNew && blogPostEntity.postStatus) || 'PUBLISHED'}
                  >
                    <option value="PUBLISHED">PUBLISHED</option>
                    <option value="DRAFT">DRAFT</option>
                    <option value="DELETED">DELETED</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="postCreatedDateLabel" for="postCreatedDate">
                    <Translate contentKey="riverApp.blogPost.postCreatedDate">Post Created Date</Translate>
                  </Label>
                  <AvInput
                    id="blog-post-postCreatedDate"
                    type="datetime-local"
                    className="form-control"
                    name="postCreatedDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.blogPostEntity.postCreatedDate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="postPublishDateLabel" for="postPublishDate">
                    <Translate contentKey="riverApp.blogPost.postPublishDate">Post Publish Date</Translate>
                  </Label>
                  <AvInput
                    id="blog-post-postPublishDate"
                    type="datetime-local"
                    className="form-control"
                    name="postPublishDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.blogPostEntity.postPublishDate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="postUpdateDateLabel" for="postUpdateDate">
                    <Translate contentKey="riverApp.blogPost.postUpdateDate">Post Update Date</Translate>
                  </Label>
                  <AvInput
                    id="blog-post-postUpdateDate"
                    type="datetime-local"
                    className="form-control"
                    name="postUpdateDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.blogPostEntity.postUpdateDate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="postSeenCountLabel" for="postSeenCount">
                    <Translate contentKey="riverApp.blogPost.postSeenCount">Post Seen Count</Translate>
                  </Label>
                  <AvField id="blog-post-postSeenCount" type="number" className="form-control" name="postSeenCount" />
                </AvGroup>
                <AvGroup>
                  <Label id="postContentLabel" for="postContent">
                    <Translate contentKey="riverApp.blogPost.postContent">Post Content</Translate>
                  </Label>
                  <AvField id="blog-post-postContent" type="text" name="postContent" />
                </AvGroup>
                <AvGroup>
                  <Label for="category.id">
                    <Translate contentKey="riverApp.blogPost.category">Category</Translate>
                  </Label>
                  <AvInput id="blog-post-category" type="select" className="form-control" name="categoryId" onChange={this.categoryUpdate}>
                    <option value="" key="0" />
                    {categories
                      ? categories.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="user.login">
                    <Translate contentKey="riverApp.blogPost.user">User</Translate>
                  </Label>
                  <AvInput id="blog-post-user" type="select" className="form-control" name="userId" onChange={this.userUpdate}>
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
                  <Label for="project.projectName">
                    <Translate contentKey="riverApp.blogPost.project">Project</Translate>
                  </Label>
                  <AvInput id="blog-post-project" type="select" className="form-control" name="projectId" onChange={this.projectUpdate}>
                    <option value="" key="0" />
                    {projects
                      ? projects.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.projectName}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/blog-post" replace color="info">
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
  categories: storeState.category.entities,
  users: storeState.userManagement.users,
  projects: storeState.project.entities,
  blogPostEntity: storeState.blogPost.entity,
  loading: storeState.blogPost.loading,
  updating: storeState.blogPost.updating
});

const mapDispatchToProps = {
  getCategories,
  getUsers,
  getProjects,
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
)(BlogPostUpdate);
