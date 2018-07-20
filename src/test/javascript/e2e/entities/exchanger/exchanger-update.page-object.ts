import { element, by, ElementFinder } from 'protractor';

export default class ExchangerUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.exchanger.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  exchangerNameInput: ElementFinder = element(by.css('input#exchanger-exchangerName'));
  exchangerAddressInput: ElementFinder = element(by.css('input#exchanger-exchangerAddress'));
  exchangerPhoneInput: ElementFinder = element(by.css('input#exchanger-exchangerPhone'));
  exchangerPhotoInput: ElementFinder = element(by.css('input#file_exchangerPhoto'));

  getPageTitle() {
    return this.pageTitle;
  }

  setExchangerNameInput(exchangerName) {
    this.exchangerNameInput.sendKeys(exchangerName);
  }

  getExchangerNameInput() {
    return this.exchangerNameInput.getAttribute('value');
  }

  setExchangerAddressInput(exchangerAddress) {
    this.exchangerAddressInput.sendKeys(exchangerAddress);
  }

  getExchangerAddressInput() {
    return this.exchangerAddressInput.getAttribute('value');
  }

  setExchangerPhoneInput(exchangerPhone) {
    this.exchangerPhoneInput.sendKeys(exchangerPhone);
  }

  getExchangerPhoneInput() {
    return this.exchangerPhoneInput.getAttribute('value');
  }

  setExchangerPhotoInput(exchangerPhoto) {
    this.exchangerPhotoInput.sendKeys(exchangerPhoto);
  }

  getExchangerPhotoInput() {
    return this.exchangerPhotoInput.getAttribute('value');
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
