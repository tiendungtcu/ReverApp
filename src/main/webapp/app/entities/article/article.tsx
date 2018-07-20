import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { byteSize, Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './article.reducer';
import { IArticle } from 'app/shared/model/article.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IArticleProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Article extends React.Component<IArticleProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { articleList, match } = this.props;
    return (
      <div>
        <h2 id="article-heading">
          <Translate contentKey="riverApp.article.home.title">Articles</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="riverApp.article.home.createLabel">Create new Article</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.article.articleTitle">Article Title</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.article.articleStatus">Article Status</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.article.articleDate">Article Date</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.article.articleSeenCount">Article Seen Count</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.article.articleContent">Article Content</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.article.category">Category</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.article.user">User</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {articleList.map((article, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${article.id}`} color="link" size="sm">
                      {article.id}
                    </Button>
                  </td>
                  <td>{article.articleTitle}</td>
                  <td>{article.articleStatus}</td>
                  <td>
                    <TextFormat type="date" value={article.articleDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{article.articleSeenCount}</td>
                  <td>{article.articleContent}</td>
                  <td>{article.categoryId ? <Link to={`supportCategory/${article.categoryId}`}>{article.categoryId}</Link> : ''}</td>
                  <td>{article.userLogin ? article.userLogin : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${article.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${article.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${article.id}/delete`} color="danger" size="sm">
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
      </div>
    );
  }
}

const mapStateToProps = ({ article }: IRootState) => ({
  articleList: article.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Article);
