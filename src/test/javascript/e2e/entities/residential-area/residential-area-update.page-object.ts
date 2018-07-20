import { element, by, ElementFinder } from 'protractor';

export default class ResidentialAreaUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.residentialArea.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  residentialNameInput: ElementFinder = element(by.css('input#residential-area-residentialName'));
  residentialAliasInput: ElementFinder = element(by.css('input#residential-area-residentialAlias'));
  residentialDescriptionInput: ElementFinder = element(by.css('input#residential-area-residentialDescription'));
  residentialDetailInput: ElementFinder = element(by.css('input#residential-area-residentialDetail'));
  residentialProvinceInput: ElementFinder = element(by.css('input#residential-area-residentialProvince'));
  residentialDistrictInput: ElementFinder = element(by.css('input#residential-area-residentialDistrict'));
  residentialBoundaryInput: ElementFinder = element(by.css('input#residential-area-residentialBoundary'));
  residentialImageInput: ElementFinder = element(by.css('input#file_residentialImage'));
  photoSelect: ElementFinder = element(by.css('select#residential-area-photo'));
  tagSelect: ElementFinder = element(by.css('select#residential-area-tag'));

  getPageTitle() {
    return this.pageTitle;
  }

  setResidentialNameInput(residentialName) {
    this.residentialNameInput.sendKeys(residentialName);
  }

  getResidentialNameInput() {
    return this.residentialNameInput.getAttribute('value');
  }

  setResidentialAliasInput(residentialAlias) {
    this.residentialAliasInput.sendKeys(residentialAlias);
  }

  getResidentialAliasInput() {
    return this.residentialAliasInput.getAttribute('value');
  }

  setResidentialDescriptionInput(residentialDescription) {
    this.residentialDescriptionInput.sendKeys(residentialDescription);
  }

  getResidentialDescriptionInput() {
    return this.residentialDescriptionInput.getAttribute('value');
  }

  setResidentialDetailInput(residentialDetail) {
    this.residentialDetailInput.sendKeys(residentialDetail);
  }

  getResidentialDetailInput() {
    return this.residentialDetailInput.getAttribute('value');
  }

  setResidentialProvinceInput(residentialProvince) {
    this.residentialProvinceInput.sendKeys(residentialProvince);
  }

  getResidentialProvinceInput() {
    return this.residentialProvinceInput.getAttribute('value');
  }

  setResidentialDistrictInput(residentialDistrict) {
    this.residentialDistrictInput.sendKeys(residentialDistrict);
  }

  getResidentialDistrictInput() {
    return this.residentialDistrictInput.getAttribute('value');
  }

  setResidentialBoundaryInput(residentialBoundary) {
    this.residentialBoundaryInput.sendKeys(residentialBoundary);
  }

  getResidentialBoundaryInput() {
    return this.residentialBoundaryInput.getAttribute('value');
  }

  setResidentialImageInput(residentialImage) {
    this.residentialImageInput.sendKeys(residentialImage);
  }

  getResidentialImageInput() {
    return this.residentialImageInput.getAttribute('value');
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

  tagSelectLastOption() {
    this.tagSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  tagSelectOption(option) {
    this.tagSelect.sendKeys(option);
  }

  getTagSelect() {
    return this.tagSelect;
  }

  getTagSelectedOption() {
    return this.tagSelect.element(by.css('option:checked')).getText();
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
