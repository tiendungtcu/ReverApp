import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './article.reducer';
import { IArticle } from 'app/shared/model/article.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IArticleDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class ArticleDetail extends React.Component<IArticleDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { articleEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="riverApp.article.detail.title">Article</Translate> [<b>{articleEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="articleTitle">
                <Translate contentKey="riverApp.article.articleTitle">Article Title</Translate>
              </span>
            </dt>
            <dd>{articleEntity.articleTitle}</dd>
            <dt>
              <span id="articleStatus">
                <Translate contentKey="riverApp.article.articleStatus">Article Status</Translate>
              </span>
            </dt>
            <dd>{articleEntity.articleStatus}</dd>
            <dt>
              <span id="articleDate">
                <Translate contentKey="riverApp.article.articleDate">Article Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={articleEntity.articleDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="articleSeenCount">
                <Translate contentKey="riverApp.article.articleSeenCount">Article Seen Count</Translate>
              </span>
            </dt>
            <dd>{articleEntity.articleSeenCount}</dd>
            <dt>
              <span id="articleContent">
                <Translate contentKey="riverApp.article.articleContent">Article Content</Translate>
              </span>
            </dt>
            <dd>{articleEntity.articleContent}</dd>
            <dt>
              <Translate contentKey="riverApp.article.author">Author</Translate>
            </dt>
            <dd>{articleEntity.authorLogin ? articleEntity.authorLogin : ''}</dd>
            <dt>
              <Translate contentKey="riverApp.article.category">Category</Translate>
            </dt>
            <dd>{articleEntity.categoryCategoryName ? articleEntity.categoryCategoryName : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/article" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/article/${articleEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ article }: IRootState) => ({
  articleEntity: article.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ArticleDetail);
