import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './document.reducer';
import { IDocument } from 'app/shared/model/document.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDocumentDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class DocumentDetail extends React.Component<IDocumentDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { documentEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="riverApp.document.detail.title">Document</Translate> [<b>{documentEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="documentName">
                <Translate contentKey="riverApp.document.documentName">Document Name</Translate>
              </span>
            </dt>
            <dd>{documentEntity.documentName}</dd>
            <dt>
              <span id="documentUrl">
                <Translate contentKey="riverApp.document.documentUrl">Document Url</Translate>
              </span>
            </dt>
            <dd>{documentEntity.documentUrl}</dd>
            <dt>
              <span id="documentDate">
                <Translate contentKey="riverApp.document.documentDate">Document Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={documentEntity.documentDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="documentContent">
                <Translate contentKey="riverApp.document.documentContent">Document Content</Translate>
              </span>
            </dt>
            <dd>{documentEntity.documentContent}</dd>
            <dt>
              <span id="documentPhoto">
                <Translate contentKey="riverApp.document.documentPhoto">Document Photo</Translate>
              </span>
            </dt>
            <dd>
              {documentEntity.documentPhoto ? (
                <div>
                  <a onClick={openFile(documentEntity.documentPhotoContentType, documentEntity.documentPhoto)}>
                    <img
                      src={`data:${documentEntity.documentPhotoContentType};base64,${documentEntity.documentPhoto}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                  <span>
                    {documentEntity.documentPhotoContentType}, {byteSize(documentEntity.documentPhoto)}
                  </span>
                </div>
              ) : null}
            </dd>
            <dt>
              <span id="documentType">
                <Translate contentKey="riverApp.document.documentType">Document Type</Translate>
              </span>
            </dt>
            <dd>{documentEntity.documentType}</dd>
            <dt>
              <Translate contentKey="riverApp.document.photo">Photo</Translate>
            </dt>
            <dd>{documentEntity.photoId ? documentEntity.photoId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/document" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/document/${documentEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ document }: IRootState) => ({
  documentEntity: document.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DocumentDetail);
