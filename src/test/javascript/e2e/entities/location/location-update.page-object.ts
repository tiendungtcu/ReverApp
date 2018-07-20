import { element, by, ElementFinder } from 'protractor';

export default class LocationUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.location.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  locationNameInput: ElementFinder = element(by.css('input#location-locationName'));
  locationFullAddressInput: ElementFinder = element(by.css('input#location-locationFullAddress'));
  locationNumberInput: ElementFinder = element(by.css('input#location-locationNumber'));
  locationRoadInput: ElementFinder = element(by.css('input#location-locationRoad'));
  locationWardInput: ElementFinder = element(by.css('input#location-locationWard'));
  locationDistrictInput: ElementFinder = element(by.css('input#location-locationDistrict'));
  locationProvinceInput: ElementFinder = element(by.css('input#location-locationProvince'));
  locationCountryInput: ElementFinder = element(by.css('input#location-locationCountry'));
  locationGmapUrlInput: ElementFinder = element(by.css('input#location-locationGmapUrl'));
  longitudeInput: ElementFinder = element(by.css('input#location-longitude'));
  latitudeInput: ElementFinder = element(by.css('input#location-latitude'));
  locationHideInput: ElementFinder = element(by.css('input#location-locationHide'));

  getPageTitle() {
    return this.pageTitle;
  }

  setLocationNameInput(locationName) {
    this.locationNameInput.sendKeys(locationName);
  }

  getLocationNameInput() {
    return this.locationNameInput.getAttribute('value');
  }

  setLocationFullAddressInput(locationFullAddress) {
    this.locationFullAddressInput.sendKeys(locationFullAddress);
  }

  getLocationFullAddressInput() {
    return this.locationFullAddressInput.getAttribute('value');
  }

  setLocationNumberInput(locationNumber) {
    this.locationNumberInput.sendKeys(locationNumber);
  }

  getLocationNumberInput() {
    return this.locationNumberInput.getAttribute('value');
  }

  setLocationRoadInput(locationRoad) {
    this.locationRoadInput.sendKeys(locationRoad);
  }

  getLocationRoadInput() {
    return this.locationRoadInput.getAttribute('value');
  }

  setLocationWardInput(locationWard) {
    this.locationWardInput.sendKeys(locationWard);
  }

  getLocationWardInput() {
    return this.locationWardInput.getAttribute('value');
  }

  setLocationDistrictInput(locationDistrict) {
    this.locationDistrictInput.sendKeys(locationDistrict);
  }

  getLocationDistrictInput() {
    return this.locationDistrictInput.getAttribute('value');
  }

  setLocationProvinceInput(locationProvince) {
    this.locationProvinceInput.sendKeys(locationProvince);
  }

  getLocationProvinceInput() {
    return this.locationProvinceInput.getAttribute('value');
  }

  setLocationCountryInput(locationCountry) {
    this.locationCountryInput.sendKeys(locationCountry);
  }

  getLocationCountryInput() {
    return this.locationCountryInput.getAttribute('value');
  }

  setLocationGmapUrlInput(locationGmapUrl) {
    this.locationGmapUrlInput.sendKeys(locationGmapUrl);
  }

  getLocationGmapUrlInput() {
    return this.locationGmapUrlInput.getAttribute('value');
  }

  setLongitudeInput(longitude) {
    this.longitudeInput.sendKeys(longitude);
  }

  getLongitudeInput() {
    return this.longitudeInput.getAttribute('value');
  }

  setLatitudeInput(latitude) {
    this.latitudeInput.sendKeys(latitude);
  }

  getLatitudeInput() {
    return this.latitudeInput.getAttribute('value');
  }

  getLocationHideInput() {
    return this.locationHideInput;
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
