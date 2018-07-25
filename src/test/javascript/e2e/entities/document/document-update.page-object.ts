import { element, by, ElementFinder } from 'protractor';

export default class DocumentUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.document.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  documentNameInput: ElementFinder = element(by.css('input#document-documentName'));
  documentUrlInput: ElementFinder = element(by.css('input#document-documentUrl'));
  documentDateInput: ElementFinder = element(by.css('input#document-documentDate'));
  documentMimeTypeInput: ElementFinder = element(by.css('input#document-documentMimeType'));
  documentSizeInput: ElementFinder = element(by.css('input#document-documentSize'));
  resourceTypeSelect: ElementFinder = element(by.css('select#document-resourceType'));
  resourceIdInput: ElementFinder = element(by.css('input#document-resourceId'));

  getPageTitle() {
    return this.pageTitle;
  }

  setDocumentNameInput(documentName) {
    this.documentNameInput.sendKeys(documentName);
  }

  getDocumentNameInput() {
    return this.documentNameInput.getAttribute('value');
  }

  setDocumentUrlInput(documentUrl) {
    this.documentUrlInput.sendKeys(documentUrl);
  }

  getDocumentUrlInput() {
    return this.documentUrlInput.getAttribute('value');
  }

  setDocumentDateInput(documentDate) {
    this.documentDateInput.sendKeys(documentDate);
  }

  getDocumentDateInput() {
    return this.documentDateInput.getAttribute('value');
  }

  setDocumentMimeTypeInput(documentMimeType) {
    this.documentMimeTypeInput.sendKeys(documentMimeType);
  }

  getDocumentMimeTypeInput() {
    return this.documentMimeTypeInput.getAttribute('value');
  }

  setDocumentSizeInput(documentSize) {
    this.documentSizeInput.sendKeys(documentSize);
  }

  getDocumentSizeInput() {
    return this.documentSizeInput.getAttribute('value');
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
  setResourceIdInput(resourceId) {
    this.resourceIdInput.sendKeys(resourceId);
  }

  getResourceIdInput() {
    return this.resourceIdInput.getAttribute('value');
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
