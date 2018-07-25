import { element, by, ElementFinder } from 'protractor';

export default class CategoryUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.category.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  categoryNameInput: ElementFinder = element(by.css('input#category-categoryName'));
  categoryAliasInput: ElementFinder = element(by.css('input#category-categoryAlias'));
  categoryDateInput: ElementFinder = element(by.css('input#category-categoryDate'));

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

  setCategoryDateInput(categoryDate) {
    this.categoryDateInput.sendKeys(categoryDate);
  }

  getCategoryDateInput() {
    return this.categoryDateInput.getAttribute('value');
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
