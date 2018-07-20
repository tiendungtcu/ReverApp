import { element, by, ElementFinder } from 'protractor';

export default class PhotoUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.photo.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  photoNameInput: ElementFinder = element(by.css('input#photo-photoName'));
  photoImageInput: ElementFinder = element(by.css('input#file_photoImage'));
  photoExtensionInput: ElementFinder = element(by.css('input#photo-photoExtension'));
  photoUrlInput: ElementFinder = element(by.css('input#photo-photoUrl'));
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

  setPhotoImageInput(photoImage) {
    this.photoImageInput.sendKeys(photoImage);
  }

  getPhotoImageInput() {
    return this.photoImageInput.getAttribute('value');
  }

  setPhotoExtensionInput(photoExtension) {
    this.photoExtensionInput.sendKeys(photoExtension);
  }

  getPhotoExtensionInput() {
    return this.photoExtensionInput.getAttribute('value');
  }

  setPhotoUrlInput(photoUrl) {
    this.photoUrlInput.sendKeys(photoUrl);
  }

  getPhotoUrlInput() {
    return this.photoUrlInput.getAttribute('value');
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
