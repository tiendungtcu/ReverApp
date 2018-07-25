/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import ExchangerComponentsPage from './exchanger.page-object';
import ExchangerUpdatePage from './exchanger-update.page-object';

const expect = chai.expect;

describe('Exchanger e2e test', () => {
  let navBarPage: NavBarPage;
  let exchangerUpdatePage: ExchangerUpdatePage;
  let exchangerComponentsPage: ExchangerComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Exchangers', async () => {
    navBarPage.getEntityPage('exchanger');
    exchangerComponentsPage = new ExchangerComponentsPage();
    expect(await exchangerComponentsPage.getTitle().getText()).to.match(/Exchangers/);
  });

  it('should load create Exchanger page', async () => {
    exchangerComponentsPage.clickOnCreateButton();
    exchangerUpdatePage = new ExchangerUpdatePage();
    expect(await exchangerUpdatePage.getPageTitle().getAttribute('id')).to.match(/riverApp.exchanger.home.createOrEditLabel/);
  });

  it('should create and save Exchangers', async () => {
    exchangerUpdatePage.setExchangerNameInput('exchangerName');
    expect(await exchangerUpdatePage.getExchangerNameInput()).to.match(/exchangerName/);
    exchangerUpdatePage.setExchangerAddressInput('exchangerAddress');
    expect(await exchangerUpdatePage.getExchangerAddressInput()).to.match(/exchangerAddress/);
    exchangerUpdatePage.setExchangerPhoneInput('exchangerPhone');
    expect(await exchangerUpdatePage.getExchangerPhoneInput()).to.match(/exchangerPhone/);
    exchangerUpdatePage.setExchangerAvatarUrlInput('exchangerAvatarUrl');
    expect(await exchangerUpdatePage.getExchangerAvatarUrlInput()).to.match(/exchangerAvatarUrl/);
    await exchangerUpdatePage.save();
    expect(await exchangerUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
