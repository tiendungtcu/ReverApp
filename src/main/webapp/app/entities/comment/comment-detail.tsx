import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './comment.reducer';
import { IComment } from 'app/shared/model/comment.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICommentDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class CommentDetail extends React.Component<ICommentDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { commentEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="riverApp.comment.detail.title">Comment</Translate> [<b>{commentEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="commentTitle">
                <Translate contentKey="riverApp.comment.commentTitle">Comment Title</Translate>
              </span>
            </dt>
            <dd>{commentEntity.commentTitle}</dd>
            <dt>
              <span id="commentContent">
                <Translate contentKey="riverApp.comment.commentContent">Comment Content</Translate>
              </span>
            </dt>
            <dd>{commentEntity.commentContent}</dd>
            <dt>
              <span id="commentCreatedDate">
                <Translate contentKey="riverApp.comment.commentCreatedDate">Comment Created Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={commentEntity.commentCreatedDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="commentUpdateDate">
                <Translate contentKey="riverApp.comment.commentUpdateDate">Comment Update Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={commentEntity.commentUpdateDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <Translate contentKey="riverApp.comment.user">User</Translate>
            </dt>
            <dd>{commentEntity.userLogin ? commentEntity.userLogin : ''}</dd>
            <dt>
              <Translate contentKey="riverApp.comment.post">Post</Translate>
            </dt>
            <dd>{commentEntity.postId ? commentEntity.postId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/comment" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/comment/${commentEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ comment }: IRootState) => ({
  commentEntity: comment.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CommentDetail);
