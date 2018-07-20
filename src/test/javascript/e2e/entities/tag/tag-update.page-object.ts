import { element, by, ElementFinder } from 'protractor';

export default class TagUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.tag.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  tagNameInput: ElementFinder = element(by.css('input#tag-tagName'));

  getPageTitle() {
    return this.pageTitle;
  }

  setTagNameInput(tagName) {
    this.tagNameInput.sendKeys(tagName);
  }

  getTagNameInput() {
    return this.tagNameInput.getAttribute('value');
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
