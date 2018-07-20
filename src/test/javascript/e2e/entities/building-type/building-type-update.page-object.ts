import { element, by, ElementFinder } from 'protractor';

export default class BuildingTypeUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.buildingType.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  typeNameInput: ElementFinder = element(by.css('input#building-type-typeName'));
  typeSelectSelect: ElementFinder = element(by.css('select#building-type-typeSelect'));

  getPageTitle() {
    return this.pageTitle;
  }

  setTypeNameInput(typeName) {
    this.typeNameInput.sendKeys(typeName);
  }

  getTypeNameInput() {
    return this.typeNameInput.getAttribute('value');
  }

  setTypeSelectSelect(typeSelect) {
    this.typeSelectSelect.sendKeys(typeSelect);
  }

  getTypeSelectSelect() {
    return this.typeSelectSelect.element(by.css('option:checked')).getText();
  }

  typeSelectSelectLastOption() {
    this.typeSelectSelect
      .all(by.tagName('option'))
      .last()
      .click();
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
