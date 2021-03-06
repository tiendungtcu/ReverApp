import { element, by, ElementFinder } from 'protractor';

export default class DepartmentUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.department.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  departmentNameInput: ElementFinder = element(by.css('input#department-departmentName'));
  departmentAvatarUrlInput: ElementFinder = element(by.css('input#department-departmentAvatarUrl'));
  departmentPhoneInput: ElementFinder = element(by.css('input#department-departmentPhone'));

  getPageTitle() {
    return this.pageTitle;
  }

  setDepartmentNameInput(departmentName) {
    this.departmentNameInput.sendKeys(departmentName);
  }

  getDepartmentNameInput() {
    return this.departmentNameInput.getAttribute('value');
  }

  setDepartmentAvatarUrlInput(departmentAvatarUrl) {
    this.departmentAvatarUrlInput.sendKeys(departmentAvatarUrl);
  }

  getDepartmentAvatarUrlInput() {
    return this.departmentAvatarUrlInput.getAttribute('value');
  }

  setDepartmentPhoneInput(departmentPhone) {
    this.departmentPhoneInput.sendKeys(departmentPhone);
  }

  getDepartmentPhoneInput() {
    return this.departmentPhoneInput.getAttribute('value');
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
