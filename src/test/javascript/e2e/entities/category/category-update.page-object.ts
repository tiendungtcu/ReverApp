import { element, by, ElementFinder } from 'protractor';

export default class CategoryUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.category.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  categoryNameInput: ElementFinder = element(by.css('input#category-categoryName'));
  categoryAliasInput: ElementFinder = element(by.css('input#category-categoryAlias'));
  categoryDescriptionInput: ElementFinder = element(by.css('input#category-categoryDescription'));

  getPageTitle() {
    return this.pageTitle;
  }

  setCategoryNameInput(categoryName) {
    this.categoryNameInput.sendKeys(categoryName);
  }

  getCategoryNameInput() {
    return this.categoryNameInput.getAttribute('value');
  }

  setCategoryAliasInput(categoryAlias) {
    this.categoryAliasInput.sendKeys(categoryAlias);
  }

  getCategoryAliasInput() {
    return this.categoryAliasInput.getAttribute('value');
  }

  setCategoryDescriptionInput(categoryDescription) {
    this.categoryDescriptionInput.sendKeys(categoryDescription);
  }

  getCategoryDescriptionInput() {
    return this.categoryDescriptionInput.getAttribute('value');
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
