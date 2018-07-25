/* tslint:disable no-unused-expression */
import { browser, protractor } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import DocumentComponentsPage from './document.page-object';
import DocumentUpdatePage from './document-update.page-object';

const expect = chai.expect;

describe('Document e2e test', () => {
  let navBarPage: NavBarPage;
  let documentUpdatePage: DocumentUpdatePage;
  let documentComponentsPage: DocumentComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Documents', async () => {
    navBarPage.getEntityPage('document');
    documentComponentsPage = new DocumentComponentsPage();
    expect(await documentComponentsPage.getTitle().getText()).to.match(/Documents/);
  });

  it('should load create Document page', async () => {
    documentComponentsPage.clickOnCreateButton();
    documentUpdatePage = new DocumentUpdatePage();
    expect(await documentUpdatePage.getPageTitle().getAttribute('id')).to.match(/riverApp.document.home.createOrEditLabel/);
  });

  it('should create and save Documents', async () => {
    documentUpdatePage.setDocumentNameInput('documentName');
    expect(await documentUpdatePage.getDocumentNameInput()).to.match(/documentName/);
    documentUpdatePage.setDocumentUrlInput('documentUrl');
    expect(await documentUpdatePage.getDocumentUrlInput()).to.match(/documentUrl/);
    documentUpdatePage.setDocumentDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await documentUpdatePage.getDocumentDateInput()).to.contain('2001-01-01T02:30');
    documentUpdatePage.setDocumentMimeTypeInput('documentMimeType');
    expect(await documentUpdatePage.getDocumentMimeTypeInput()).to.match(/documentMimeType/);
    documentUpdatePage.setDocumentSizeInput('documentSize');
    expect(await documentUpdatePage.getDocumentSizeInput()).to.match(/documentSize/);
    documentUpdatePage.resourceTypeSelectLastOption();
    documentUpdatePage.setResourceIdInput('5');
    expect(await documentUpdatePage.getResourceIdInput()).to.eq('5');
    await documentUpdatePage.save();
    expect(await documentUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
