import { element, by, ElementFinder } from 'protractor';

export default class PhotoUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.photo.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  photoNameInput: ElementFinder = element(by.css('input#photo-photoName'));
  photoDateInput: ElementFinder = element(by.css('input#photo-photoDate'));
  photoUrlInput: ElementFinder = element(by.css('input#photo-photoUrl'));
  photoMimeTypeInput: ElementFinder = element(by.css('input#photo-photoMimeType'));
  resourceIdInput: ElementFinder = element(by.css('input#photo-resourceId'));
  resourceTypeSelect: ElementFinder = element(by.css('select#photo-resourceType'));
  photoSizeInput: ElementFinder = element(by.css('input#photo-photoSize'));
  photoAltTextInput: ElementFinder = element(by.css('input#photo-photoAltText'));
  photoThumbnailUrlInput: ElementFinder = element(by.css('input#photo-photoThumbnailUrl'));

  getPageTitle() {
    return this.pageTitle;
  }

  setPhotoNameInput(photoName) {
    this.photoNameInput.sendKeys(photoName);
  }

  getPhotoNameInput() {
    return this.photoNameInput.getAttribute('value');
  }

  setPhotoDateInput(photoDate) {
    this.photoDateInput.sendKeys(photoDate);
  }

  getPhotoDateInput() {
    return this.photoDateInput.getAttribute('value');
  }

  setPhotoUrlInput(photoUrl) {
    this.photoUrlInput.sendKeys(photoUrl);
  }

  getPhotoUrlInput() {
    return this.photoUrlInput.getAttribute('value');
  }

  setPhotoMimeTypeInput(photoMimeType) {
    this.photoMimeTypeInput.sendKeys(photoMimeType);
  }

  getPhotoMimeTypeInput() {
    return this.photoMimeTypeInput.getAttribute('value');
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
  setPhotoSizeInput(photoSize) {
    this.photoSizeInput.sendKeys(photoSize);
  }

  getPhotoSizeInput() {
    return this.photoSizeInput.getAttribute('value');
  }

  setPhotoAltTextInput(photoAltText) {
    this.photoAltTextInput.sendKeys(photoAltText);
  }

  getPhotoAltTextInput() {
    return this.photoAltTextInput.getAttribute('value');
  }

  setPhotoThumbnailUrlInput(photoThumbnailUrl) {
    this.photoThumbnailUrlInput.sendKeys(photoThumbnailUrl);
  }

  getPhotoThumbnailUrlInput() {
    return this.photoThumbnailUrlInput.getAttribute('value');
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
