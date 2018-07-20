/* tslint:disable no-unused-expression */
import { browser, protractor } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import RequestComponentsPage from './request.page-object';
import RequestUpdatePage from './request-update.page-object';

const expect = chai.expect;

describe('Request e2e test', () => {
  let navBarPage: NavBarPage;
  let requestUpdatePage: RequestUpdatePage;
  let requestComponentsPage: RequestComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Requests', async () => {
    navBarPage.getEntityPage('request');
    requestComponentsPage = new RequestComponentsPage();
    expect(await requestComponentsPage.getTitle().getText()).to.match(/Requests/);
  });

  it('should load create Request page', async () => {
    requestComponentsPage.clickOnCreateButton();
    requestUpdatePage = new RequestUpdatePage();
    expect(await requestUpdatePage.getPageTitle().getAttribute('id')).to.match(/riverApp.request.home.createOrEditLabel/);
  });

  it('should create and save Requests', async () => {
    requestUpdatePage.setRequestFirstNameInput('requestFirstName');
    expect(await requestUpdatePage.getRequestFirstNameInput()).to.match(/requestFirstName/);
    requestUpdatePage.setRequestLastNameInput('requestLastName');
    expect(await requestUpdatePage.getRequestLastNameInput()).to.match(/requestLastName/);
    requestUpdatePage.setRequestEmailInput('requestEmail');
    expect(await requestUpdatePage.getRequestEmailInput()).to.match(/requestEmail/);
    requestUpdatePage.setRequestPhoneInput('requestPhone');
    expect(await requestUpdatePage.getRequestPhoneInput()).to.match(/requestPhone/);
    const selectedRequestGetAnalysis = await requestUpdatePage.getRequestGetAnalysisInput().isSelected();
    if (selectedRequestGetAnalysis) {
      requestUpdatePage.getRequestGetAnalysisInput().click();
      expect(await requestUpdatePage.getRequestGetAnalysisInput().isSelected()).to.be.false;
    } else {
      requestUpdatePage.getRequestGetAnalysisInput().click();
      expect(await requestUpdatePage.getRequestGetAnalysisInput().isSelected()).to.be.true;
    }
    const selectedRequestGetPrice = await requestUpdatePage.getRequestGetPriceInput().isSelected();
    if (selectedRequestGetPrice) {
      requestUpdatePage.getRequestGetPriceInput().click();
      expect(await requestUpdatePage.getRequestGetPriceInput().isSelected()).to.be.false;
    } else {
      requestUpdatePage.getRequestGetPriceInput().click();
      expect(await requestUpdatePage.getRequestGetPriceInput().isSelected()).to.be.true;
    }
    requestUpdatePage.setRequestPageUrlInput('requestPageUrl');
    expect(await requestUpdatePage.getRequestPageUrlInput()).to.match(/requestPageUrl/);
    requestUpdatePage.setRequestPageNameInput('requestPageName');
    expect(await requestUpdatePage.getRequestPageNameInput()).to.match(/requestPageName/);
    requestUpdatePage.setRequestPropertyIdInput('5');
    expect(await requestUpdatePage.getRequestPropertyIdInput()).to.eq('5');
    requestUpdatePage.requestPropertyTypeSelectLastOption();
    requestUpdatePage.requestTypeSelectLastOption();
    requestUpdatePage.setRequestMeetingDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await requestUpdatePage.getRequestMeetingDateInput()).to.contain('2001-01-01T02:30');
    requestUpdatePage.setRequestQuestionInput('requestQuestion');
    expect(await requestUpdatePage.getRequestQuestionInput()).to.match(/requestQuestion/);
    requestUpdatePage.setRequestPriceInput('5');
    expect(await requestUpdatePage.getRequestPriceInput()).to.eq('5');
    requestUpdatePage.setRequestCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await requestUpdatePage.getRequestCreatedDateInput()).to.contain('2001-01-01T02:30');
    requestUpdatePage.setRequestConsultantIdInput('5');
    expect(await requestUpdatePage.getRequestConsultantIdInput()).to.eq('5');
    requestUpdatePage.userSelectLastOption();
    requestUpdatePage.propertySelectLastOption();
    requestUpdatePage.projectSelectLastOption();
    await requestUpdatePage.save();
    expect(await requestUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
