import { element, by, ElementFinder } from 'protractor';

export default class ContactUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.contact.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  contactNameInput: ElementFinder = element(by.css('input#contact-contactName'));
  contactPhoneInput: ElementFinder = element(by.css('input#contact-contactPhone'));
  contactAddressInput: ElementFinder = element(by.css('input#contact-contactAddress'));
  contactEmailInput: ElementFinder = element(by.css('input#contact-contactEmail'));
  contactWebsiteInput: ElementFinder = element(by.css('input#contact-contactWebsite'));
  contactPhotoInput: ElementFinder = element(by.css('input#file_contactPhoto'));
  contactFacebookInput: ElementFinder = element(by.css('input#contact-contactFacebook'));
  contactTwitterInput: ElementFinder = element(by.css('input#contact-contactTwitter'));
  contactInstagramInput: ElementFinder = element(by.css('input#contact-contactInstagram'));
  contactLinkedinInput: ElementFinder = element(by.css('input#contact-contactLinkedin'));
  contactGooglePlusInput: ElementFinder = element(by.css('input#contact-contactGooglePlus'));
  contactYoutubeInput: ElementFinder = element(by.css('input#contact-contactYoutube'));
  contactStatusInput: ElementFinder = element(by.css('input#contact-contactStatus'));

  getPageTitle() {
    return this.pageTitle;
  }

  setContactNameInput(contactName) {
    this.contactNameInput.sendKeys(contactName);
  }

  getContactNameInput() {
    return this.contactNameInput.getAttribute('value');
  }

  setContactPhoneInput(contactPhone) {
    this.contactPhoneInput.sendKeys(contactPhone);
  }

  getContactPhoneInput() {
    return this.contactPhoneInput.getAttribute('value');
  }

  setContactAddressInput(contactAddress) {
    this.contactAddressInput.sendKeys(contactAddress);
  }

  getContactAddressInput() {
    return this.contactAddressInput.getAttribute('value');
  }

  setContactEmailInput(contactEmail) {
    this.contactEmailInput.sendKeys(contactEmail);
  }

  getContactEmailInput() {
    return this.contactEmailInput.getAttribute('value');
  }

  setContactWebsiteInput(contactWebsite) {
    this.contactWebsiteInput.sendKeys(contactWebsite);
  }

  getContactWebsiteInput() {
    return this.contactWebsiteInput.getAttribute('value');
  }

  setContactPhotoInput(contactPhoto) {
    this.contactPhotoInput.sendKeys(contactPhoto);
  }

  getContactPhotoInput() {
    return this.contactPhotoInput.getAttribute('value');
  }

  setContactFacebookInput(contactFacebook) {
    this.contactFacebookInput.sendKeys(contactFacebook);
  }

  getContactFacebookInput() {
    return this.contactFacebookInput.getAttribute('value');
  }

  setContactTwitterInput(contactTwitter) {
    this.contactTwitterInput.sendKeys(contactTwitter);
  }

  getContactTwitterInput() {
    return this.contactTwitterInput.getAttribute('value');
  }

  setContactInstagramInput(contactInstagram) {
    this.contactInstagramInput.sendKeys(contactInstagram);
  }

  getContactInstagramInput() {
    return this.contactInstagramInput.getAttribute('value');
  }

  setContactLinkedinInput(contactLinkedin) {
    this.contactLinkedinInput.sendKeys(contactLinkedin);
  }

  getContactLinkedinInput() {
    return this.contactLinkedinInput.getAttribute('value');
  }

  setContactGooglePlusInput(contactGooglePlus) {
    this.contactGooglePlusInput.sendKeys(contactGooglePlus);
  }

  getContactGooglePlusInput() {
    return this.contactGooglePlusInput.getAttribute('value');
  }

  setContactYoutubeInput(contactYoutube) {
    this.contactYoutubeInput.sendKeys(contactYoutube);
  }

  getContactYoutubeInput() {
    return this.contactYoutubeInput.getAttribute('value');
  }

  getContactStatusInput() {
    return this.contactStatusInput;
  }
  save() {
    return this.saveButton.click();
  }

  cancel() {
    this.cancelButton.click();
  }

  getSaveButton() {
    return this.saveButton;
  }
}
