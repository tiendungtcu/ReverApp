/* tslint:disable no-unused-expression */
import { browser, protractor } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import ProjectBuilderComponentsPage from './project-builder.page-object';
import ProjectBuilderUpdatePage from './project-builder-update.page-object';
import path from 'path';

const expect = chai.expect;

describe('ProjectBuilder e2e test', () => {
  let navBarPage: NavBarPage;
  let projectBuilderUpdatePage: ProjectBuilderUpdatePage;
  let projectBuilderComponentsPage: ProjectBuilderComponentsPage;
  const fileToUpload = '../../../../../main/webapp/static/images/logo-jhipster.png';
  const absolutePath = path.resolve(__dirname, fileToUpload);

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load ProjectBuilders', async () => {
    navBarPage.getEntityPage('project-builder');
    projectBuilderComponentsPage = new ProjectBuilderComponentsPage();
    expect(await projectBuilderComponentsPage.getTitle().getText()).to.match(/Project Builders/);
  });

  it('should load create ProjectBuilder page', async () => {
    projectBuilderComponentsPage.clickOnCreateButton();
    projectBuilderUpdatePage = new ProjectBuilderUpdatePage();
    expect(await projectBuilderUpdatePage.getPageTitle().getAttribute('id')).to.match(/riverApp.projectBuilder.home.createOrEditLabel/);
  });

  it('should create and save ProjectBuilders', async () => {
    projectBuilderUpdatePage.setBuilderNameInput('builderName');
    expect(await projectBuilderUpdatePage.getBuilderNameInput()).to.match(/builderName/);
    projectBuilderUpdatePage.setBuilderTitleInput('builderTitle');
    expect(await projectBuilderUpdatePage.getBuilderTitleInput()).to.match(/builderTitle/);
    projectBuilderUpdatePage.setBuilderDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await projectBuilderUpdatePage.getBuilderDateInput()).to.contain('2001-01-01T02:30');
    projectBuilderUpdatePage.setBuilderDescriptionInput('builderDescription');
    expect(await projectBuilderUpdatePage.getBuilderDescriptionInput()).to.match(/builderDescription/);
    projectBuilderUpdatePage.setBuilderAddressInput('builderAddress');
    expect(await projectBuilderUpdatePage.getBuilderAddressInput()).to.match(/builderAddress/);
    projectBuilderUpdatePage.setBuilderWebsiteInput('builderWebsite');
    expect(await projectBuilderUpdatePage.getBuilderWebsiteInput()).to.match(/builderWebsite/);
    projectBuilderUpdatePage.setBuilderPhoneInput('builderPhone');
    expect(await projectBuilderUpdatePage.getBuilderPhoneInput()).to.match(/builderPhone/);
    projectBuilderUpdatePage.setBuilderPhotoInput(absolutePath);
    await projectBuilderUpdatePage.save();
    expect(await projectBuilderUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
