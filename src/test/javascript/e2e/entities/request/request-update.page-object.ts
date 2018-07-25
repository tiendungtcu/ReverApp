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
  resourceIdInput: ElementFinder = element(by.css('input#request-resourceId'));
  resourceTypeSelect: ElementFinder = element(by.css('select#request-resourceType'));
  requestTypeSelect: ElementFinder = element(by.css('select#request-requestType'));
  requestMeetingDateInput: ElementFinder = element(by.css('input#request-requestMeetingDate'));
  requestQuestionInput: ElementFinder = element(by.css('input#request-requestQuestion'));
  requestPriceInput: ElementFinder = element(by.css('input#request-requestPrice'));
  senderSelect: ElementFinder = element(by.css('select#request-sender'));
  receiverSelect: ElementFinder = element(by.css('select#request-receiver'));

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

  setResourceIdInput(resourceId) {
    this.resourceIdInput.sendKeys(resourceId);
  }

  getResourceIdInput() {
    return this.resourceIdInput.getAttribute('value');
  }

  setResourceTypeSelect(resourceType) {
    this.resourceTypeSelect.sendKeys(resourceType);
  }

  getResourceTypeSelect() {
    return this.resourceTypeSelect.element(by.css('option:checked')).getText();
  }

  resourceTypeSelectLastOption() {
    this.resourceTypeSelect
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

  senderSelectLastOption() {
    this.senderSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  senderSelectOption(option) {
    this.senderSelect.sendKeys(option);
  }

  getSenderSelect() {
    return this.senderSelect;
  }

  getSenderSelectedOption() {
    return this.senderSelect.element(by.css('option:checked')).getText();
  }

  receiverSelectLastOption() {
    this.receiverSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  receiverSelectOption(option) {
    this.receiverSelect.sendKeys(option);
  }

  getReceiverSelect() {
    return this.receiverSelect;
  }

  getReceiverSelectedOption() {
    return this.receiverSelect.element(by.css('option:checked')).getText();
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
