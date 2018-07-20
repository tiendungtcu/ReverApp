import { element, by, ElementFinder } from 'protractor';

export default class DocumentUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.document.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  documentNameInput: ElementFinder = element(by.css('input#document-documentName'));
  documentUrlInput: ElementFinder = element(by.css('input#document-documentUrl'));
  documentDateInput: ElementFinder = element(by.css('input#document-documentDate'));
  documentContentInput: ElementFinder = element(by.css('input#document-documentContent'));
  documentPhotoInput: ElementFinder = element(by.css('input#file_documentPhoto'));
  documentTypeSelect: ElementFinder = element(by.css('select#document-documentType'));
  photoSelect: ElementFinder = element(by.css('select#document-photo'));

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

  setDocumentContentInput(documentContent) {
    this.documentContentInput.sendKeys(documentContent);
  }

  getDocumentContentInput() {
    return this.documentContentInput.getAttribute('value');
  }

  setDocumentPhotoInput(documentPhoto) {
    this.documentPhotoInput.sendKeys(documentPhoto);
  }

  getDocumentPhotoInput() {
    return this.documentPhotoInput.getAttribute('value');
  }

  setDocumentTypeSelect(documentType) {
    this.documentTypeSelect.sendKeys(documentType);
  }

  getDocumentTypeSelect() {
    return this.documentTypeSelect.element(by.css('option:checked')).getText();
  }

  documentTypeSelectLastOption() {
    this.documentTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
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
