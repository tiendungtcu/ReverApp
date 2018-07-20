import { element, by, ElementFinder } from 'protractor';

export default class ProjectBuilderUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.projectBuilder.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  builderNameInput: ElementFinder = element(by.css('input#project-builder-builderName'));
  builderTitleInput: ElementFinder = element(by.css('input#project-builder-builderTitle'));
  builderDateInput: ElementFinder = element(by.css('input#project-builder-builderDate'));
  builderDescriptionInput: ElementFinder = element(by.css('input#project-builder-builderDescription'));
  builderAddressInput: ElementFinder = element(by.css('input#project-builder-builderAddress'));
  builderWebsiteInput: ElementFinder = element(by.css('input#project-builder-builderWebsite'));
  builderPhoneInput: ElementFinder = element(by.css('input#project-builder-builderPhone'));
  builderPhotoInput: ElementFinder = element(by.css('input#file_builderPhoto'));

  getPageTitle() {
    return this.pageTitle;
  }

  setBuilderNameInput(builderName) {
    this.builderNameInput.sendKeys(builderName);
  }

  getBuilderNameInput() {
    return this.builderNameInput.getAttribute('value');
  }

  setBuilderTitleInput(builderTitle) {
    this.builderTitleInput.sendKeys(builderTitle);
  }

  getBuilderTitleInput() {
    return this.builderTitleInput.getAttribute('value');
  }

  setBuilderDateInput(builderDate) {
    this.builderDateInput.sendKeys(builderDate);
  }

  getBuilderDateInput() {
    return this.builderDateInput.getAttribute('value');
  }

  setBuilderDescriptionInput(builderDescription) {
    this.builderDescriptionInput.sendKeys(builderDescription);
  }

  getBuilderDescriptionInput() {
    return this.builderDescriptionInput.getAttribute('value');
  }

  setBuilderAddressInput(builderAddress) {
    this.builderAddressInput.sendKeys(builderAddress);
  }

  getBuilderAddressInput() {
    return this.builderAddressInput.getAttribute('value');
  }

  setBuilderWebsiteInput(builderWebsite) {
    this.builderWebsiteInput.sendKeys(builderWebsite);
  }

  getBuilderWebsiteInput() {
    return this.builderWebsiteInput.getAttribute('value');
  }

  setBuilderPhoneInput(builderPhone) {
    this.builderPhoneInput.sendKeys(builderPhone);
  }

  getBuilderPhoneInput() {
    return this.builderPhoneInput.getAttribute('value');
  }

  setBuilderPhotoInput(builderPhoto) {
    this.builderPhotoInput.sendKeys(builderPhoto);
  }

  getBuilderPhotoInput() {
    return this.builderPhotoInput.getAttribute('value');
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
