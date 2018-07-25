/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import BuildingTypeComponentsPage from './building-type.page-object';
import BuildingTypeUpdatePage from './building-type-update.page-object';

const expect = chai.expect;

describe('BuildingType e2e test', () => {
  let navBarPage: NavBarPage;
  let buildingTypeUpdatePage: BuildingTypeUpdatePage;
  let buildingTypeComponentsPage: BuildingTypeComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load BuildingTypes', async () => {
    navBarPage.getEntityPage('building-type');
    buildingTypeComponentsPage = new BuildingTypeComponentsPage();
    expect(await buildingTypeComponentsPage.getTitle().getText()).to.match(/Building Types/);
  });

  it('should load create BuildingType page', async () => {
    buildingTypeComponentsPage.clickOnCreateButton();
    buildingTypeUpdatePage = new BuildingTypeUpdatePage();
    expect(await buildingTypeUpdatePage.getPageTitle().getAttribute('id')).to.match(/riverApp.buildingType.home.createOrEditLabel/);
  });

  it('should create and save BuildingTypes', async () => {
    buildingTypeUpdatePage.setTypeNameInput('typeName');
    expect(await buildingTypeUpdatePage.getTypeNameInput()).to.match(/typeName/);
    await buildingTypeUpdatePage.save();
    expect(await buildingTypeUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
