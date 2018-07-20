/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SupportCategoryComponentsPage from './support-category.page-object';
import SupportCategoryUpdatePage from './support-category-update.page-object';

const expect = chai.expect;

describe('SupportCategory e2e test', () => {
  let navBarPage: NavBarPage;
  let supportCategoryUpdatePage: SupportCategoryUpdatePage;
  let supportCategoryComponentsPage: SupportCategoryComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load SupportCategories', async () => {
    navBarPage.getEntityPage('support-category');
    supportCategoryComponentsPage = new SupportCategoryComponentsPage();
    expect(await supportCategoryComponentsPage.getTitle().getText()).to.match(/Support Categories/);
  });

  it('should load create SupportCategory page', async () => {
    supportCategoryComponentsPage.clickOnCreateButton();
    supportCategoryUpdatePage = new SupportCategoryUpdatePage();
    expect(await supportCategoryUpdatePage.getPageTitle().getAttribute('id')).to.match(/riverApp.supportCategory.home.createOrEditLabel/);
  });

  it('should create and save SupportCategories', async () => {
    supportCategoryUpdatePage.setCategoryNameInput('categoryName');
    expect(await supportCategoryUpdatePage.getCategoryNameInput()).to.match(/categoryName/);
    supportCategoryUpdatePage.setCategoryDescriptionInput('categoryDescription');
    expect(await supportCategoryUpdatePage.getCategoryDescriptionInput()).to.match(/categoryDescription/);
    await supportCategoryUpdatePage.save();
    expect(await supportCategoryUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
