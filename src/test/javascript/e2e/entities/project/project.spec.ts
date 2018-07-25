/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import ProjectComponentsPage from './project.page-object';
import ProjectUpdatePage from './project-update.page-object';

const expect = chai.expect;

describe('Project e2e test', () => {
  let navBarPage: NavBarPage;
  let projectUpdatePage: ProjectUpdatePage;
  let projectComponentsPage: ProjectComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Projects', async () => {
    navBarPage.getEntityPage('project');
    projectComponentsPage = new ProjectComponentsPage();
    expect(await projectComponentsPage.getTitle().getText()).to.match(/Projects/);
  });

  it('should load create Project page', async () => {
    projectComponentsPage.clickOnCreateButton();
    projectUpdatePage = new ProjectUpdatePage();
    expect(await projectUpdatePage.getPageTitle().getAttribute('id')).to.match(/riverApp.project.home.createOrEditLabel/);
  });

  it('should create and save Projects', async () => {
    projectUpdatePage.setProjectNameInput('projectName');
    expect(await projectUpdatePage.getProjectNameInput()).to.match(/projectName/);
    projectUpdatePage.setProjectAliasInput('projectAlias');
    expect(await projectUpdatePage.getProjectAliasInput()).to.match(/projectAlias/);
    projectUpdatePage.setProjectAvatarUrlInput('projectAvatarUrl');
    expect(await projectUpdatePage.getProjectAvatarUrlInput()).to.match(/projectAvatarUrl/);
    projectUpdatePage.setProjectDistrictInput('projectDistrict');
    expect(await projectUpdatePage.getProjectDistrictInput()).to.match(/projectDistrict/);
    projectUpdatePage.setProjectProvinceInput('projectProvince');
    expect(await projectUpdatePage.getProjectProvinceInput()).to.match(/projectProvince/);
    projectUpdatePage.setResidentialAreaIdInput('5');
    expect(await projectUpdatePage.getResidentialAreaIdInput()).to.eq('5');
    projectUpdatePage.setProjectRoadInput('projectRoad');
    expect(await projectUpdatePage.getProjectRoadInput()).to.match(/projectRoad/);
    projectUpdatePage.setProjectWardInput('projectWard');
    expect(await projectUpdatePage.getProjectWardInput()).to.match(/projectWard/);
    projectUpdatePage.projectStatusSelectLastOption();
    projectUpdatePage.setProjectNoBlocksInput('5');
    expect(await projectUpdatePage.getProjectNoBlocksInput()).to.eq('5');
    projectUpdatePage.setProjectNoFloorsInput('5');
    expect(await projectUpdatePage.getProjectNoFloorsInput()).to.eq('5');
    projectUpdatePage.setProjectNoApartmentsInput('5');
    expect(await projectUpdatePage.getProjectNoApartmentsInput()).to.eq('5');
    projectUpdatePage.setProjectNoShophouseInput('5');
    expect(await projectUpdatePage.getProjectNoShophouseInput()).to.eq('5');
    projectUpdatePage.setProjectDescriptionInput('projectDescription');
    expect(await projectUpdatePage.getProjectDescriptionInput()).to.match(/projectDescription/);
    projectUpdatePage.setProjectMinSellPriceInput('5');
    expect(await projectUpdatePage.getProjectMinSellPriceInput()).to.eq('5');
    projectUpdatePage.setProjectMaxSellPriceInput('5');
    expect(await projectUpdatePage.getProjectMaxSellPriceInput()).to.eq('5');
    projectUpdatePage.projectSellPriceUnitSelectLastOption();
    projectUpdatePage.setProjectMinRentPriceInput('5');
    expect(await projectUpdatePage.getProjectMinRentPriceInput()).to.eq('5');
    projectUpdatePage.setProjectMaxRentPriceInput('5');
    expect(await projectUpdatePage.getProjectMaxRentPriceInput()).to.eq('5');
    projectUpdatePage.projectRentPriceUnitSelectLastOption();
    projectUpdatePage.setProjectStartedDateInput('01-01-2001');
    expect(await projectUpdatePage.getProjectStartedDateInput()).to.eq('2001-01-01');
    projectUpdatePage.setProjectFinishingDateInput('01-01-2001');
    expect(await projectUpdatePage.getProjectFinishingDateInput()).to.eq('2001-01-01');
    projectUpdatePage.setProjectMinApartmentSquareInput('5');
    expect(await projectUpdatePage.getProjectMinApartmentSquareInput()).to.eq('5');
    projectUpdatePage.setProjectMaxApartmentSquareInput('5');
    expect(await projectUpdatePage.getProjectMaxApartmentSquareInput()).to.eq('5');
    projectUpdatePage.setProjectGreenSpaceInput('5');
    expect(await projectUpdatePage.getProjectGreenSpaceInput()).to.eq('5');
    projectUpdatePage.setProjectBuildingDensityInput('5');
    expect(await projectUpdatePage.getProjectBuildingDensityInput()).to.eq('5');
    projectUpdatePage.setProjectDesignCompanyInput('projectDesignCompany');
    expect(await projectUpdatePage.getProjectDesignCompanyInput()).to.match(/projectDesignCompany/);
    const selectedProjectCarPark = await projectUpdatePage.getProjectCarParkInput().isSelected();
    if (selectedProjectCarPark) {
      projectUpdatePage.getProjectCarParkInput().click();
      expect(await projectUpdatePage.getProjectCarParkInput().isSelected()).to.be.false;
    } else {
      projectUpdatePage.getProjectCarParkInput().click();
      expect(await projectUpdatePage.getProjectCarParkInput().isSelected()).to.be.true;
    }
    const selectedProjectBbqCourt = await projectUpdatePage.getProjectBbqCourtInput().isSelected();
    if (selectedProjectBbqCourt) {
      projectUpdatePage.getProjectBbqCourtInput().click();
      expect(await projectUpdatePage.getProjectBbqCourtInput().isSelected()).to.be.false;
    } else {
      projectUpdatePage.getProjectBbqCourtInput().click();
      expect(await projectUpdatePage.getProjectBbqCourtInput().isSelected()).to.be.true;
    }
    const selectedProjectElevator = await projectUpdatePage.getProjectElevatorInput().isSelected();
    if (selectedProjectElevator) {
      projectUpdatePage.getProjectElevatorInput().click();
      expect(await projectUpdatePage.getProjectElevatorInput().isSelected()).to.be.false;
    } else {
      projectUpdatePage.getProjectElevatorInput().click();
      expect(await projectUpdatePage.getProjectElevatorInput().isSelected()).to.be.true;
    }
    const selectedProjectShoppingCenter = await projectUpdatePage.getProjectShoppingCenterInput().isSelected();
    if (selectedProjectShoppingCenter) {
      projectUpdatePage.getProjectShoppingCenterInput().click();
      expect(await projectUpdatePage.getProjectShoppingCenterInput().isSelected()).to.be.false;
    } else {
      projectUpdatePage.getProjectShoppingCenterInput().click();
      expect(await projectUpdatePage.getProjectShoppingCenterInput().isSelected()).to.be.true;
    }
    const selectedProjectSwimmingPool = await projectUpdatePage.getProjectSwimmingPoolInput().isSelected();
    if (selectedProjectSwimmingPool) {
      projectUpdatePage.getProjectSwimmingPoolInput().click();
      expect(await projectUpdatePage.getProjectSwimmingPoolInput().isSelected()).to.be.false;
    } else {
      projectUpdatePage.getProjectSwimmingPoolInput().click();
      expect(await projectUpdatePage.getProjectSwimmingPoolInput().isSelected()).to.be.true;
    }
    const selectedProjectCommunityRoom = await projectUpdatePage.getProjectCommunityRoomInput().isSelected();
    if (selectedProjectCommunityRoom) {
      projectUpdatePage.getProjectCommunityRoomInput().click();
      expect(await projectUpdatePage.getProjectCommunityRoomInput().isSelected()).to.be.false;
    } else {
      projectUpdatePage.getProjectCommunityRoomInput().click();
      expect(await projectUpdatePage.getProjectCommunityRoomInput().isSelected()).to.be.true;
    }
    const selectedProjectGym = await projectUpdatePage.getProjectGymInput().isSelected();
    if (selectedProjectGym) {
      projectUpdatePage.getProjectGymInput().click();
      expect(await projectUpdatePage.getProjectGymInput().isSelected()).to.be.false;
    } else {
      projectUpdatePage.getProjectGymInput().click();
      expect(await projectUpdatePage.getProjectGymInput().isSelected()).to.be.true;
    }
    const selectedProjectCityPark = await projectUpdatePage.getProjectCityParkInput().isSelected();
    if (selectedProjectCityPark) {
      projectUpdatePage.getProjectCityParkInput().click();
      expect(await projectUpdatePage.getProjectCityParkInput().isSelected()).to.be.false;
    } else {
      projectUpdatePage.getProjectCityParkInput().click();
      expect(await projectUpdatePage.getProjectCityParkInput().isSelected()).to.be.true;
    }
    const selectedProjectGuard = await projectUpdatePage.getProjectGuardInput().isSelected();
    if (selectedProjectGuard) {
      projectUpdatePage.getProjectGuardInput().click();
      expect(await projectUpdatePage.getProjectGuardInput().isSelected()).to.be.false;
    } else {
      projectUpdatePage.getProjectGuardInput().click();
      expect(await projectUpdatePage.getProjectGuardInput().isSelected()).to.be.true;
    }
    const selectedProjectPlayGround = await projectUpdatePage.getProjectPlayGroundInput().isSelected();
    if (selectedProjectPlayGround) {
      projectUpdatePage.getProjectPlayGroundInput().click();
      expect(await projectUpdatePage.getProjectPlayGroundInput().isSelected()).to.be.false;
    } else {
      projectUpdatePage.getProjectPlayGroundInput().click();
      expect(await projectUpdatePage.getProjectPlayGroundInput().isSelected()).to.be.true;
    }
    projectUpdatePage.setLongitudeInput('5');
    expect(await projectUpdatePage.getLongitudeInput()).to.eq('5');
    projectUpdatePage.setLatitudeInput('5');
    expect(await projectUpdatePage.getLatitudeInput()).to.eq('5');
    projectUpdatePage.setProjectSeenCountInput('5');
    expect(await projectUpdatePage.getProjectSeenCountInput()).to.eq('5');
    const selectedProjectAvailable = await projectUpdatePage.getProjectAvailableInput().isSelected();
    if (selectedProjectAvailable) {
      projectUpdatePage.getProjectAvailableInput().click();
      expect(await projectUpdatePage.getProjectAvailableInput().isSelected()).to.be.false;
    } else {
      projectUpdatePage.getProjectAvailableInput().click();
      expect(await projectUpdatePage.getProjectAvailableInput().isSelected()).to.be.true;
    }
    projectUpdatePage.locationSelectLastOption();
    projectUpdatePage.consultantSelectLastOption();
    // projectUpdatePage.tagSelectLastOption();
    // projectUpdatePage.buildingtypeSelectLastOption();
    // projectUpdatePage.inverstorSelectLastOption();
    // projectUpdatePage.contractorSelectLastOption();
    await projectUpdatePage.save();
    expect(await projectUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
