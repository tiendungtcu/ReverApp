import { element, by, ElementFinder } from 'protractor';

export default class ProjectUserUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.projectUser.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  likedInput: ElementFinder = element(by.css('input#project-user-liked'));
  sharedInput: ElementFinder = element(by.css('input#project-user-shared'));
  likedDateInput: ElementFinder = element(by.css('input#project-user-likedDate'));
  sharedDateInput: ElementFinder = element(by.css('input#project-user-sharedDate'));
  userSelect: ElementFinder = element(by.css('select#project-user-user'));
  projectSelect: ElementFinder = element(by.css('select#project-user-project'));

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

  projectSelectLastOption() {
    this.projectSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  projectSelectOption(option) {
    this.projectSelect.sendKeys(option);
  }

  getProjectSelect() {
    return this.projectSelect;
  }

  getProjectSelectedOption() {
    return this.projectSelect.element(by.css('option:checked')).getText();
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
