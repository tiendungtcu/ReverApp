/* tslint:disable no-unused-expression */
import { browser, protractor } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import ProjectUserComponentsPage from './project-user.page-object';
import ProjectUserUpdatePage from './project-user-update.page-object';

const expect = chai.expect;

describe('ProjectUser e2e test', () => {
  let navBarPage: NavBarPage;
  let projectUserUpdatePage: ProjectUserUpdatePage;
  let projectUserComponentsPage: ProjectUserComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load ProjectUsers', async () => {
    navBarPage.getEntityPage('project-user');
    projectUserComponentsPage = new ProjectUserComponentsPage();
    expect(await projectUserComponentsPage.getTitle().getText()).to.match(/Project Users/);
  });

  it('should load create ProjectUser page', async () => {
    projectUserComponentsPage.clickOnCreateButton();
    projectUserUpdatePage = new ProjectUserUpdatePage();
    expect(await projectUserUpdatePage.getPageTitle().getAttribute('id')).to.match(/riverApp.projectUser.home.createOrEditLabel/);
  });

  it('should create and save ProjectUsers', async () => {
    const selectedLiked = await projectUserUpdatePage.getLikedInput().isSelected();
    if (selectedLiked) {
      projectUserUpdatePage.getLikedInput().click();
      expect(await projectUserUpdatePage.getLikedInput().isSelected()).to.be.false;
    } else {
      projectUserUpdatePage.getLikedInput().click();
      expect(await projectUserUpdatePage.getLikedInput().isSelected()).to.be.true;
    }
    const selectedShared = await projectUserUpdatePage.getSharedInput().isSelected();
    if (selectedShared) {
      projectUserUpdatePage.getSharedInput().click();
      expect(await projectUserUpdatePage.getSharedInput().isSelected()).to.be.false;
    } else {
      projectUserUpdatePage.getSharedInput().click();
      expect(await projectUserUpdatePage.getSharedInput().isSelected()).to.be.true;
    }
    projectUserUpdatePage.setLikedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await projectUserUpdatePage.getLikedDateInput()).to.contain('2001-01-01T02:30');
    projectUserUpdatePage.setSharedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await projectUserUpdatePage.getSharedDateInput()).to.contain('2001-01-01T02:30');
    projectUserUpdatePage.userSelectLastOption();
    projectUserUpdatePage.projectSelectLastOption();
    await projectUserUpdatePage.save();
    expect(await projectUserUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
