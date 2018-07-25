/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import InvestorComponentsPage from './investor.page-object';
import InvestorUpdatePage from './investor-update.page-object';

const expect = chai.expect;

describe('Investor e2e test', () => {
  let navBarPage: NavBarPage;
  let investorUpdatePage: InvestorUpdatePage;
  let investorComponentsPage: InvestorComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Investors', async () => {
    navBarPage.getEntityPage('investor');
    investorComponentsPage = new InvestorComponentsPage();
    expect(await investorComponentsPage.getTitle().getText()).to.match(/Investors/);
  });

  it('should load create Investor page', async () => {
    investorComponentsPage.clickOnCreateButton();
    investorUpdatePage = new InvestorUpdatePage();
    expect(await investorUpdatePage.getPageTitle().getAttribute('id')).to.match(/riverApp.investor.home.createOrEditLabel/);
  });

  it('should create and save Investors', async () => {
    investorUpdatePage.setInvestorNameInput('investorName');
    expect(await investorUpdatePage.getInvestorNameInput()).to.match(/investorName/);
    investorUpdatePage.setInvestorTitleInput('investorTitle');
    expect(await investorUpdatePage.getInvestorTitleInput()).to.match(/investorTitle/);
    investorUpdatePage.setInvestorDateInput('01-01-2001');
    expect(await investorUpdatePage.getInvestorDateInput()).to.eq('2001-01-01');
    investorUpdatePage.setInvestorDescriptionInput('investorDescription');
    expect(await investorUpdatePage.getInvestorDescriptionInput()).to.match(/investorDescription/);
    investorUpdatePage.setInvestorAddressInput('investorAddress');
    expect(await investorUpdatePage.getInvestorAddressInput()).to.match(/investorAddress/);
    investorUpdatePage.setInvestorWebsiteInput('investorWebsite');
    expect(await investorUpdatePage.getInvestorWebsiteInput()).to.match(/investorWebsite/);
    investorUpdatePage.setInvestorPhoneInput('investorPhone');
    expect(await investorUpdatePage.getInvestorPhoneInput()).to.match(/investorPhone/);
    investorUpdatePage.setInvestorAvatarUrlInput('investorAvatarUrl');
    expect(await investorUpdatePage.getInvestorAvatarUrlInput()).to.match(/investorAvatarUrl/);
    await investorUpdatePage.save();
    expect(await investorUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
