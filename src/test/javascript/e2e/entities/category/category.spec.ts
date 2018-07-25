/* tslint:disable no-unused-expression */
import { browser, protractor } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import CategoryComponentsPage from './category.page-object';
import CategoryUpdatePage from './category-update.page-object';

const expect = chai.expect;

describe('Category e2e test', () => {
  let navBarPage: NavBarPage;
  let categoryUpdatePage: CategoryUpdatePage;
  let categoryComponentsPage: CategoryComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Categories', async () => {
    navBarPage.getEntityPage('category');
    categoryComponentsPage = new CategoryComponentsPage();
    expect(await categoryComponentsPage.getTitle().getText()).to.match(/Categories/);
  });

  it('should load create Category page', async () => {
    categoryComponentsPage.clickOnCreateButton();
    categoryUpdatePage = new CategoryUpdatePage();
    expect(await categoryUpdatePage.getPageTitle().getAttribute('id')).to.match(/riverApp.category.home.createOrEditLabel/);
  });

  it('should create and save Categories', async () => {
    categoryUpdatePage.setCategoryNameInput('categoryName');
    expect(await categoryUpdatePage.getCategoryNameInput()).to.match(/categoryName/);
    categoryUpdatePage.setCategoryAliasInput('categoryAlias');
    expect(await categoryUpdatePage.getCategoryAliasInput()).to.match(/categoryAlias/);
    categoryUpdatePage.setCategoryDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await categoryUpdatePage.getCategoryDateInput()).to.contain('2001-01-01T02:30');
    await categoryUpdatePage.save();
    expect(await categoryUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
