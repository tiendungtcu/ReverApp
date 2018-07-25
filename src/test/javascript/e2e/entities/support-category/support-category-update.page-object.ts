import { element, by, ElementFinder } from 'protractor';

export default class SupportCategoryUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.supportCategory.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  categoryNameInput: ElementFinder = element(by.css('input#support-category-categoryName'));
  categoryDescriptionInput: ElementFinder = element(by.css('input#support-category-categoryDescription'));
  categorySupportTypeSelect: ElementFinder = element(by.css('select#support-category-categorySupportType'));

  getPageTitle() {
    return this.pageTitle;
  }

  setCategoryNameInput(categoryName) {
    this.categoryNameInput.sendKeys(categoryName);
  }

  getCategoryNameInput() {
    return this.categoryNameInput.getAttribute('value');
  }

  setCategoryDescriptionInput(categoryDescription) {
    this.categoryDescriptionInput.sendKeys(categoryDescription);
  }

  getCategoryDescriptionInput() {
    return this.categoryDescriptionInput.getAttribute('value');
  }

  setCategorySupportTypeSelect(categorySupportType) {
    this.categorySupportTypeSelect.sendKeys(categorySupportType);
  }

  getCategorySupportTypeSelect() {
    return this.categorySupportTypeSelect.element(by.css('option:checked')).getText();
  }

  categorySupportTypeSelectLastOption() {
    this.categorySupportTypeSelect
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
