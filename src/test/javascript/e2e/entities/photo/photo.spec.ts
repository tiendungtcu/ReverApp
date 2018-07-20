/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import PhotoComponentsPage from './photo.page-object';
import PhotoUpdatePage from './photo-update.page-object';
import path from 'path';

const expect = chai.expect;

describe('Photo e2e test', () => {
  let navBarPage: NavBarPage;
  let photoUpdatePage: PhotoUpdatePage;
  let photoComponentsPage: PhotoComponentsPage;
  const fileToUpload = '../../../../../main/webapp/static/images/logo-jhipster.png';
  const absolutePath = path.resolve(__dirname, fileToUpload);

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
    photoUpdatePage.setPhotoImageInput(absolutePath);
    photoUpdatePage.setPhotoExtensionInput('photoExtension');
    expect(await photoUpdatePage.getPhotoExtensionInput()).to.match(/photoExtension/);
    photoUpdatePage.setPhotoUrlInput('photoUrl');
    expect(await photoUpdatePage.getPhotoUrlInput()).to.match(/photoUrl/);
    photoUpdatePage.setPhotoThumbnailUrlInput('photoThumbnailUrl');
    expect(await photoUpdatePage.getPhotoThumbnailUrlInput()).to.match(/photoThumbnailUrl/);
    await photoUpdatePage.save();
    expect(await photoUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
