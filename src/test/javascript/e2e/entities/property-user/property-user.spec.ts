/* tslint:disable no-unused-expression */
import { browser, protractor } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import PropertyUserComponentsPage from './property-user.page-object';
import PropertyUserUpdatePage from './property-user-update.page-object';

const expect = chai.expect;

describe('PropertyUser e2e test', () => {
  let navBarPage: NavBarPage;
  let propertyUserUpdatePage: PropertyUserUpdatePage;
  let propertyUserComponentsPage: PropertyUserComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load PropertyUsers', async () => {
    navBarPage.getEntityPage('property-user');
    propertyUserComponentsPage = new PropertyUserComponentsPage();
    expect(await propertyUserComponentsPage.getTitle().getText()).to.match(/Property Users/);
  });

  it('should load create PropertyUser page', async () => {
    propertyUserComponentsPage.clickOnCreateButton();
    propertyUserUpdatePage = new PropertyUserUpdatePage();
    expect(await propertyUserUpdatePage.getPageTitle().getAttribute('id')).to.match(/riverApp.propertyUser.home.createOrEditLabel/);
  });

  it('should create and save PropertyUsers', async () => {
    const selectedLiked = await propertyUserUpdatePage.getLikedInput().isSelected();
    if (selectedLiked) {
      propertyUserUpdatePage.getLikedInput().click();
      expect(await propertyUserUpdatePage.getLikedInput().isSelected()).to.be.false;
    } else {
      propertyUserUpdatePage.getLikedInput().click();
      expect(await propertyUserUpdatePage.getLikedInput().isSelected()).to.be.true;
    }
    const selectedShared = await propertyUserUpdatePage.getSharedInput().isSelected();
    if (selectedShared) {
      propertyUserUpdatePage.getSharedInput().click();
      expect(await propertyUserUpdatePage.getSharedInput().isSelected()).to.be.false;
    } else {
      propertyUserUpdatePage.getSharedInput().click();
      expect(await propertyUserUpdatePage.getSharedInput().isSelected()).to.be.true;
    }
    propertyUserUpdatePage.setLikedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await propertyUserUpdatePage.getLikedDateInput()).to.contain('2001-01-01T02:30');
    propertyUserUpdatePage.setSharedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await propertyUserUpdatePage.getSharedDateInput()).to.contain('2001-01-01T02:30');
    propertyUserUpdatePage.userSelectLastOption();
    propertyUserUpdatePage.propertySelectLastOption();
    await propertyUserUpdatePage.save();
    expect(await propertyUserUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
