import { element, by, ElementFinder } from 'protractor';

export default class BuildingTypeUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.buildingType.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  typeNameInput: ElementFinder = element(by.css('input#building-type-typeName'));

  getPageTitle() {
    return this.pageTitle;
  }

  setTypeNameInput(typeName) {
    this.typeNameInput.sendKeys(typeName);
  }

  getTypeNameInput() {
    return this.typeNameInput.getAttribute('value');
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
