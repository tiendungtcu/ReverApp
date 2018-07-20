/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import ContactComponentsPage from './contact.page-object';
import ContactUpdatePage from './contact-update.page-object';
import path from 'path';

const expect = chai.expect;

describe('Contact e2e test', () => {
  let navBarPage: NavBarPage;
  let contactUpdatePage: ContactUpdatePage;
  let contactComponentsPage: ContactComponentsPage;
  const fileToUpload = '../../../../../main/webapp/static/images/logo-jhipster.png';
  const absolutePath = path.resolve(__dirname, fileToUpload);

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Contacts', async () => {
    navBarPage.getEntityPage('contact');
    contactComponentsPage = new ContactComponentsPage();
    expect(await contactComponentsPage.getTitle().getText()).to.match(/Contacts/);
  });

  it('should load create Contact page', async () => {
    contactComponentsPage.clickOnCreateButton();
    contactUpdatePage = new ContactUpdatePage();
    expect(await contactUpdatePage.getPageTitle().getAttribute('id')).to.match(/riverApp.contact.home.createOrEditLabel/);
  });

  it('should create and save Contacts', async () => {
    contactUpdatePage.setContactNameInput('contactName');
    expect(await contactUpdatePage.getContactNameInput()).to.match(/contactName/);
    contactUpdatePage.setContactPhoneInput('contactPhone');
    expect(await contactUpdatePage.getContactPhoneInput()).to.match(/contactPhone/);
    contactUpdatePage.setContactAddressInput('contactAddress');
    expect(await contactUpdatePage.getContactAddressInput()).to.match(/contactAddress/);
    contactUpdatePage.setContactEmailInput('contactEmail');
    expect(await contactUpdatePage.getContactEmailInput()).to.match(/contactEmail/);
    contactUpdatePage.setContactWebsiteInput('contactWebsite');
    expect(await contactUpdatePage.getContactWebsiteInput()).to.match(/contactWebsite/);
    contactUpdatePage.setContactPhotoInput(absolutePath);
    contactUpdatePage.setContactFacebookInput('contactFacebook');
    expect(await contactUpdatePage.getContactFacebookInput()).to.match(/contactFacebook/);
    contactUpdatePage.setContactTwitterInput('contactTwitter');
    expect(await contactUpdatePage.getContactTwitterInput()).to.match(/contactTwitter/);
    contactUpdatePage.setContactInstagramInput('contactInstagram');
    expect(await contactUpdatePage.getContactInstagramInput()).to.match(/contactInstagram/);
    contactUpdatePage.setContactLinkedinInput('contactLinkedin');
    expect(await contactUpdatePage.getContactLinkedinInput()).to.match(/contactLinkedin/);
    contactUpdatePage.setContactGooglePlusInput('contactGooglePlus');
    expect(await contactUpdatePage.getContactGooglePlusInput()).to.match(/contactGooglePlus/);
    contactUpdatePage.setContactYoutubeInput('contactYoutube');
    expect(await contactUpdatePage.getContactYoutubeInput()).to.match(/contactYoutube/);
    const selectedContactStatus = await contactUpdatePage.getContactStatusInput().isSelected();
    if (selectedContactStatus) {
      contactUpdatePage.getContactStatusInput().click();
      expect(await contactUpdatePage.getContactStatusInput().isSelected()).to.be.false;
    } else {
      contactUpdatePage.getContactStatusInput().click();
      expect(await contactUpdatePage.getContactStatusInput().isSelected()).to.be.true;
    }
    await contactUpdatePage.save();
    expect(await contactUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
