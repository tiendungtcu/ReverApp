/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import CompanyComponentsPage from './company.page-object';
import CompanyUpdatePage from './company-update.page-object';
import path from 'path';

const expect = chai.expect;

describe('Company e2e test', () => {
  let navBarPage: NavBarPage;
  let companyUpdatePage: CompanyUpdatePage;
  let companyComponentsPage: CompanyComponentsPage;
  const fileToUpload = '../../../../../main/webapp/static/images/logo-jhipster.png';
  const absolutePath = path.resolve(__dirname, fileToUpload);

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Companies', async () => {
    navBarPage.getEntityPage('company');
    companyComponentsPage = new CompanyComponentsPage();
    expect(await companyComponentsPage.getTitle().getText()).to.match(/Companies/);
  });

  it('should load create Company page', async () => {
    companyComponentsPage.clickOnCreateButton();
    companyUpdatePage = new CompanyUpdatePage();
    expect(await companyUpdatePage.getPageTitle().getAttribute('id')).to.match(/riverApp.company.home.createOrEditLabel/);
  });

  it('should create and save Companies', async () => {
    companyUpdatePage.setCompanyNameInput('companyName');
    expect(await companyUpdatePage.getCompanyNameInput()).to.match(/companyName/);
    companyUpdatePage.setCompanyPhoneInput('companyPhone');
    expect(await companyUpdatePage.getCompanyPhoneInput()).to.match(/companyPhone/);
    companyUpdatePage.setCompanyAddressInput('companyAddress');
    expect(await companyUpdatePage.getCompanyAddressInput()).to.match(/companyAddress/);
    companyUpdatePage.setCompanyLogoInput(absolutePath);
    companyUpdatePage.setCompanyWebsiteInput('companyWebsite');
    expect(await companyUpdatePage.getCompanyWebsiteInput()).to.match(/companyWebsite/);
    companyUpdatePage.setCompanyFacebookInput('companyFacebook');
    expect(await companyUpdatePage.getCompanyFacebookInput()).to.match(/companyFacebook/);
    companyUpdatePage.setCompanyTwitterInput('companyTwitter');
    expect(await companyUpdatePage.getCompanyTwitterInput()).to.match(/companyTwitter/);
    companyUpdatePage.setCompanyInstagramInput('companyInstagram');
    expect(await companyUpdatePage.getCompanyInstagramInput()).to.match(/companyInstagram/);
    companyUpdatePage.setCompanyLinkedinInput('companyLinkedin');
    expect(await companyUpdatePage.getCompanyLinkedinInput()).to.match(/companyLinkedin/);
    companyUpdatePage.setCompanyGooglePlusInput('companyGooglePlus');
    expect(await companyUpdatePage.getCompanyGooglePlusInput()).to.match(/companyGooglePlus/);
    companyUpdatePage.setCompanyYoutubeInput('companyYoutube');
    expect(await companyUpdatePage.getCompanyYoutubeInput()).to.match(/companyYoutube/);
    companyUpdatePage.setCompanyDescriptionInput('companyDescription');
    expect(await companyUpdatePage.getCompanyDescriptionInput()).to.match(/companyDescription/);
    await companyUpdatePage.save();
    expect(await companyUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
