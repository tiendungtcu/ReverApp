import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, openFile, byteSize } from 'react-jhipster';
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
              <span id="photoImage">
                <Translate contentKey="riverApp.photo.photoImage">Photo Image</Translate>
              </span>
            </dt>
            <dd>
              {photoEntity.photoImage ? (
                <div>
                  <a onClick={openFile(photoEntity.photoImageContentType, photoEntity.photoImage)}>
                    <img src={`data:${photoEntity.photoImageContentType};base64,${photoEntity.photoImage}`} style={{ maxHeight: '30px' }} />
                  </a>
                  <span>
                    {photoEntity.photoImageContentType}, {byteSize(photoEntity.photoImage)}
                  </span>
                </div>
              ) : null}
            </dd>
            <dt>
              <span id="photoExtension">
                <Translate contentKey="riverApp.photo.photoExtension">Photo Extension</Translate>
              </span>
            </dt>
            <dd>{photoEntity.photoExtension}</dd>
            <dt>
              <span id="photoUrl">
                <Translate contentKey="riverApp.photo.photoUrl">Photo Url</Translate>
              </span>
            </dt>
            <dd>{photoEntity.photoUrl}</dd>
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
