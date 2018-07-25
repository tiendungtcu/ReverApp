import { element, by, ElementFinder } from 'protractor';

export default class ProjectUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.project.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  projectNameInput: ElementFinder = element(by.css('input#project-projectName'));
  projectAliasInput: ElementFinder = element(by.css('input#project-projectAlias'));
  projectAvatarUrlInput: ElementFinder = element(by.css('input#project-projectAvatarUrl'));
  projectDistrictInput: ElementFinder = element(by.css('input#project-projectDistrict'));
  projectProvinceInput: ElementFinder = element(by.css('input#project-projectProvince'));
  residentialAreaIdInput: ElementFinder = element(by.css('input#project-residentialAreaId'));
  projectRoadInput: ElementFinder = element(by.css('input#project-projectRoad'));
  projectWardInput: ElementFinder = element(by.css('input#project-projectWard'));
  projectStatusSelect: ElementFinder = element(by.css('select#project-projectStatus'));
  projectNoBlocksInput: ElementFinder = element(by.css('input#project-projectNoBlocks'));
  projectNoFloorsInput: ElementFinder = element(by.css('input#project-projectNoFloors'));
  projectNoApartmentsInput: ElementFinder = element(by.css('input#project-projectNoApartments'));
  projectNoShophouseInput: ElementFinder = element(by.css('input#project-projectNoShophouse'));
  projectDescriptionInput: ElementFinder = element(by.css('input#project-projectDescription'));
  projectMinSellPriceInput: ElementFinder = element(by.css('input#project-projectMinSellPrice'));
  projectMaxSellPriceInput: ElementFinder = element(by.css('input#project-projectMaxSellPrice'));
  projectSellPriceUnitSelect: ElementFinder = element(by.css('select#project-projectSellPriceUnit'));
  projectMinRentPriceInput: ElementFinder = element(by.css('input#project-projectMinRentPrice'));
  projectMaxRentPriceInput: ElementFinder = element(by.css('input#project-projectMaxRentPrice'));
  projectRentPriceUnitSelect: ElementFinder = element(by.css('select#project-projectRentPriceUnit'));
  projectStartedDateInput: ElementFinder = element(by.css('input#project-projectStartedDate'));
  projectFinishingDateInput: ElementFinder = element(by.css('input#project-projectFinishingDate'));
  projectMinApartmentSquareInput: ElementFinder = element(by.css('input#project-projectMinApartmentSquare'));
  projectMaxApartmentSquareInput: ElementFinder = element(by.css('input#project-projectMaxApartmentSquare'));
  projectGreenSpaceInput: ElementFinder = element(by.css('input#project-projectGreenSpace'));
  projectBuildingDensityInput: ElementFinder = element(by.css('input#project-projectBuildingDensity'));
  projectDesignCompanyInput: ElementFinder = element(by.css('input#project-projectDesignCompany'));
  projectCarParkInput: ElementFinder = element(by.css('input#project-projectCarPark'));
  projectBbqCourtInput: ElementFinder = element(by.css('input#project-projectBbqCourt'));
  projectElevatorInput: ElementFinder = element(by.css('input#project-projectElevator'));
  projectShoppingCenterInput: ElementFinder = element(by.css('input#project-projectShoppingCenter'));
  projectSwimmingPoolInput: ElementFinder = element(by.css('input#project-projectSwimmingPool'));
  projectCommunityRoomInput: ElementFinder = element(by.css('input#project-projectCommunityRoom'));
  projectGymInput: ElementFinder = element(by.css('input#project-projectGym'));
  projectCityParkInput: ElementFinder = element(by.css('input#project-projectCityPark'));
  projectGuardInput: ElementFinder = element(by.css('input#project-projectGuard'));
  projectPlayGroundInput: ElementFinder = element(by.css('input#project-projectPlayGround'));
  longitudeInput: ElementFinder = element(by.css('input#project-longitude'));
  latitudeInput: ElementFinder = element(by.css('input#project-latitude'));
  projectSeenCountInput: ElementFinder = element(by.css('input#project-projectSeenCount'));
  projectAvailableInput: ElementFinder = element(by.css('input#project-projectAvailable'));
  locationSelect: ElementFinder = element(by.css('select#project-location'));
  consultantSelect: ElementFinder = element(by.css('select#project-consultant'));
  tagSelect: ElementFinder = element(by.css('select#project-tag'));
  buildingtypeSelect: ElementFinder = element(by.css('select#project-buildingtype'));
  inverstorSelect: ElementFinder = element(by.css('select#project-inverstor'));
  contractorSelect: ElementFinder = element(by.css('select#project-contractor'));

  getPageTitle() {
    return this.pageTitle;
  }

  setProjectNameInput(projectName) {
    this.projectNameInput.sendKeys(projectName);
  }

  getProjectNameInput() {
    return this.projectNameInput.getAttribute('value');
  }

  setProjectAliasInput(projectAlias) {
    this.projectAliasInput.sendKeys(projectAlias);
  }

  getProjectAliasInput() {
    return this.projectAliasInput.getAttribute('value');
  }

  setProjectAvatarUrlInput(projectAvatarUrl) {
    this.projectAvatarUrlInput.sendKeys(projectAvatarUrl);
  }

  getProjectAvatarUrlInput() {
    return this.projectAvatarUrlInput.getAttribute('value');
  }

  setProjectDistrictInput(projectDistrict) {
    this.projectDistrictInput.sendKeys(projectDistrict);
  }

  getProjectDistrictInput() {
    return this.projectDistrictInput.getAttribute('value');
  }

  setProjectProvinceInput(projectProvince) {
    this.projectProvinceInput.sendKeys(projectProvince);
  }

  getProjectProvinceInput() {
    return this.projectProvinceInput.getAttribute('value');
  }

  setResidentialAreaIdInput(residentialAreaId) {
    this.residentialAreaIdInput.sendKeys(residentialAreaId);
  }

  getResidentialAreaIdInput() {
    return this.residentialAreaIdInput.getAttribute('value');
  }

  setProjectRoadInput(projectRoad) {
    this.projectRoadInput.sendKeys(projectRoad);
  }

  getProjectRoadInput() {
    return this.projectRoadInput.getAttribute('value');
  }

  setProjectWardInput(projectWard) {
    this.projectWardInput.sendKeys(projectWard);
  }

  getProjectWardInput() {
    return this.projectWardInput.getAttribute('value');
  }

  setProjectStatusSelect(projectStatus) {
    this.projectStatusSelect.sendKeys(projectStatus);
  }

  getProjectStatusSelect() {
    return this.projectStatusSelect.element(by.css('option:checked')).getText();
  }

  projectStatusSelectLastOption() {
    this.projectStatusSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setProjectNoBlocksInput(projectNoBlocks) {
    this.projectNoBlocksInput.sendKeys(projectNoBlocks);
  }

  getProjectNoBlocksInput() {
    return this.projectNoBlocksInput.getAttribute('value');
  }

  setProjectNoFloorsInput(projectNoFloors) {
    this.projectNoFloorsInput.sendKeys(projectNoFloors);
  }

  getProjectNoFloorsInput() {
    return this.projectNoFloorsInput.getAttribute('value');
  }

  setProjectNoApartmentsInput(projectNoApartments) {
    this.projectNoApartmentsInput.sendKeys(projectNoApartments);
  }

  getProjectNoApartmentsInput() {
    return this.projectNoApartmentsInput.getAttribute('value');
  }

  setProjectNoShophouseInput(projectNoShophouse) {
    this.projectNoShophouseInput.sendKeys(projectNoShophouse);
  }

  getProjectNoShophouseInput() {
    return this.projectNoShophouseInput.getAttribute('value');
  }

  setProjectDescriptionInput(projectDescription) {
    this.projectDescriptionInput.sendKeys(projectDescription);
  }

  getProjectDescriptionInput() {
    return this.projectDescriptionInput.getAttribute('value');
  }

  setProjectMinSellPriceInput(projectMinSellPrice) {
    this.projectMinSellPriceInput.sendKeys(projectMinSellPrice);
  }

  getProjectMinSellPriceInput() {
    return this.projectMinSellPriceInput.getAttribute('value');
  }

  setProjectMaxSellPriceInput(projectMaxSellPrice) {
    this.projectMaxSellPriceInput.sendKeys(projectMaxSellPrice);
  }

  getProjectMaxSellPriceInput() {
    return this.projectMaxSellPriceInput.getAttribute('value');
  }

  setProjectSellPriceUnitSelect(projectSellPriceUnit) {
    this.projectSellPriceUnitSelect.sendKeys(projectSellPriceUnit);
  }

  getProjectSellPriceUnitSelect() {
    return this.projectSellPriceUnitSelect.element(by.css('option:checked')).getText();
  }

  projectSellPriceUnitSelectLastOption() {
    this.projectSellPriceUnitSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setProjectMinRentPriceInput(projectMinRentPrice) {
    this.projectMinRentPriceInput.sendKeys(projectMinRentPrice);
  }

  getProjectMinRentPriceInput() {
    return this.projectMinRentPriceInput.getAttribute('value');
  }

  setProjectMaxRentPriceInput(projectMaxRentPrice) {
    this.projectMaxRentPriceInput.sendKeys(projectMaxRentPrice);
  }

  getProjectMaxRentPriceInput() {
    return this.projectMaxRentPriceInput.getAttribute('value');
  }

  setProjectRentPriceUnitSelect(projectRentPriceUnit) {
    this.projectRentPriceUnitSelect.sendKeys(projectRentPriceUnit);
  }

  getProjectRentPriceUnitSelect() {
    return this.projectRentPriceUnitSelect.element(by.css('option:checked')).getText();
  }

  projectRentPriceUnitSelectLastOption() {
    this.projectRentPriceUnitSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setProjectStartedDateInput(projectStartedDate) {
    this.projectStartedDateInput.sendKeys(projectStartedDate);
  }

  getProjectStartedDateInput() {
    return this.projectStartedDateInput.getAttribute('value');
  }

  setProjectFinishingDateInput(projectFinishingDate) {
    this.projectFinishingDateInput.sendKeys(projectFinishingDate);
  }

  getProjectFinishingDateInput() {
    return this.projectFinishingDateInput.getAttribute('value');
  }

  setProjectMinApartmentSquareInput(projectMinApartmentSquare) {
    this.projectMinApartmentSquareInput.sendKeys(projectMinApartmentSquare);
  }

  getProjectMinApartmentSquareInput() {
    return this.projectMinApartmentSquareInput.getAttribute('value');
  }

  setProjectMaxApartmentSquareInput(projectMaxApartmentSquare) {
    this.projectMaxApartmentSquareInput.sendKeys(projectMaxApartmentSquare);
  }

  getProjectMaxApartmentSquareInput() {
    return this.projectMaxApartmentSquareInput.getAttribute('value');
  }

  setProjectGreenSpaceInput(projectGreenSpace) {
    this.projectGreenSpaceInput.sendKeys(projectGreenSpace);
  }

  getProjectGreenSpaceInput() {
    return this.projectGreenSpaceInput.getAttribute('value');
  }

  setProjectBuildingDensityInput(projectBuildingDensity) {
    this.projectBuildingDensityInput.sendKeys(projectBuildingDensity);
  }

  getProjectBuildingDensityInput() {
    return this.projectBuildingDensityInput.getAttribute('value');
  }

  setProjectDesignCompanyInput(projectDesignCompany) {
    this.projectDesignCompanyInput.sendKeys(projectDesignCompany);
  }

  getProjectDesignCompanyInput() {
    return this.projectDesignCompanyInput.getAttribute('value');
  }

  getProjectCarParkInput() {
    return this.projectCarParkInput;
  }
  getProjectBbqCourtInput() {
    return this.projectBbqCourtInput;
  }
  getProjectElevatorInput() {
    return this.projectElevatorInput;
  }
  getProjectShoppingCenterInput() {
    return this.projectShoppingCenterInput;
  }
  getProjectSwimmingPoolInput() {
    return this.projectSwimmingPoolInput;
  }
  getProjectCommunityRoomInput() {
    return this.projectCommunityRoomInput;
  }
  getProjectGymInput() {
    return this.projectGymInput;
  }
  getProjectCityParkInput() {
    return this.projectCityParkInput;
  }
  getProjectGuardInput() {
    return this.projectGuardInput;
  }
  getProjectPlayGroundInput() {
    return this.projectPlayGroundInput;
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

  setProjectSeenCountInput(projectSeenCount) {
    this.projectSeenCountInput.sendKeys(projectSeenCount);
  }

  getProjectSeenCountInput() {
    return this.projectSeenCountInput.getAttribute('value');
  }

  getProjectAvailableInput() {
    return this.projectAvailableInput;
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

  consultantSelectLastOption() {
    this.consultantSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  consultantSelectOption(option) {
    this.consultantSelect.sendKeys(option);
  }

  getConsultantSelect() {
    return this.consultantSelect;
  }

  getConsultantSelectedOption() {
    return this.consultantSelect.element(by.css('option:checked')).getText();
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

  inverstorSelectLastOption() {
    this.inverstorSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  inverstorSelectOption(option) {
    this.inverstorSelect.sendKeys(option);
  }

  getInverstorSelect() {
    return this.inverstorSelect;
  }

  getInverstorSelectedOption() {
    return this.inverstorSelect.element(by.css('option:checked')).getText();
  }

  contractorSelectLastOption() {
    this.contractorSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  contractorSelectOption(option) {
    this.contractorSelect.sendKeys(option);
  }

  getContractorSelect() {
    return this.contractorSelect;
  }

  getContractorSelectedOption() {
    return this.contractorSelect.element(by.css('option:checked')).getText();
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
