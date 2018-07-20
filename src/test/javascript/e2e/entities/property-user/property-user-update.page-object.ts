import { element, by, ElementFinder } from 'protractor';

export default class PropertyUserUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.propertyUser.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  likedInput: ElementFinder = element(by.css('input#property-user-liked'));
  sharedInput: ElementFinder = element(by.css('input#property-user-shared'));
  likedDateInput: ElementFinder = element(by.css('input#property-user-likedDate'));
  sharedDateInput: ElementFinder = element(by.css('input#property-user-sharedDate'));
  userSelect: ElementFinder = element(by.css('select#property-user-user'));
  propertySelect: ElementFinder = element(by.css('select#property-user-property'));

  getPageTitle() {
    return this.pageTitle;
  }

  getLikedInput() {
    return this.likedInput;
  }
  getSharedInput() {
    return this.sharedInput;
  }
  setLikedDateInput(likedDate) {
    this.likedDateInput.sendKeys(likedDate);
  }

  getLikedDateInput() {
    return this.likedDateInput.getAttribute('value');
  }

  setSharedDateInput(sharedDate) {
    this.sharedDateInput.sendKeys(sharedDate);
  }

  getSharedDateInput() {
    return this.sharedDateInput.getAttribute('value');
  }

  userSelectLastOption() {
    this.userSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  userSelectOption(option) {
    this.userSelect.sendKeys(option);
  }

  getUserSelect() {
    return this.userSelect;
  }

  getUserSelectedOption() {
    return this.userSelect.element(by.css('option:checked')).getText();
  }

  propertySelectLastOption() {
    this.propertySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  propertySelectOption(option) {
    this.propertySelect.sendKeys(option);
  }

  getPropertySelect() {
    return this.propertySelect;
  }

  getPropertySelectedOption() {
    return this.propertySelect.element(by.css('option:checked')).getText();
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
