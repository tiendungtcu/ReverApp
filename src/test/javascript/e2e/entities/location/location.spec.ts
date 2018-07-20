/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import LocationComponentsPage from './location.page-object';
import LocationUpdatePage from './location-update.page-object';

const expect = chai.expect;

describe('Location e2e test', () => {
  let navBarPage: NavBarPage;
  let locationUpdatePage: LocationUpdatePage;
  let locationComponentsPage: LocationComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Locations', async () => {
    navBarPage.getEntityPage('location');
    locationComponentsPage = new LocationComponentsPage();
    expect(await locationComponentsPage.getTitle().getText()).to.match(/Locations/);
  });

  it('should load create Location page', async () => {
    locationComponentsPage.clickOnCreateButton();
    locationUpdatePage = new LocationUpdatePage();
    expect(await locationUpdatePage.getPageTitle().getAttribute('id')).to.match(/riverApp.location.home.createOrEditLabel/);
  });

  it('should create and save Locations', async () => {
    locationUpdatePage.setLocationNameInput('locationName');
    expect(await locationUpdatePage.getLocationNameInput()).to.match(/locationName/);
    locationUpdatePage.setLocationFullAddressInput('locationFullAddress');
    expect(await locationUpdatePage.getLocationFullAddressInput()).to.match(/locationFullAddress/);
    locationUpdatePage.setLocationNumberInput('locationNumber');
    expect(await locationUpdatePage.getLocationNumberInput()).to.match(/locationNumber/);
    locationUpdatePage.setLocationRoadInput('locationRoad');
    expect(await locationUpdatePage.getLocationRoadInput()).to.match(/locationRoad/);
    locationUpdatePage.setLocationWardInput('locationWard');
    expect(await locationUpdatePage.getLocationWardInput()).to.match(/locationWard/);
    locationUpdatePage.setLocationDistrictInput('locationDistrict');
    expect(await locationUpdatePage.getLocationDistrictInput()).to.match(/locationDistrict/);
    locationUpdatePage.setLocationProvinceInput('locationProvince');
    expect(await locationUpdatePage.getLocationProvinceInput()).to.match(/locationProvince/);
    locationUpdatePage.setLocationCountryInput('locationCountry');
    expect(await locationUpdatePage.getLocationCountryInput()).to.match(/locationCountry/);
    locationUpdatePage.setLocationGmapUrlInput('locationGmapUrl');
    expect(await locationUpdatePage.getLocationGmapUrlInput()).to.match(/locationGmapUrl/);
    locationUpdatePage.setLongitudeInput('5');
    expect(await locationUpdatePage.getLongitudeInput()).to.eq('5');
    locationUpdatePage.setLatitudeInput('5');
    expect(await locationUpdatePage.getLatitudeInput()).to.eq('5');
    const selectedLocationHide = await locationUpdatePage.getLocationHideInput().isSelected();
    if (selectedLocationHide) {
      locationUpdatePage.getLocationHideInput().click();
      expect(await locationUpdatePage.getLocationHideInput().isSelected()).to.be.false;
    } else {
      locationUpdatePage.getLocationHideInput().click();
      expect(await locationUpdatePage.getLocationHideInput().isSelected()).to.be.true;
    }
    await locationUpdatePage.save();
    expect(await locationUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
