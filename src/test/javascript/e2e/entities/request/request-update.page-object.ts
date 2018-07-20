import { element, by, ElementFinder } from 'protractor';

export default class RequestUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.request.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  requestFirstNameInput: ElementFinder = element(by.css('input#request-requestFirstName'));
  requestLastNameInput: ElementFinder = element(by.css('input#request-requestLastName'));
  requestEmailInput: ElementFinder = element(by.css('input#request-requestEmail'));
  requestPhoneInput: ElementFinder = element(by.css('input#request-requestPhone'));
  requestGetAnalysisInput: ElementFinder = element(by.css('input#request-requestGetAnalysis'));
  requestGetPriceInput: ElementFinder = element(by.css('input#request-requestGetPrice'));
  requestPageUrlInput: ElementFinder = element(by.css('input#request-requestPageUrl'));
  requestPageNameInput: ElementFinder = element(by.css('input#request-requestPageName'));
  requestPropertyIdInput: ElementFinder = element(by.css('input#request-requestPropertyId'));
  requestPropertyTypeSelect: ElementFinder = element(by.css('select#request-requestPropertyType'));
  requestTypeSelect: ElementFinder = element(by.css('select#request-requestType'));
  requestMeetingDateInput: ElementFinder = element(by.css('input#request-requestMeetingDate'));
  requestQuestionInput: ElementFinder = element(by.css('input#request-requestQuestion'));
  requestPriceInput: ElementFinder = element(by.css('input#request-requestPrice'));
  requestCreatedDateInput: ElementFinder = element(by.css('input#request-requestCreatedDate'));
  requestConsultantIdInput: ElementFinder = element(by.css('input#request-requestConsultantId'));
  userSelect: ElementFinder = element(by.css('select#request-user'));
  propertySelect: ElementFinder = element(by.css('select#request-property'));
  projectSelect: ElementFinder = element(by.css('select#request-project'));

  getPageTitle() {
    return this.pageTitle;
  }

  setRequestFirstNameInput(requestFirstName) {
    this.requestFirstNameInput.sendKeys(requestFirstName);
  }

  getRequestFirstNameInput() {
    return this.requestFirstNameInput.getAttribute('value');
  }

  setRequestLastNameInput(requestLastName) {
    this.requestLastNameInput.sendKeys(requestLastName);
  }

  getRequestLastNameInput() {
    return this.requestLastNameInput.getAttribute('value');
  }

  setRequestEmailInput(requestEmail) {
    this.requestEmailInput.sendKeys(requestEmail);
  }

  getRequestEmailInput() {
    return this.requestEmailInput.getAttribute('value');
  }

  setRequestPhoneInput(requestPhone) {
    this.requestPhoneInput.sendKeys(requestPhone);
  }

  getRequestPhoneInput() {
    return this.requestPhoneInput.getAttribute('value');
  }

  getRequestGetAnalysisInput() {
    return this.requestGetAnalysisInput;
  }
  getRequestGetPriceInput() {
    return this.requestGetPriceInput;
  }
  setRequestPageUrlInput(requestPageUrl) {
    this.requestPageUrlInput.sendKeys(requestPageUrl);
  }

  getRequestPageUrlInput() {
    return this.requestPageUrlInput.getAttribute('value');
  }

  setRequestPageNameInput(requestPageName) {
    this.requestPageNameInput.sendKeys(requestPageName);
  }

  getRequestPageNameInput() {
    return this.requestPageNameInput.getAttribute('value');
  }

  setRequestPropertyIdInput(requestPropertyId) {
    this.requestPropertyIdInput.sendKeys(requestPropertyId);
  }

  getRequestPropertyIdInput() {
    return this.requestPropertyIdInput.getAttribute('value');
  }

  setRequestPropertyTypeSelect(requestPropertyType) {
    this.requestPropertyTypeSelect.sendKeys(requestPropertyType);
  }

  getRequestPropertyTypeSelect() {
    return this.requestPropertyTypeSelect.element(by.css('option:checked')).getText();
  }

  requestPropertyTypeSelectLastOption() {
    this.requestPropertyTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setRequestTypeSelect(requestType) {
    this.requestTypeSelect.sendKeys(requestType);
  }

  getRequestTypeSelect() {
    return this.requestTypeSelect.element(by.css('option:checked')).getText();
  }

  requestTypeSelectLastOption() {
    this.requestTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setRequestMeetingDateInput(requestMeetingDate) {
    this.requestMeetingDateInput.sendKeys(requestMeetingDate);
  }

  getRequestMeetingDateInput() {
    return this.requestMeetingDateInput.getAttribute('value');
  }

  setRequestQuestionInput(requestQuestion) {
    this.requestQuestionInput.sendKeys(requestQuestion);
  }

  getRequestQuestionInput() {
    return this.requestQuestionInput.getAttribute('value');
  }

  setRequestPriceInput(requestPrice) {
    this.requestPriceInput.sendKeys(requestPrice);
  }

  getRequestPriceInput() {
    return this.requestPriceInput.getAttribute('value');
  }

  setRequestCreatedDateInput(requestCreatedDate) {
    this.requestCreatedDateInput.sendKeys(requestCreatedDate);
  }

  getRequestCreatedDateInput() {
    return this.requestCreatedDateInput.getAttribute('value');
  }

  setRequestConsultantIdInput(requestConsultantId) {
    this.requestConsultantIdInput.sendKeys(requestConsultantId);
  }

  getRequestConsultantIdInput() {
    return this.requestConsultantIdInput.getAttribute('value');
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

  propertySelectLastOption() {
    this.propertySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  propertySelectOption(option) {
    this.propertySelect.sendKeys(option);
  }

  getPropertySelect() {
    return this.propertySelect;
  }

  getPropertySelectedOption() {
    return this.propertySelect.element(by.css('option:checked')).getText();
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
