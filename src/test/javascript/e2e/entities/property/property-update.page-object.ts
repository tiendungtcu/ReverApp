import { element, by, ElementFinder } from 'protractor';

export default class PropertyUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.property.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  propertyCodeInput: ElementFinder = element(by.css('input#property-propertyCode'));
  propertyNameInput: ElementFinder = element(by.css('input#property-propertyName'));
  propertyAliasInput: ElementFinder = element(by.css('input#property-propertyAlias'));
  propertyTransactionSelect: ElementFinder = element(by.css('select#property-propertyTransaction'));
  propertyNumberInput: ElementFinder = element(by.css('input#property-propertyNumber'));
  propertyRoadInput: ElementFinder = element(by.css('input#property-propertyRoad'));
  propertyWardInput: ElementFinder = element(by.css('input#property-propertyWard'));
  propertyDistrictInput: ElementFinder = element(by.css('input#property-propertyDistrict'));
  propertyProvinceInput: ElementFinder = element(by.css('input#property-propertyProvince'));
  propertyDescriptionInput: ElementFinder = element(by.css('input#property-propertyDescription'));
  propertyBedRoomsInput: ElementFinder = element(by.css('input#property-propertyBedRooms'));
  propertyBathRoomsInput: ElementFinder = element(by.css('input#property-propertyBathRooms'));
  propertySquareInput: ElementFinder = element(by.css('input#property-propertySquare'));
  propertyUsePurposeSelect: ElementFinder = element(by.css('select#property-propertyUsePurpose'));
  propertyOwnerTypeInput: ElementFinder = element(by.css('input#property-propertyOwnerType'));
  propertyTowerInput: ElementFinder = element(by.css('input#property-propertyTower'));
  propertyRentPriceInput: ElementFinder = element(by.css('input#property-propertyRentPrice'));
  propertyRentUnitSelect: ElementFinder = element(by.css('select#property-propertyRentUnit'));
  propertyRentStartedDateInput: ElementFinder = element(by.css('input#property-propertyRentStartedDate'));
  propertySellPriceInput: ElementFinder = element(by.css('input#property-propertySellPrice'));
  propertySellUnitSelect: ElementFinder = element(by.css('select#property-propertySellUnit'));
  propertySellStartedDateInput: ElementFinder = element(by.css('input#property-propertySellStartedDate'));
  propertySofaInput: ElementFinder = element(by.css('input#property-propertySofa'));
  propertyDiningTableInput: ElementFinder = element(by.css('input#property-propertyDiningTable'));
  propertyKitchenInput: ElementFinder = element(by.css('input#property-propertyKitchen'));
  propertyCabinetKitchenInput: ElementFinder = element(by.css('input#property-propertyCabinetKitchen'));
  propertyKitchenEquipmentInput: ElementFinder = element(by.css('input#property-propertyKitchenEquipment'));
  propertyWardrobeInput: ElementFinder = element(by.css('input#property-propertyWardrobe'));
  propertyMakeupTableInput: ElementFinder = element(by.css('input#property-propertyMakeupTable'));
  propertyDeskInput: ElementFinder = element(by.css('input#property-propertyDesk'));
  propertyTiviInput: ElementFinder = element(by.css('input#property-propertyTivi'));
  propertyWashingMachineInput: ElementFinder = element(by.css('input#property-propertyWashingMachine'));
  propertyRefrigeratorInput: ElementFinder = element(by.css('input#property-propertyRefrigerator'));
  propertyAirconditionInput: ElementFinder = element(by.css('input#property-propertyAircondition'));
  propertyMicrowaveInput: ElementFinder = element(by.css('input#property-propertyMicrowave'));
  propertyWaterHeaterInput: ElementFinder = element(by.css('input#property-propertyWaterHeater'));
  propertyBedInput: ElementFinder = element(by.css('input#property-propertyBed'));
  propertyHeaterInput: ElementFinder = element(by.css('input#property-propertyHeater'));
  propertyAudioEquipmentInput: ElementFinder = element(by.css('input#property-propertyAudioEquipment'));
  propertyInternetInput: ElementFinder = element(by.css('input#property-propertyInternet'));
  propertyCableTiviInput: ElementFinder = element(by.css('input#property-propertyCableTivi'));
  propertyPetPermissionInput: ElementFinder = element(by.css('input#property-propertyPetPermission'));
  propertyElevatorInput: ElementFinder = element(by.css('input#property-propertyElevator'));
  propertySwimmingPoolInput: ElementFinder = element(by.css('input#property-propertySwimmingPool'));
  propertyGymInput: ElementFinder = element(by.css('input#property-propertyGym'));
  propertyFunctionalAreaInput: ElementFinder = element(by.css('input#property-propertyFunctionalArea'));
  propertyOpen24hInput: ElementFinder = element(by.css('input#property-propertyOpen24h'));
  propertyCarParkInput: ElementFinder = element(by.css('input#property-propertyCarPark'));
  propertyBalconyInput: ElementFinder = element(by.css('input#property-propertyBalcony'));
  propertySaunaInput: ElementFinder = element(by.css('input#property-propertySauna'));
  propertySteamSaunaInput: ElementFinder = element(by.css('input#property-propertySteamSauna'));
  propertyAttractionInput: ElementFinder = element(by.css('input#property-propertyAttraction'));
  propertySpecialFeatureInput: ElementFinder = element(by.css('input#property-propertySpecialFeature'));
  propertyFurnitureOverviewInput: ElementFinder = element(by.css('input#property-propertyFurnitureOverview'));
  propertyLocationOverviewInput: ElementFinder = element(by.css('input#property-propertyLocationOverview'));
  propertyResidentialCommunityInput: ElementFinder = element(by.css('input#property-propertyResidentialCommunity'));
  propertyEducationalAspectInput: ElementFinder = element(by.css('input#property-propertyEducationalAspect'));
  propertyExtraInfoInput: ElementFinder = element(by.css('input#property-propertyExtraInfo'));
  propertyDraftInput: ElementFinder = element(by.css('input#file_propertyDraft'));
  longitudeInput: ElementFinder = element(by.css('input#property-longitude'));
  latitudeInput: ElementFinder = element(by.css('input#property-latitude'));
  propertyGoodPriceInput: ElementFinder = element(by.css('input#property-propertyGoodPrice'));
  propertySeenCountInput: ElementFinder = element(by.css('input#property-propertySeenCount'));
  propertyIsSoldInput: ElementFinder = element(by.css('input#property-propertyIsSold'));
  propertyIsRentInput: ElementFinder = element(by.css('input#property-propertyIsRent'));
  propertyAvailableInput: ElementFinder = element(by.css('input#property-propertyAvailable'));
  locationSelect: ElementFinder = element(by.css('select#property-location'));
  residentialAreaSelect: ElementFinder = element(by.css('select#property-residentialArea'));
  tagSelect: ElementFinder = element(by.css('select#property-tag'));
  buildingtypeSelect: ElementFinder = element(by.css('select#property-buildingtype'));
  photoSelect: ElementFinder = element(by.css('select#property-photo'));

  getPageTitle() {
    return this.pageTitle;
  }

  setPropertyCodeInput(propertyCode) {
    this.propertyCodeInput.sendKeys(propertyCode);
  }

  getPropertyCodeInput() {
    return this.propertyCodeInput.getAttribute('value');
  }

  setPropertyNameInput(propertyName) {
    this.propertyNameInput.sendKeys(propertyName);
  }

  getPropertyNameInput() {
    return this.propertyNameInput.getAttribute('value');
  }

  setPropertyAliasInput(propertyAlias) {
    this.propertyAliasInput.sendKeys(propertyAlias);
  }

  getPropertyAliasInput() {
    return this.propertyAliasInput.getAttribute('value');
  }

  setPropertyTransactionSelect(propertyTransaction) {
    this.propertyTransactionSelect.sendKeys(propertyTransaction);
  }

  getPropertyTransactionSelect() {
    return this.propertyTransactionSelect.element(by.css('option:checked')).getText();
  }

  propertyTransactionSelectLastOption() {
    this.propertyTransactionSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setPropertyNumberInput(propertyNumber) {
    this.propertyNumberInput.sendKeys(propertyNumber);
  }

  getPropertyNumberInput() {
    return this.propertyNumberInput.getAttribute('value');
  }

  setPropertyRoadInput(propertyRoad) {
    this.propertyRoadInput.sendKeys(propertyRoad);
  }

  getPropertyRoadInput() {
    return this.propertyRoadInput.getAttribute('value');
  }

  setPropertyWardInput(propertyWard) {
    this.propertyWardInput.sendKeys(propertyWard);
  }

  getPropertyWardInput() {
    return this.propertyWardInput.getAttribute('value');
  }

  setPropertyDistrictInput(propertyDistrict) {
    this.propertyDistrictInput.sendKeys(propertyDistrict);
  }

  getPropertyDistrictInput() {
    return this.propertyDistrictInput.getAttribute('value');
  }

  setPropertyProvinceInput(propertyProvince) {
    this.propertyProvinceInput.sendKeys(propertyProvince);
  }

  getPropertyProvinceInput() {
    return this.propertyProvinceInput.getAttribute('value');
  }

  setPropertyDescriptionInput(propertyDescription) {
    this.propertyDescriptionInput.sendKeys(propertyDescription);
  }

  getPropertyDescriptionInput() {
    return this.propertyDescriptionInput.getAttribute('value');
  }

  setPropertyBedRoomsInput(propertyBedRooms) {
    this.propertyBedRoomsInput.sendKeys(propertyBedRooms);
  }

  getPropertyBedRoomsInput() {
    return this.propertyBedRoomsInput.getAttribute('value');
  }

  setPropertyBathRoomsInput(propertyBathRooms) {
    this.propertyBathRoomsInput.sendKeys(propertyBathRooms);
  }

  getPropertyBathRoomsInput() {
    return this.propertyBathRoomsInput.getAttribute('value');
  }

  setPropertySquareInput(propertySquare) {
    this.propertySquareInput.sendKeys(propertySquare);
  }

  getPropertySquareInput() {
    return this.propertySquareInput.getAttribute('value');
  }

  setPropertyUsePurposeSelect(propertyUsePurpose) {
    this.propertyUsePurposeSelect.sendKeys(propertyUsePurpose);
  }

  getPropertyUsePurposeSelect() {
    return this.propertyUsePurposeSelect.element(by.css('option:checked')).getText();
  }

  propertyUsePurposeSelectLastOption() {
    this.propertyUsePurposeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setPropertyOwnerTypeInput(propertyOwnerType) {
    this.propertyOwnerTypeInput.sendKeys(propertyOwnerType);
  }

  getPropertyOwnerTypeInput() {
    return this.propertyOwnerTypeInput.getAttribute('value');
  }

  setPropertyTowerInput(propertyTower) {
    this.propertyTowerInput.sendKeys(propertyTower);
  }

  getPropertyTowerInput() {
    return this.propertyTowerInput.getAttribute('value');
  }

  setPropertyRentPriceInput(propertyRentPrice) {
    this.propertyRentPriceInput.sendKeys(propertyRentPrice);
  }

  getPropertyRentPriceInput() {
    return this.propertyRentPriceInput.getAttribute('value');
  }

  setPropertyRentUnitSelect(propertyRentUnit) {
    this.propertyRentUnitSelect.sendKeys(propertyRentUnit);
  }

  getPropertyRentUnitSelect() {
    return this.propertyRentUnitSelect.element(by.css('option:checked')).getText();
  }

  propertyRentUnitSelectLastOption() {
    this.propertyRentUnitSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setPropertyRentStartedDateInput(propertyRentStartedDate) {
    this.propertyRentStartedDateInput.sendKeys(propertyRentStartedDate);
  }

  getPropertyRentStartedDateInput() {
    return this.propertyRentStartedDateInput.getAttribute('value');
  }

  setPropertySellPriceInput(propertySellPrice) {
    this.propertySellPriceInput.sendKeys(propertySellPrice);
  }

  getPropertySellPriceInput() {
    return this.propertySellPriceInput.getAttribute('value');
  }

  setPropertySellUnitSelect(propertySellUnit) {
    this.propertySellUnitSelect.sendKeys(propertySellUnit);
  }

  getPropertySellUnitSelect() {
    return this.propertySellUnitSelect.element(by.css('option:checked')).getText();
  }

  propertySellUnitSelectLastOption() {
    this.propertySellUnitSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setPropertySellStartedDateInput(propertySellStartedDate) {
    this.propertySellStartedDateInput.sendKeys(propertySellStartedDate);
  }

  getPropertySellStartedDateInput() {
    return this.propertySellStartedDateInput.getAttribute('value');
  }

  getPropertySofaInput() {
    return this.propertySofaInput;
  }
  getPropertyDiningTableInput() {
    return this.propertyDiningTableInput;
  }
  getPropertyKitchenInput() {
    return this.propertyKitchenInput;
  }
  getPropertyCabinetKitchenInput() {
    return this.propertyCabinetKitchenInput;
  }
  getPropertyKitchenEquipmentInput() {
    return this.propertyKitchenEquipmentInput;
  }
  getPropertyWardrobeInput() {
    return this.propertyWardrobeInput;
  }
  getPropertyMakeupTableInput() {
    return this.propertyMakeupTableInput;
  }
  getPropertyDeskInput() {
    return this.propertyDeskInput;
  }
  getPropertyTiviInput() {
    return this.propertyTiviInput;
  }
  getPropertyWashingMachineInput() {
    return this.propertyWashingMachineInput;
  }
  getPropertyRefrigeratorInput() {
    return this.propertyRefrigeratorInput;
  }
  getPropertyAirconditionInput() {
    return this.propertyAirconditionInput;
  }
  getPropertyMicrowaveInput() {
    return this.propertyMicrowaveInput;
  }
  getPropertyWaterHeaterInput() {
    return this.propertyWaterHeaterInput;
  }
  getPropertyBedInput() {
    return this.propertyBedInput;
  }
  getPropertyHeaterInput() {
    return this.propertyHeaterInput;
  }
  getPropertyAudioEquipmentInput() {
    return this.propertyAudioEquipmentInput;
  }
  getPropertyInternetInput() {
    return this.propertyInternetInput;
  }
  getPropertyCableTiviInput() {
    return this.propertyCableTiviInput;
  }
  getPropertyPetPermissionInput() {
    return this.propertyPetPermissionInput;
  }
  getPropertyElevatorInput() {
    return this.propertyElevatorInput;
  }
  getPropertySwimmingPoolInput() {
    return this.propertySwimmingPoolInput;
  }
  getPropertyGymInput() {
    return this.propertyGymInput;
  }
  getPropertyFunctionalAreaInput() {
    return this.propertyFunctionalAreaInput;
  }
  getPropertyOpen24hInput() {
    return this.propertyOpen24hInput;
  }
  getPropertyCarParkInput() {
    return this.propertyCarParkInput;
  }
  getPropertyBalconyInput() {
    return this.propertyBalconyInput;
  }
  getPropertySaunaInput() {
    return this.propertySaunaInput;
  }
  getPropertySteamSaunaInput() {
    return this.propertySteamSaunaInput;
  }
  getPropertyAttractionInput() {
    return this.propertyAttractionInput;
  }
  setPropertySpecialFeatureInput(propertySpecialFeature) {
    this.propertySpecialFeatureInput.sendKeys(propertySpecialFeature);
  }

  getPropertySpecialFeatureInput() {
    return this.propertySpecialFeatureInput.getAttribute('value');
  }

  setPropertyFurnitureOverviewInput(propertyFurnitureOverview) {
    this.propertyFurnitureOverviewInput.sendKeys(propertyFurnitureOverview);
  }

  getPropertyFurnitureOverviewInput() {
    return this.propertyFurnitureOverviewInput.getAttribute('value');
  }

  setPropertyLocationOverviewInput(propertyLocationOverview) {
    this.propertyLocationOverviewInput.sendKeys(propertyLocationOverview);
  }

  getPropertyLocationOverviewInput() {
    return this.propertyLocationOverviewInput.getAttribute('value');
  }

  setPropertyResidentialCommunityInput(propertyResidentialCommunity) {
    this.propertyResidentialCommunityInput.sendKeys(propertyResidentialCommunity);
  }

  getPropertyResidentialCommunityInput() {
    return this.propertyResidentialCommunityInput.getAttribute('value');
  }

  setPropertyEducationalAspectInput(propertyEducationalAspect) {
    this.propertyEducationalAspectInput.sendKeys(propertyEducationalAspect);
  }

  getPropertyEducationalAspectInput() {
    return this.propertyEducationalAspectInput.getAttribute('value');
  }

  setPropertyExtraInfoInput(propertyExtraInfo) {
    this.propertyExtraInfoInput.sendKeys(propertyExtraInfo);
  }

  getPropertyExtraInfoInput() {
    return this.propertyExtraInfoInput.getAttribute('value');
  }

  setPropertyDraftInput(propertyDraft) {
    this.propertyDraftInput.sendKeys(propertyDraft);
  }

  getPropertyDraftInput() {
    return this.propertyDraftInput.getAttribute('value');
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

  getPropertyGoodPriceInput() {
    return this.propertyGoodPriceInput;
  }
  setPropertySeenCountInput(propertySeenCount) {
    this.propertySeenCountInput.sendKeys(propertySeenCount);
  }

  getPropertySeenCountInput() {
    return this.propertySeenCountInput.getAttribute('value');
  }

  getPropertyIsSoldInput() {
    return this.propertyIsSoldInput;
  }
  getPropertyIsRentInput() {
    return this.propertyIsRentInput;
  }
  getPropertyAvailableInput() {
    return this.propertyAvailableInput;
  }
  locationSelectLastOption() {
    this.locationSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  locationSelectOption(option) {
    this.locationSelect.sendKeys(option);
  }

  getLocationSelect() {
    return this.locationSelect;
  }

  getLocationSelectedOption() {
    return this.locationSelect.element(by.css('option:checked')).getText();
  }

  residentialAreaSelectLastOption() {
    this.residentialAreaSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  residentialAreaSelectOption(option) {
    this.residentialAreaSelect.sendKeys(option);
  }

  getResidentialAreaSelect() {
    return this.residentialAreaSelect;
  }

  getResidentialAreaSelectedOption() {
    return this.residentialAreaSelect.element(by.css('option:checked')).getText();
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

  buildingtypeSelectLastOption() {
    this.buildingtypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  buildingtypeSelectOption(option) {
    this.buildingtypeSelect.sendKeys(option);
  }

  getBuildingtypeSelect() {
    return this.buildingtypeSelect;
  }

  getBuildingtypeSelectedOption() {
    return this.buildingtypeSelect.element(by.css('option:checked')).getText();
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
