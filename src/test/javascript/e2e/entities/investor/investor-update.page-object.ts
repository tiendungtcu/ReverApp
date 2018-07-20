import { element, by, ElementFinder } from 'protractor';

export default class InvestorUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.investor.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  investorNameInput: ElementFinder = element(by.css('input#investor-investorName'));
  investorTitleInput: ElementFinder = element(by.css('input#investor-investorTitle'));
  investorDateInput: ElementFinder = element(by.css('input#investor-investorDate'));
  investorDescriptionInput: ElementFinder = element(by.css('input#investor-investorDescription'));
  investorAddressInput: ElementFinder = element(by.css('input#investor-investorAddress'));
  investorWebsiteInput: ElementFinder = element(by.css('input#investor-investorWebsite'));
  investorPhoneInput: ElementFinder = element(by.css('input#investor-investorPhone'));
  investorPhotoInput: ElementFinder = element(by.css('input#file_investorPhoto'));

  getPageTitle() {
    return this.pageTitle;
  }

  setInvestorNameInput(investorName) {
    this.investorNameInput.sendKeys(investorName);
  }

  getInvestorNameInput() {
    return this.investorNameInput.getAttribute('value');
  }

  setInvestorTitleInput(investorTitle) {
    this.investorTitleInput.sendKeys(investorTitle);
  }

  getInvestorTitleInput() {
    return this.investorTitleInput.getAttribute('value');
  }

  setInvestorDateInput(investorDate) {
    this.investorDateInput.sendKeys(investorDate);
  }

  getInvestorDateInput() {
    return this.investorDateInput.getAttribute('value');
  }

  setInvestorDescriptionInput(investorDescription) {
    this.investorDescriptionInput.sendKeys(investorDescription);
  }

  getInvestorDescriptionInput() {
    return this.investorDescriptionInput.getAttribute('value');
  }

  setInvestorAddressInput(investorAddress) {
    this.investorAddressInput.sendKeys(investorAddress);
  }

  getInvestorAddressInput() {
    return this.investorAddressInput.getAttribute('value');
  }

  setInvestorWebsiteInput(investorWebsite) {
    this.investorWebsiteInput.sendKeys(investorWebsite);
  }

  getInvestorWebsiteInput() {
    return this.investorWebsiteInput.getAttribute('value');
  }

  setInvestorPhoneInput(investorPhone) {
    this.investorPhoneInput.sendKeys(investorPhone);
  }

  getInvestorPhoneInput() {
    return this.investorPhoneInput.getAttribute('value');
  }

  setInvestorPhotoInput(investorPhoto) {
    this.investorPhotoInput.sendKeys(investorPhoto);
  }

  getInvestorPhotoInput() {
    return this.investorPhotoInput.getAttribute('value');
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
