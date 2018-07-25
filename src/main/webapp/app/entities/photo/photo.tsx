import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './photo.reducer';
import { IPhoto } from 'app/shared/model/photo.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPhotoProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Photo extends React.Component<IPhotoProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { photoList, match } = this.props;
    return (
      <div>
        <h2 id="photo-heading">
          <Translate contentKey="riverApp.photo.home.title">Photos</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="riverApp.photo.home.createLabel">Create new Photo</Translate>
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
                  <Translate contentKey="riverApp.photo.photoName">Photo Name</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.photo.photoDate">Photo Date</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.photo.photoUrl">Photo Url</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.photo.photoMimeType">Photo Mime Type</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.photo.resourceId">Resource Id</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.photo.resourceType">Resource Type</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.photo.photoSize">Photo Size</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.photo.photoAltText">Photo Alt Text</Translate>
                </th>
                <th>
                  <Translate contentKey="riverApp.photo.photoThumbnailUrl">Photo Thumbnail Url</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {photoList.map((photo, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${photo.id}`} color="link" size="sm">
                      {photo.id}
                    </Button>
                  </td>
                  <td>{photo.photoName}</td>
                  <td>
                    <TextFormat type="date" value={photo.photoDate} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{photo.photoUrl}</td>
                  <td>{photo.photoMimeType}</td>
                  <td>{photo.resourceId}</td>
                  <td>{photo.resourceType}</td>
                  <td>{photo.photoSize}</td>
                  <td>{photo.photoAltText}</td>
                  <td>{photo.photoThumbnailUrl}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${photo.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${photo.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${photo.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ photo }: IRootState) => ({
  photoList: photo.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Photo);
