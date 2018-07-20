import { element, by, ElementFinder } from 'protractor';

export default class EmployeeUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.employee.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  employeeNameInput: ElementFinder = element(by.css('input#employee-employeeName'));
  employeeFirstNameInput: ElementFinder = element(by.css('input#employee-employeeFirstName'));
  employeeLastNameInput: ElementFinder = element(by.css('input#employee-employeeLastName'));
  employeeDobInput: ElementFinder = element(by.css('input#employee-employeeDob'));
  employeeSexSelect: ElementFinder = element(by.css('select#employee-employeeSex'));
  employeeIdentityCardInput: ElementFinder = element(by.css('input#employee-employeeIdentityCard'));
  employeePhoneInput: ElementFinder = element(by.css('input#employee-employeePhone'));
  employeeEmailInput: ElementFinder = element(by.css('input#employee-employeeEmail'));
  employeeAvatarInput: ElementFinder = element(by.css('input#file_employeeAvatar'));
  employeeFacebookInput: ElementFinder = element(by.css('input#employee-employeeFacebook'));
  employeeLinkedinInput: ElementFinder = element(by.css('input#employee-employeeLinkedin'));
  employeeInstagramInput: ElementFinder = element(by.css('input#employee-employeeInstagram'));
  employeeGooglePlusInput: ElementFinder = element(by.css('input#employee-employeeGooglePlus'));
  employeeZaloInput: ElementFinder = element(by.css('input#employee-employeeZalo'));
  employeeTwitterInput: ElementFinder = element(by.css('input#employee-employeeTwitter'));
  employeeYoutubeInput: ElementFinder = element(by.css('input#employee-employeeYoutube'));
  contactSelect: ElementFinder = element(by.css('select#employee-contact'));
  photoSelect: ElementFinder = element(by.css('select#employee-photo'));
  jobtitleSelect: ElementFinder = element(by.css('select#employee-jobtitle'));
  departmentSelect: ElementFinder = element(by.css('select#employee-department'));
  managerSelect: ElementFinder = element(by.css('select#employee-manager'));

  getPageTitle() {
    return this.pageTitle;
  }

  setEmployeeNameInput(employeeName) {
    this.employeeNameInput.sendKeys(employeeName);
  }

  getEmployeeNameInput() {
    return this.employeeNameInput.getAttribute('value');
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

  setEmployeeAvatarInput(employeeAvatar) {
    this.employeeAvatarInput.sendKeys(employeeAvatar);
  }

  getEmployeeAvatarInput() {
    return this.employeeAvatarInput.getAttribute('value');
  }

  setEmployeeFacebookInput(employeeFacebook) {
    this.employeeFacebookInput.sendKeys(employeeFacebook);
  }

  getEmployeeFacebookInput() {
    return this.employeeFacebookInput.getAttribute('value');
  }

  setEmployeeLinkedinInput(employeeLinkedin) {
    this.employeeLinkedinInput.sendKeys(employeeLinkedin);
  }

  getEmployeeLinkedinInput() {
    return this.employeeLinkedinInput.getAttribute('value');
  }

  setEmployeeInstagramInput(employeeInstagram) {
    this.employeeInstagramInput.sendKeys(employeeInstagram);
  }

  getEmployeeInstagramInput() {
    return this.employeeInstagramInput.getAttribute('value');
  }

  setEmployeeGooglePlusInput(employeeGooglePlus) {
    this.employeeGooglePlusInput.sendKeys(employeeGooglePlus);
  }

  getEmployeeGooglePlusInput() {
    return this.employeeGooglePlusInput.getAttribute('value');
  }

  setEmployeeZaloInput(employeeZalo) {
    this.employeeZaloInput.sendKeys(employeeZalo);
  }

  getEmployeeZaloInput() {
    return this.employeeZaloInput.getAttribute('value');
  }

  setEmployeeTwitterInput(employeeTwitter) {
    this.employeeTwitterInput.sendKeys(employeeTwitter);
  }

  getEmployeeTwitterInput() {
    return this.employeeTwitterInput.getAttribute('value');
  }

  setEmployeeYoutubeInput(employeeYoutube) {
    this.employeeYoutubeInput.sendKeys(employeeYoutube);
  }

  getEmployeeYoutubeInput() {
    return this.employeeYoutubeInput.getAttribute('value');
  }

  contactSelectLastOption() {
    this.contactSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  contactSelectOption(option) {
    this.contactSelect.sendKeys(option);
  }

  getContactSelect() {
    return this.contactSelect;
  }

  getContactSelectedOption() {
    return this.contactSelect.element(by.css('option:checked')).getText();
  }

  photoSelectLastOption() {
    this.photoSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  photoSelectOption(option) {
    this.photoSelect.sendKeys(option);
  }

  getPhotoSelect() {
    return this.photoSelect;
  }

  getPhotoSelectedOption() {
    return this.photoSelect.element(by.css('option:checked')).getText();
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

  managerSelectLastOption() {
    this.managerSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  managerSelectOption(option) {
    this.managerSelect.sendKeys(option);
  }

  getManagerSelect() {
    return this.managerSelect;
  }

  getManagerSelectedOption() {
    return this.managerSelect.element(by.css('option:checked')).getText();
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
