import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './photo.reducer';
import { IPhoto } from 'app/shared/model/photo.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPhotoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class PhotoDetail extends React.Component<IPhotoDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { photoEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="riverApp.photo.detail.title">Photo</Translate> [<b>{photoEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="photoName">
                <Translate contentKey="riverApp.photo.photoName">Photo Name</Translate>
              </span>
            </dt>
            <dd>{photoEntity.photoName}</dd>
            <dt>
              <span id="photoDate">
                <Translate contentKey="riverApp.photo.photoDate">Photo Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={photoEntity.photoDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="photoUrl">
                <Translate contentKey="riverApp.photo.photoUrl">Photo Url</Translate>
              </span>
            </dt>
            <dd>{photoEntity.photoUrl}</dd>
            <dt>
              <span id="photoMimeType">
                <Translate contentKey="riverApp.photo.photoMimeType">Photo Mime Type</Translate>
              </span>
            </dt>
            <dd>{photoEntity.photoMimeType}</dd>
            <dt>
              <span id="resourceId">
                <Translate contentKey="riverApp.photo.resourceId">Resource Id</Translate>
              </span>
            </dt>
            <dd>{photoEntity.resourceId}</dd>
            <dt>
              <span id="resourceType">
                <Translate contentKey="riverApp.photo.resourceType">Resource Type</Translate>
              </span>
            </dt>
            <dd>{photoEntity.resourceType}</dd>
            <dt>
              <span id="photoSize">
                <Translate contentKey="riverApp.photo.photoSize">Photo Size</Translate>
              </span>
            </dt>
            <dd>{photoEntity.photoSize}</dd>
            <dt>
              <span id="photoAltText">
                <Translate contentKey="riverApp.photo.photoAltText">Photo Alt Text</Translate>
              </span>
            </dt>
            <dd>{photoEntity.photoAltText}</dd>
            <dt>
              <span id="photoThumbnailUrl">
                <Translate contentKey="riverApp.photo.photoThumbnailUrl">Photo Thumbnail Url</Translate>
              </span>
            </dt>
            <dd>{photoEntity.photoThumbnailUrl}</dd>
          </dl>
          <Button tag={Link} to="/entity/photo" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/photo/${photoEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ photo }: IRootState) => ({
  photoEntity: photo.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PhotoDetail);
