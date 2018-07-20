/* tslint:disable no-unused-expression */
import { browser, protractor } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import EmployeeComponentsPage from './employee.page-object';
import EmployeeUpdatePage from './employee-update.page-object';
import path from 'path';

const expect = chai.expect;

describe('Employee e2e test', () => {
  let navBarPage: NavBarPage;
  let employeeUpdatePage: EmployeeUpdatePage;
  let employeeComponentsPage: EmployeeComponentsPage;
  const fileToUpload = '../../../../../main/webapp/static/images/logo-jhipster.png';
  const absolutePath = path.resolve(__dirname, fileToUpload);

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Employees', async () => {
    navBarPage.getEntityPage('employee');
    employeeComponentsPage = new EmployeeComponentsPage();
    expect(await employeeComponentsPage.getTitle().getText()).to.match(/Employees/);
  });

  it('should load create Employee page', async () => {
    employeeComponentsPage.clickOnCreateButton();
    employeeUpdatePage = new EmployeeUpdatePage();
    expect(await employeeUpdatePage.getPageTitle().getAttribute('id')).to.match(/riverApp.employee.home.createOrEditLabel/);
  });

  it('should create and save Employees', async () => {
    employeeUpdatePage.setEmployeeNameInput('employeeName');
    expect(await employeeUpdatePage.getEmployeeNameInput()).to.match(/employeeName/);
    employeeUpdatePage.setEmployeeFirstNameInput('employeeFirstName');
    expect(await employeeUpdatePage.getEmployeeFirstNameInput()).to.match(/employeeFirstName/);
    employeeUpdatePage.setEmployeeLastNameInput('employeeLastName');
    expect(await employeeUpdatePage.getEmployeeLastNameInput()).to.match(/employeeLastName/);
    employeeUpdatePage.setEmployeeDobInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await employeeUpdatePage.getEmployeeDobInput()).to.contain('2001-01-01T02:30');
    employeeUpdatePage.employeeSexSelectLastOption();
    employeeUpdatePage.setEmployeeIdentityCardInput('employeeIdentityCard');
    expect(await employeeUpdatePage.getEmployeeIdentityCardInput()).to.match(/employeeIdentityCard/);
    employeeUpdatePage.setEmployeePhoneInput('employeePhone');
    expect(await employeeUpdatePage.getEmployeePhoneInput()).to.match(/employeePhone/);
    employeeUpdatePage.setEmployeeEmailInput('employeeEmail');
    expect(await employeeUpdatePage.getEmployeeEmailInput()).to.match(/employeeEmail/);
    employeeUpdatePage.setEmployeeAvatarInput(absolutePath);
    employeeUpdatePage.setEmployeeFacebookInput('employeeFacebook');
    expect(await employeeUpdatePage.getEmployeeFacebookInput()).to.match(/employeeFacebook/);
    employeeUpdatePage.setEmployeeLinkedinInput('employeeLinkedin');
    expect(await employeeUpdatePage.getEmployeeLinkedinInput()).to.match(/employeeLinkedin/);
    employeeUpdatePage.setEmployeeInstagramInput('employeeInstagram');
    expect(await employeeUpdatePage.getEmployeeInstagramInput()).to.match(/employeeInstagram/);
    employeeUpdatePage.setEmployeeGooglePlusInput('employeeGooglePlus');
    expect(await employeeUpdatePage.getEmployeeGooglePlusInput()).to.match(/employeeGooglePlus/);
    employeeUpdatePage.setEmployeeZaloInput('employeeZalo');
    expect(await employeeUpdatePage.getEmployeeZaloInput()).to.match(/employeeZalo/);
    employeeUpdatePage.setEmployeeTwitterInput('employeeTwitter');
    expect(await employeeUpdatePage.getEmployeeTwitterInput()).to.match(/employeeTwitter/);
    employeeUpdatePage.setEmployeeYoutubeInput('employeeYoutube');
    expect(await employeeUpdatePage.getEmployeeYoutubeInput()).to.match(/employeeYoutube/);
    employeeUpdatePage.contactSelectLastOption();
    employeeUpdatePage.photoSelectLastOption();
    employeeUpdatePage.jobtitleSelectLastOption();
    employeeUpdatePage.departmentSelectLastOption();
    employeeUpdatePage.managerSelectLastOption();
    await employeeUpdatePage.save();
    expect(await employeeUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
