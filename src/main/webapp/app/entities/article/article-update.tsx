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
import { ISupportCategory } from 'app/shared/model/support-category.model';
import { getEntities as getSupportCategories } from 'app/entities/support-category/support-category.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './article.reducer';
import { IArticle } from 'app/shared/model/article.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IArticleUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IArticleUpdateState {
  isNew: boolean;
  authorId: number;
  categoryId: number;
}

export class ArticleUpdate extends React.Component<IArticleUpdateProps, IArticleUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      authorId: 0,
      categoryId: 0,
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
    this.props.getSupportCategories();
  }

  onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => this.props.setBlob(name, data, contentType), isAnImage);
  };

  clearBlob = name => () => {
    this.props.setBlob(name, undefined, undefined);
  };

  saveEntity = (event, errors, values) => {
    values.articleDate = new Date(values.articleDate);

    if (errors.length === 0) {
      const { articleEntity } = this.props;
      const entity = {
        ...articleEntity,
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
    this.props.history.push('/entity/article');
  };

  authorUpdate = element => {
    const login = element.target.value.toString();
    if (login === '') {
      this.setState({
        authorId: -1
      });
    } else {
      for (const i in this.props.users) {
        if (login === this.props.users[i].login.toString()) {
          this.setState({
            authorId: this.props.users[i].id
          });
        }
      }
    }
  };

  categoryUpdate = element => {
    const categoryName = element.target.value.toString();
    if (categoryName === '') {
      this.setState({
        categoryId: -1
      });
    } else {
      for (const i in this.props.supportCategories) {
        if (categoryName === this.props.supportCategories[i].categoryName.toString()) {
          this.setState({
            categoryId: this.props.supportCategories[i].id
          });
        }
      }
    }
  };

  render() {
    const isInvalid = false;
    const { articleEntity, users, supportCategories, loading, updating } = this.props;
    const { isNew } = this.state;

    const { articleContent } = articleEntity;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="riverApp.article.home.createOrEditLabel">
              <Translate contentKey="riverApp.article.home.createOrEditLabel">Create or edit a Article</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : articleEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="article-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="articleTitleLabel" for="articleTitle">
                    <Translate contentKey="riverApp.article.articleTitle">Article Title</Translate>
                  </Label>
                  <AvField
                    id="article-articleTitle"
                    type="text"
                    name="articleTitle"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 128, errorMessage: translate('entity.validation.maxlength', { max: 128 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="articleStatusLabel">
                    <Translate contentKey="riverApp.article.articleStatus">Article Status</Translate>
                  </Label>
                  <AvInput
                    id="article-articleStatus"
                    type="select"
                    className="form-control"
                    name="articleStatus"
                    value={(!isNew && articleEntity.articleStatus) || 'PUBLISHED'}
                  >
                    <option value="PUBLISHED">PUBLISHED</option>
                    <option value="DRAFT">DRAFT</option>
                    <option value="DELETED">DELETED</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="articleDateLabel" for="articleDate">
                    <Translate contentKey="riverApp.article.articleDate">Article Date</Translate>
                  </Label>
                  <AvInput
                    id="article-articleDate"
                    type="datetime-local"
                    className="form-control"
                    name="articleDate"
                    value={isNew ? null : convertDateTimeFromServer(this.props.articleEntity.articleDate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="articleSeenCountLabel" for="articleSeenCount">
                    <Translate contentKey="riverApp.article.articleSeenCount">Article Seen Count</Translate>
                  </Label>
                  <AvField id="article-articleSeenCount" type="number" className="form-control" name="articleSeenCount" />
                </AvGroup>
                <AvGroup>
                  <Label id="articleContentLabel" for="articleContent">
                    <Translate contentKey="riverApp.article.articleContent">Article Content</Translate>
                  </Label>
                  <AvField id="article-articleContent" type="text" name="articleContent" />
                </AvGroup>
                <AvGroup>
                  <Label for="author.login">
                    <Translate contentKey="riverApp.article.author">Author</Translate>
                  </Label>
                  <AvInput id="article-author" type="select" className="form-control" name="authorId" onChange={this.authorUpdate}>
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
                  <Label for="category.categoryName">
                    <Translate contentKey="riverApp.article.category">Category</Translate>
                  </Label>
                  <AvInput id="article-category" type="select" className="form-control" name="categoryId" onChange={this.categoryUpdate}>
                    <option value="" key="0" />
                    {supportCategories
                      ? supportCategories.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.categoryName}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/article" replace color="info">
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
  supportCategories: storeState.supportCategory.entities,
  articleEntity: storeState.article.entity,
  loading: storeState.article.loading,
  updating: storeState.article.updating
});

const mapDispatchToProps = {
  getUsers,
  getSupportCategories,
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
)(ArticleUpdate);
