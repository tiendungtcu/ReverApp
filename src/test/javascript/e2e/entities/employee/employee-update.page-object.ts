import { element, by, ElementFinder } from 'protractor';

export default class EmployeeUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.employee.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  employeeFirstNameInput: ElementFinder = element(by.css('input#employee-employeeFirstName'));
  employeeLastNameInput: ElementFinder = element(by.css('input#employee-employeeLastName'));
  employeeDobInput: ElementFinder = element(by.css('input#employee-employeeDob'));
  employeeSexSelect: ElementFinder = element(by.css('select#employee-employeeSex'));
  employeeIdentityCardInput: ElementFinder = element(by.css('input#employee-employeeIdentityCard'));
  employeePhoneInput: ElementFinder = element(by.css('input#employee-employeePhone'));
  employeeEmailInput: ElementFinder = element(by.css('input#employee-employeeEmail'));
  accountSelect: ElementFinder = element(by.css('select#employee-account'));
  departmentSelect: ElementFinder = element(by.css('select#employee-department'));
  jobtitleSelect: ElementFinder = element(by.css('select#employee-jobtitle'));

  getPageTitle() {
    return this.pageTitle;
  }

  setEmployeeFirstNameInput(employeeFirstName) {
    this.employeeFirstNameInput.sendKeys(employeeFirstName);
  }

  getEmployeeFirstNameInput() {
    return this.employeeFirstNameInput.getAttribute('value');
  }

  setEmployeeLastNameInput(employeeLastName) {
    this.employeeLastNameInput.sendKeys(employeeLastName);
  }

  getEmployeeLastNameInput() {
    return this.employeeLastNameInput.getAttribute('value');
  }

  setEmployeeDobInput(employeeDob) {
    this.employeeDobInput.sendKeys(employeeDob);
  }

  getEmployeeDobInput() {
    return this.employeeDobInput.getAttribute('value');
  }

  setEmployeeSexSelect(employeeSex) {
    this.employeeSexSelect.sendKeys(employeeSex);
  }

  getEmployeeSexSelect() {
    return this.employeeSexSelect.element(by.css('option:checked')).getText();
  }

  employeeSexSelectLastOption() {
    this.employeeSexSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setEmployeeIdentityCardInput(employeeIdentityCard) {
    this.employeeIdentityCardInput.sendKeys(employeeIdentityCard);
  }

  getEmployeeIdentityCardInput() {
    return this.employeeIdentityCardInput.getAttribute('value');
  }

  setEmployeePhoneInput(employeePhone) {
    this.employeePhoneInput.sendKeys(employeePhone);
  }

  getEmployeePhoneInput() {
    return this.employeePhoneInput.getAttribute('value');
  }

  setEmployeeEmailInput(employeeEmail) {
    this.employeeEmailInput.sendKeys(employeeEmail);
  }

  getEmployeeEmailInput() {
    return this.employeeEmailInput.getAttribute('value');
  }

  accountSelectLastOption() {
    this.accountSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  accountSelectOption(option) {
    this.accountSelect.sendKeys(option);
  }

  getAccountSelect() {
    return this.accountSelect;
  }

  getAccountSelectedOption() {
    return this.accountSelect.element(by.css('option:checked')).getText();
  }

  departmentSelectLastOption() {
    this.departmentSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  departmentSelectOption(option) {
    this.departmentSelect.sendKeys(option);
  }

  getDepartmentSelect() {
    return this.departmentSelect;
  }

  getDepartmentSelectedOption() {
    return this.departmentSelect.element(by.css('option:checked')).getText();
  }

  jobtitleSelectLastOption() {
    this.jobtitleSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  jobtitleSelectOption(option) {
    this.jobtitleSelect.sendKeys(option);
  }

  getJobtitleSelect() {
    return this.jobtitleSelect;
  }

  getJobtitleSelectedOption() {
    return this.jobtitleSelect.element(by.css('option:checked')).getText();
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
