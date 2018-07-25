/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import ResidentialAreaComponentsPage from './residential-area.page-object';
import ResidentialAreaUpdatePage from './residential-area-update.page-object';

const expect = chai.expect;

describe('ResidentialArea e2e test', () => {
  let navBarPage: NavBarPage;
  let residentialAreaUpdatePage: ResidentialAreaUpdatePage;
  let residentialAreaComponentsPage: ResidentialAreaComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load ResidentialAreas', async () => {
    navBarPage.getEntityPage('residential-area');
    residentialAreaComponentsPage = new ResidentialAreaComponentsPage();
    expect(await residentialAreaComponentsPage.getTitle().getText()).to.match(/Residential Areas/);
  });

  it('should load create ResidentialArea page', async () => {
    residentialAreaComponentsPage.clickOnCreateButton();
    residentialAreaUpdatePage = new ResidentialAreaUpdatePage();
    expect(await residentialAreaUpdatePage.getPageTitle().getAttribute('id')).to.match(/riverApp.residentialArea.home.createOrEditLabel/);
  });

  it('should create and save ResidentialAreas', async () => {
    residentialAreaUpdatePage.setResidentialNameInput('residentialName');
    expect(await residentialAreaUpdatePage.getResidentialNameInput()).to.match(/residentialName/);
    residentialAreaUpdatePage.setResidentialAliasInput('residentialAlias');
    expect(await residentialAreaUpdatePage.getResidentialAliasInput()).to.match(/residentialAlias/);
    residentialAreaUpdatePage.setResidentialDescriptionInput('residentialDescription');
    expect(await residentialAreaUpdatePage.getResidentialDescriptionInput()).to.match(/residentialDescription/);
    residentialAreaUpdatePage.setResidentialDetailInput('residentialDetail');
    expect(await residentialAreaUpdatePage.getResidentialDetailInput()).to.match(/residentialDetail/);
    residentialAreaUpdatePage.setResidentialProvinceInput('residentialProvince');
    expect(await residentialAreaUpdatePage.getResidentialProvinceInput()).to.match(/residentialProvince/);
    residentialAreaUpdatePage.setResidentialDistrictInput('residentialDistrict');
    expect(await residentialAreaUpdatePage.getResidentialDistrictInput()).to.match(/residentialDistrict/);
    residentialAreaUpdatePage.setResidentialBoundaryInput('residentialBoundary');
    expect(await residentialAreaUpdatePage.getResidentialBoundaryInput()).to.match(/residentialBoundary/);
    residentialAreaUpdatePage.setResidentialAvatarInput('residentialAvatar');
    expect(await residentialAreaUpdatePage.getResidentialAvatarInput()).to.match(/residentialAvatar/);
    // residentialAreaUpdatePage.tagSelectLastOption();
    await residentialAreaUpdatePage.save();
    expect(await residentialAreaUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
