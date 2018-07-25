/* tslint:disable no-unused-expression */
import { browser, protractor } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import PhotoComponentsPage from './photo.page-object';
import PhotoUpdatePage from './photo-update.page-object';

const expect = chai.expect;

describe('Photo e2e test', () => {
  let navBarPage: NavBarPage;
  let photoUpdatePage: PhotoUpdatePage;
  let photoComponentsPage: PhotoComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Photos', async () => {
    navBarPage.getEntityPage('photo');
    photoComponentsPage = new PhotoComponentsPage();
    expect(await photoComponentsPage.getTitle().getText()).to.match(/Photos/);
  });

  it('should load create Photo page', async () => {
    photoComponentsPage.clickOnCreateButton();
    photoUpdatePage = new PhotoUpdatePage();
    expect(await photoUpdatePage.getPageTitle().getAttribute('id')).to.match(/riverApp.photo.home.createOrEditLabel/);
  });

  it('should create and save Photos', async () => {
    photoUpdatePage.setPhotoNameInput('photoName');
    expect(await photoUpdatePage.getPhotoNameInput()).to.match(/photoName/);
    photoUpdatePage.setPhotoDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await photoUpdatePage.getPhotoDateInput()).to.contain('2001-01-01T02:30');
    photoUpdatePage.setPhotoUrlInput('photoUrl');
    expect(await photoUpdatePage.getPhotoUrlInput()).to.match(/photoUrl/);
    photoUpdatePage.setPhotoMimeTypeInput('photoMimeType');
    expect(await photoUpdatePage.getPhotoMimeTypeInput()).to.match(/photoMimeType/);
    photoUpdatePage.setResourceIdInput('5');
    expect(await photoUpdatePage.getResourceIdInput()).to.eq('5');
    photoUpdatePage.resourceTypeSelectLastOption();
    photoUpdatePage.setPhotoSizeInput('5');
    expect(await photoUpdatePage.getPhotoSizeInput()).to.eq('5');
    photoUpdatePage.setPhotoAltTextInput('photoAltText');
    expect(await photoUpdatePage.getPhotoAltTextInput()).to.match(/photoAltText/);
    photoUpdatePage.setPhotoThumbnailUrlInput('photoThumbnailUrl');
    expect(await photoUpdatePage.getPhotoThumbnailUrlInput()).to.match(/photoThumbnailUrl/);
    await photoUpdatePage.save();
    expect(await photoUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
