import { element, by, ElementFinder } from 'protractor';

export default class CompanyUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.company.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  companyNameInput: ElementFinder = element(by.css('input#company-companyName'));
  companyPhoneInput: ElementFinder = element(by.css('input#company-companyPhone'));
  companyAddressInput: ElementFinder = element(by.css('input#company-companyAddress'));
  companyLogoInput: ElementFinder = element(by.css('input#file_companyLogo'));
  companyWebsiteInput: ElementFinder = element(by.css('input#company-companyWebsite'));
  companyFacebookInput: ElementFinder = element(by.css('input#company-companyFacebook'));
  companyTwitterInput: ElementFinder = element(by.css('input#company-companyTwitter'));
  companyInstagramInput: ElementFinder = element(by.css('input#company-companyInstagram'));
  companyLinkedinInput: ElementFinder = element(by.css('input#company-companyLinkedin'));
  companyGooglePlusInput: ElementFinder = element(by.css('input#company-companyGooglePlus'));
  companyYoutubeInput: ElementFinder = element(by.css('input#company-companyYoutube'));
  companyDescriptionInput: ElementFinder = element(by.css('input#company-companyDescription'));

  getPageTitle() {
    return this.pageTitle;
  }

  setCompanyNameInput(companyName) {
    this.companyNameInput.sendKeys(companyName);
  }

  getCompanyNameInput() {
    return this.companyNameInput.getAttribute('value');
  }

  setCompanyPhoneInput(companyPhone) {
    this.companyPhoneInput.sendKeys(companyPhone);
  }

  getCompanyPhoneInput() {
    return this.companyPhoneInput.getAttribute('value');
  }

  setCompanyAddressInput(companyAddress) {
    this.companyAddressInput.sendKeys(companyAddress);
  }

  getCompanyAddressInput() {
    return this.companyAddressInput.getAttribute('value');
  }

  setCompanyLogoInput(companyLogo) {
    this.companyLogoInput.sendKeys(companyLogo);
  }

  getCompanyLogoInput() {
    return this.companyLogoInput.getAttribute('value');
  }

  setCompanyWebsiteInput(companyWebsite) {
    this.companyWebsiteInput.sendKeys(companyWebsite);
  }

  getCompanyWebsiteInput() {
    return this.companyWebsiteInput.getAttribute('value');
  }

  setCompanyFacebookInput(companyFacebook) {
    this.companyFacebookInput.sendKeys(companyFacebook);
  }

  getCompanyFacebookInput() {
    return this.companyFacebookInput.getAttribute('value');
  }

  setCompanyTwitterInput(companyTwitter) {
    this.companyTwitterInput.sendKeys(companyTwitter);
  }

  getCompanyTwitterInput() {
    return this.companyTwitterInput.getAttribute('value');
  }

  setCompanyInstagramInput(companyInstagram) {
    this.companyInstagramInput.sendKeys(companyInstagram);
  }

  getCompanyInstagramInput() {
    return this.companyInstagramInput.getAttribute('value');
  }

  setCompanyLinkedinInput(companyLinkedin) {
    this.companyLinkedinInput.sendKeys(companyLinkedin);
  }

  getCompanyLinkedinInput() {
    return this.companyLinkedinInput.getAttribute('value');
  }

  setCompanyGooglePlusInput(companyGooglePlus) {
    this.companyGooglePlusInput.sendKeys(companyGooglePlus);
  }

  getCompanyGooglePlusInput() {
    return this.companyGooglePlusInput.getAttribute('value');
  }

  setCompanyYoutubeInput(companyYoutube) {
    this.companyYoutubeInput.sendKeys(companyYoutube);
  }

  getCompanyYoutubeInput() {
    return this.companyYoutubeInput.getAttribute('value');
  }

  setCompanyDescriptionInput(companyDescription) {
    this.companyDescriptionInput.sendKeys(companyDescription);
  }

  getCompanyDescriptionInput() {
    return this.companyDescriptionInput.getAttribute('value');
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
