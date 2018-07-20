/* tslint:disable no-unused-expression */
import { browser, protractor } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import PropertyComponentsPage from './property.page-object';
import PropertyUpdatePage from './property-update.page-object';
import path from 'path';

const expect = chai.expect;

describe('Property e2e test', () => {
  let navBarPage: NavBarPage;
  let propertyUpdatePage: PropertyUpdatePage;
  let propertyComponentsPage: PropertyComponentsPage;
  const fileToUpload = '../../../../../main/webapp/static/images/logo-jhipster.png';
  const absolutePath = path.resolve(__dirname, fileToUpload);

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Properties', async () => {
    navBarPage.getEntityPage('property');
    propertyComponentsPage = new PropertyComponentsPage();
    expect(await propertyComponentsPage.getTitle().getText()).to.match(/Properties/);
  });

  it('should load create Property page', async () => {
    propertyComponentsPage.clickOnCreateButton();
    propertyUpdatePage = new PropertyUpdatePage();
    expect(await propertyUpdatePage.getPageTitle().getAttribute('id')).to.match(/riverApp.property.home.createOrEditLabel/);
  });

  it('should create and save Properties', async () => {
    propertyUpdatePage.setPropertyCodeInput('propertyCode');
    expect(await propertyUpdatePage.getPropertyCodeInput()).to.match(/propertyCode/);
    propertyUpdatePage.setPropertyNameInput('propertyName');
    expect(await propertyUpdatePage.getPropertyNameInput()).to.match(/propertyName/);
    propertyUpdatePage.setPropertyAliasInput('propertyAlias');
    expect(await propertyUpdatePage.getPropertyAliasInput()).to.match(/propertyAlias/);
    propertyUpdatePage.propertyTransactionSelectLastOption();
    propertyUpdatePage.setPropertyNumberInput('propertyNumber');
    expect(await propertyUpdatePage.getPropertyNumberInput()).to.match(/propertyNumber/);
    propertyUpdatePage.setPropertyRoadInput('propertyRoad');
    expect(await propertyUpdatePage.getPropertyRoadInput()).to.match(/propertyRoad/);
    propertyUpdatePage.setPropertyWardInput('propertyWard');
    expect(await propertyUpdatePage.getPropertyWardInput()).to.match(/propertyWard/);
    propertyUpdatePage.setPropertyDistrictInput('propertyDistrict');
    expect(await propertyUpdatePage.getPropertyDistrictInput()).to.match(/propertyDistrict/);
    propertyUpdatePage.setPropertyProvinceInput('propertyProvince');
    expect(await propertyUpdatePage.getPropertyProvinceInput()).to.match(/propertyProvince/);
    propertyUpdatePage.setPropertyDescriptionInput('propertyDescription');
    expect(await propertyUpdatePage.getPropertyDescriptionInput()).to.match(/propertyDescription/);
    propertyUpdatePage.setPropertyBedRoomsInput('5');
    expect(await propertyUpdatePage.getPropertyBedRoomsInput()).to.eq('5');
    propertyUpdatePage.setPropertyBathRoomsInput('5');
    expect(await propertyUpdatePage.getPropertyBathRoomsInput()).to.eq('5');
    propertyUpdatePage.setPropertySquareInput('5');
    expect(await propertyUpdatePage.getPropertySquareInput()).to.eq('5');
    propertyUpdatePage.propertyUsePurposeSelectLastOption();
    propertyUpdatePage.setPropertyOwnerTypeInput('propertyOwnerType');
    expect(await propertyUpdatePage.getPropertyOwnerTypeInput()).to.match(/propertyOwnerType/);
    propertyUpdatePage.setPropertyTowerInput('propertyTower');
    expect(await propertyUpdatePage.getPropertyTowerInput()).to.match(/propertyTower/);
    propertyUpdatePage.setPropertyRentPriceInput('5');
    expect(await propertyUpdatePage.getPropertyRentPriceInput()).to.eq('5');
    propertyUpdatePage.propertyRentUnitSelectLastOption();
    propertyUpdatePage.setPropertyRentStartedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await propertyUpdatePage.getPropertyRentStartedDateInput()).to.contain('2001-01-01T02:30');
    propertyUpdatePage.setPropertySellPriceInput('5');
    expect(await propertyUpdatePage.getPropertySellPriceInput()).to.eq('5');
    propertyUpdatePage.propertySellUnitSelectLastOption();
    propertyUpdatePage.setPropertySellStartedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await propertyUpdatePage.getPropertySellStartedDateInput()).to.contain('2001-01-01T02:30');
    const selectedPropertySofa = await propertyUpdatePage.getPropertySofaInput().isSelected();
    if (selectedPropertySofa) {
      propertyUpdatePage.getPropertySofaInput().click();
      expect(await propertyUpdatePage.getPropertySofaInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertySofaInput().click();
      expect(await propertyUpdatePage.getPropertySofaInput().isSelected()).to.be.true;
    }
    const selectedPropertyDiningTable = await propertyUpdatePage.getPropertyDiningTableInput().isSelected();
    if (selectedPropertyDiningTable) {
      propertyUpdatePage.getPropertyDiningTableInput().click();
      expect(await propertyUpdatePage.getPropertyDiningTableInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyDiningTableInput().click();
      expect(await propertyUpdatePage.getPropertyDiningTableInput().isSelected()).to.be.true;
    }
    const selectedPropertyKitchen = await propertyUpdatePage.getPropertyKitchenInput().isSelected();
    if (selectedPropertyKitchen) {
      propertyUpdatePage.getPropertyKitchenInput().click();
      expect(await propertyUpdatePage.getPropertyKitchenInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyKitchenInput().click();
      expect(await propertyUpdatePage.getPropertyKitchenInput().isSelected()).to.be.true;
    }
    const selectedPropertyCabinetKitchen = await propertyUpdatePage.getPropertyCabinetKitchenInput().isSelected();
    if (selectedPropertyCabinetKitchen) {
      propertyUpdatePage.getPropertyCabinetKitchenInput().click();
      expect(await propertyUpdatePage.getPropertyCabinetKitchenInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyCabinetKitchenInput().click();
      expect(await propertyUpdatePage.getPropertyCabinetKitchenInput().isSelected()).to.be.true;
    }
    const selectedPropertyKitchenEquipment = await propertyUpdatePage.getPropertyKitchenEquipmentInput().isSelected();
    if (selectedPropertyKitchenEquipment) {
      propertyUpdatePage.getPropertyKitchenEquipmentInput().click();
      expect(await propertyUpdatePage.getPropertyKitchenEquipmentInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyKitchenEquipmentInput().click();
      expect(await propertyUpdatePage.getPropertyKitchenEquipmentInput().isSelected()).to.be.true;
    }
    const selectedPropertyWardrobe = await propertyUpdatePage.getPropertyWardrobeInput().isSelected();
    if (selectedPropertyWardrobe) {
      propertyUpdatePage.getPropertyWardrobeInput().click();
      expect(await propertyUpdatePage.getPropertyWardrobeInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyWardrobeInput().click();
      expect(await propertyUpdatePage.getPropertyWardrobeInput().isSelected()).to.be.true;
    }
    const selectedPropertyMakeupTable = await propertyUpdatePage.getPropertyMakeupTableInput().isSelected();
    if (selectedPropertyMakeupTable) {
      propertyUpdatePage.getPropertyMakeupTableInput().click();
      expect(await propertyUpdatePage.getPropertyMakeupTableInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyMakeupTableInput().click();
      expect(await propertyUpdatePage.getPropertyMakeupTableInput().isSelected()).to.be.true;
    }
    const selectedPropertyDesk = await propertyUpdatePage.getPropertyDeskInput().isSelected();
    if (selectedPropertyDesk) {
      propertyUpdatePage.getPropertyDeskInput().click();
      expect(await propertyUpdatePage.getPropertyDeskInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyDeskInput().click();
      expect(await propertyUpdatePage.getPropertyDeskInput().isSelected()).to.be.true;
    }
    const selectedPropertyTivi = await propertyUpdatePage.getPropertyTiviInput().isSelected();
    if (selectedPropertyTivi) {
      propertyUpdatePage.getPropertyTiviInput().click();
      expect(await propertyUpdatePage.getPropertyTiviInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyTiviInput().click();
      expect(await propertyUpdatePage.getPropertyTiviInput().isSelected()).to.be.true;
    }
    const selectedPropertyWashingMachine = await propertyUpdatePage.getPropertyWashingMachineInput().isSelected();
    if (selectedPropertyWashingMachine) {
      propertyUpdatePage.getPropertyWashingMachineInput().click();
      expect(await propertyUpdatePage.getPropertyWashingMachineInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyWashingMachineInput().click();
      expect(await propertyUpdatePage.getPropertyWashingMachineInput().isSelected()).to.be.true;
    }
    const selectedPropertyRefrigerator = await propertyUpdatePage.getPropertyRefrigeratorInput().isSelected();
    if (selectedPropertyRefrigerator) {
      propertyUpdatePage.getPropertyRefrigeratorInput().click();
      expect(await propertyUpdatePage.getPropertyRefrigeratorInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyRefrigeratorInput().click();
      expect(await propertyUpdatePage.getPropertyRefrigeratorInput().isSelected()).to.be.true;
    }
    const selectedPropertyAircondition = await propertyUpdatePage.getPropertyAirconditionInput().isSelected();
    if (selectedPropertyAircondition) {
      propertyUpdatePage.getPropertyAirconditionInput().click();
      expect(await propertyUpdatePage.getPropertyAirconditionInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyAirconditionInput().click();
      expect(await propertyUpdatePage.getPropertyAirconditionInput().isSelected()).to.be.true;
    }
    const selectedPropertyMicrowave = await propertyUpdatePage.getPropertyMicrowaveInput().isSelected();
    if (selectedPropertyMicrowave) {
      propertyUpdatePage.getPropertyMicrowaveInput().click();
      expect(await propertyUpdatePage.getPropertyMicrowaveInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyMicrowaveInput().click();
      expect(await propertyUpdatePage.getPropertyMicrowaveInput().isSelected()).to.be.true;
    }
    const selectedPropertyWaterHeater = await propertyUpdatePage.getPropertyWaterHeaterInput().isSelected();
    if (selectedPropertyWaterHeater) {
      propertyUpdatePage.getPropertyWaterHeaterInput().click();
      expect(await propertyUpdatePage.getPropertyWaterHeaterInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyWaterHeaterInput().click();
      expect(await propertyUpdatePage.getPropertyWaterHeaterInput().isSelected()).to.be.true;
    }
    const selectedPropertyBed = await propertyUpdatePage.getPropertyBedInput().isSelected();
    if (selectedPropertyBed) {
      propertyUpdatePage.getPropertyBedInput().click();
      expect(await propertyUpdatePage.getPropertyBedInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyBedInput().click();
      expect(await propertyUpdatePage.getPropertyBedInput().isSelected()).to.be.true;
    }
    const selectedPropertyHeater = await propertyUpdatePage.getPropertyHeaterInput().isSelected();
    if (selectedPropertyHeater) {
      propertyUpdatePage.getPropertyHeaterInput().click();
      expect(await propertyUpdatePage.getPropertyHeaterInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyHeaterInput().click();
      expect(await propertyUpdatePage.getPropertyHeaterInput().isSelected()).to.be.true;
    }
    const selectedPropertyAudioEquipment = await propertyUpdatePage.getPropertyAudioEquipmentInput().isSelected();
    if (selectedPropertyAudioEquipment) {
      propertyUpdatePage.getPropertyAudioEquipmentInput().click();
      expect(await propertyUpdatePage.getPropertyAudioEquipmentInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyAudioEquipmentInput().click();
      expect(await propertyUpdatePage.getPropertyAudioEquipmentInput().isSelected()).to.be.true;
    }
    const selectedPropertyInternet = await propertyUpdatePage.getPropertyInternetInput().isSelected();
    if (selectedPropertyInternet) {
      propertyUpdatePage.getPropertyInternetInput().click();
      expect(await propertyUpdatePage.getPropertyInternetInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyInternetInput().click();
      expect(await propertyUpdatePage.getPropertyInternetInput().isSelected()).to.be.true;
    }
    const selectedPropertyCableTivi = await propertyUpdatePage.getPropertyCableTiviInput().isSelected();
    if (selectedPropertyCableTivi) {
      propertyUpdatePage.getPropertyCableTiviInput().click();
      expect(await propertyUpdatePage.getPropertyCableTiviInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyCableTiviInput().click();
      expect(await propertyUpdatePage.getPropertyCableTiviInput().isSelected()).to.be.true;
    }
    const selectedPropertyPetPermission = await propertyUpdatePage.getPropertyPetPermissionInput().isSelected();
    if (selectedPropertyPetPermission) {
      propertyUpdatePage.getPropertyPetPermissionInput().click();
      expect(await propertyUpdatePage.getPropertyPetPermissionInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyPetPermissionInput().click();
      expect(await propertyUpdatePage.getPropertyPetPermissionInput().isSelected()).to.be.true;
    }
    const selectedPropertyElevator = await propertyUpdatePage.getPropertyElevatorInput().isSelected();
    if (selectedPropertyElevator) {
      propertyUpdatePage.getPropertyElevatorInput().click();
      expect(await propertyUpdatePage.getPropertyElevatorInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyElevatorInput().click();
      expect(await propertyUpdatePage.getPropertyElevatorInput().isSelected()).to.be.true;
    }
    const selectedPropertySwimmingPool = await propertyUpdatePage.getPropertySwimmingPoolInput().isSelected();
    if (selectedPropertySwimmingPool) {
      propertyUpdatePage.getPropertySwimmingPoolInput().click();
      expect(await propertyUpdatePage.getPropertySwimmingPoolInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertySwimmingPoolInput().click();
      expect(await propertyUpdatePage.getPropertySwimmingPoolInput().isSelected()).to.be.true;
    }
    const selectedPropertyGym = await propertyUpdatePage.getPropertyGymInput().isSelected();
    if (selectedPropertyGym) {
      propertyUpdatePage.getPropertyGymInput().click();
      expect(await propertyUpdatePage.getPropertyGymInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyGymInput().click();
      expect(await propertyUpdatePage.getPropertyGymInput().isSelected()).to.be.true;
    }
    const selectedPropertyFunctionalArea = await propertyUpdatePage.getPropertyFunctionalAreaInput().isSelected();
    if (selectedPropertyFunctionalArea) {
      propertyUpdatePage.getPropertyFunctionalAreaInput().click();
      expect(await propertyUpdatePage.getPropertyFunctionalAreaInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyFunctionalAreaInput().click();
      expect(await propertyUpdatePage.getPropertyFunctionalAreaInput().isSelected()).to.be.true;
    }
    const selectedPropertyOpen24h = await propertyUpdatePage.getPropertyOpen24hInput().isSelected();
    if (selectedPropertyOpen24h) {
      propertyUpdatePage.getPropertyOpen24hInput().click();
      expect(await propertyUpdatePage.getPropertyOpen24hInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyOpen24hInput().click();
      expect(await propertyUpdatePage.getPropertyOpen24hInput().isSelected()).to.be.true;
    }
    const selectedPropertyCarPark = await propertyUpdatePage.getPropertyCarParkInput().isSelected();
    if (selectedPropertyCarPark) {
      propertyUpdatePage.getPropertyCarParkInput().click();
      expect(await propertyUpdatePage.getPropertyCarParkInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyCarParkInput().click();
      expect(await propertyUpdatePage.getPropertyCarParkInput().isSelected()).to.be.true;
    }
    const selectedPropertyBalcony = await propertyUpdatePage.getPropertyBalconyInput().isSelected();
    if (selectedPropertyBalcony) {
      propertyUpdatePage.getPropertyBalconyInput().click();
      expect(await propertyUpdatePage.getPropertyBalconyInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyBalconyInput().click();
      expect(await propertyUpdatePage.getPropertyBalconyInput().isSelected()).to.be.true;
    }
    const selectedPropertySauna = await propertyUpdatePage.getPropertySaunaInput().isSelected();
    if (selectedPropertySauna) {
      propertyUpdatePage.getPropertySaunaInput().click();
      expect(await propertyUpdatePage.getPropertySaunaInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertySaunaInput().click();
      expect(await propertyUpdatePage.getPropertySaunaInput().isSelected()).to.be.true;
    }
    const selectedPropertySteamSauna = await propertyUpdatePage.getPropertySteamSaunaInput().isSelected();
    if (selectedPropertySteamSauna) {
      propertyUpdatePage.getPropertySteamSaunaInput().click();
      expect(await propertyUpdatePage.getPropertySteamSaunaInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertySteamSaunaInput().click();
      expect(await propertyUpdatePage.getPropertySteamSaunaInput().isSelected()).to.be.true;
    }
    const selectedPropertyAttraction = await propertyUpdatePage.getPropertyAttractionInput().isSelected();
    if (selectedPropertyAttraction) {
      propertyUpdatePage.getPropertyAttractionInput().click();
      expect(await propertyUpdatePage.getPropertyAttractionInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyAttractionInput().click();
      expect(await propertyUpdatePage.getPropertyAttractionInput().isSelected()).to.be.true;
    }
    propertyUpdatePage.setPropertySpecialFeatureInput('propertySpecialFeature');
    expect(await propertyUpdatePage.getPropertySpecialFeatureInput()).to.match(/propertySpecialFeature/);
    propertyUpdatePage.setPropertyFurnitureOverviewInput('propertyFurnitureOverview');
    expect(await propertyUpdatePage.getPropertyFurnitureOverviewInput()).to.match(/propertyFurnitureOverview/);
    propertyUpdatePage.setPropertyLocationOverviewInput('propertyLocationOverview');
    expect(await propertyUpdatePage.getPropertyLocationOverviewInput()).to.match(/propertyLocationOverview/);
    propertyUpdatePage.setPropertyResidentialCommunityInput('propertyResidentialCommunity');
    expect(await propertyUpdatePage.getPropertyResidentialCommunityInput()).to.match(/propertyResidentialCommunity/);
    propertyUpdatePage.setPropertyEducationalAspectInput('propertyEducationalAspect');
    expect(await propertyUpdatePage.getPropertyEducationalAspectInput()).to.match(/propertyEducationalAspect/);
    propertyUpdatePage.setPropertyExtraInfoInput('propertyExtraInfo');
    expect(await propertyUpdatePage.getPropertyExtraInfoInput()).to.match(/propertyExtraInfo/);
    propertyUpdatePage.setPropertyDraftInput(absolutePath);
    propertyUpdatePage.setLongitudeInput('5');
    expect(await propertyUpdatePage.getLongitudeInput()).to.eq('5');
    propertyUpdatePage.setLatitudeInput('5');
    expect(await propertyUpdatePage.getLatitudeInput()).to.eq('5');
    const selectedPropertyGoodPrice = await propertyUpdatePage.getPropertyGoodPriceInput().isSelected();
    if (selectedPropertyGoodPrice) {
      propertyUpdatePage.getPropertyGoodPriceInput().click();
      expect(await propertyUpdatePage.getPropertyGoodPriceInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyGoodPriceInput().click();
      expect(await propertyUpdatePage.getPropertyGoodPriceInput().isSelected()).to.be.true;
    }
    propertyUpdatePage.setPropertySeenCountInput('5');
    expect(await propertyUpdatePage.getPropertySeenCountInput()).to.eq('5');
    const selectedPropertyIsSold = await propertyUpdatePage.getPropertyIsSoldInput().isSelected();
    if (selectedPropertyIsSold) {
      propertyUpdatePage.getPropertyIsSoldInput().click();
      expect(await propertyUpdatePage.getPropertyIsSoldInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyIsSoldInput().click();
      expect(await propertyUpdatePage.getPropertyIsSoldInput().isSelected()).to.be.true;
    }
    const selectedPropertyIsRent = await propertyUpdatePage.getPropertyIsRentInput().isSelected();
    if (selectedPropertyIsRent) {
      propertyUpdatePage.getPropertyIsRentInput().click();
      expect(await propertyUpdatePage.getPropertyIsRentInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyIsRentInput().click();
      expect(await propertyUpdatePage.getPropertyIsRentInput().isSelected()).to.be.true;
    }
    const selectedPropertyAvailable = await propertyUpdatePage.getPropertyAvailableInput().isSelected();
    if (selectedPropertyAvailable) {
      propertyUpdatePage.getPropertyAvailableInput().click();
      expect(await propertyUpdatePage.getPropertyAvailableInput().isSelected()).to.be.false;
    } else {
      propertyUpdatePage.getPropertyAvailableInput().click();
      expect(await propertyUpdatePage.getPropertyAvailableInput().isSelected()).to.be.true;
    }
    propertyUpdatePage.locationSelectLastOption();
    propertyUpdatePage.residentialAreaSelectLastOption();
    // propertyUpdatePage.tagSelectLastOption();
    // propertyUpdatePage.buildingtypeSelectLastOption();
    // propertyUpdatePage.photoSelectLastOption();
    await propertyUpdatePage.save();
    expect(await propertyUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
