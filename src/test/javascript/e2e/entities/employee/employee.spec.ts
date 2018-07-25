/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import EmployeeComponentsPage from './employee.page-object';
import EmployeeUpdatePage from './employee-update.page-object';

const expect = chai.expect;

describe('Employee e2e test', () => {
  let navBarPage: NavBarPage;
  let employeeUpdatePage: EmployeeUpdatePage;
  let employeeComponentsPage: EmployeeComponentsPage;

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
    employeeUpdatePage.setEmployeeFirstNameInput('employeeFirstName');
    expect(await employeeUpdatePage.getEmployeeFirstNameInput()).to.match(/employeeFirstName/);
    employeeUpdatePage.setEmployeeLastNameInput('employeeLastName');
    expect(await employeeUpdatePage.getEmployeeLastNameInput()).to.match(/employeeLastName/);
    employeeUpdatePage.setEmployeeDobInput('01-01-2001');
    expect(await employeeUpdatePage.getEmployeeDobInput()).to.eq('2001-01-01');
    employeeUpdatePage.employeeSexSelectLastOption();
    employeeUpdatePage.setEmployeeIdentityCardInput('employeeIdentityCard');
    expect(await employeeUpdatePage.getEmployeeIdentityCardInput()).to.match(/employeeIdentityCard/);
    employeeUpdatePage.setEmployeePhoneInput('employeePhone');
    expect(await employeeUpdatePage.getEmployeePhoneInput()).to.match(/employeePhone/);
    employeeUpdatePage.setEmployeeEmailInput('employeeEmail');
    expect(await employeeUpdatePage.getEmployeeEmailInput()).to.match(/employeeEmail/);
    employeeUpdatePage.accountSelectLastOption();
    employeeUpdatePage.departmentSelectLastOption();
    employeeUpdatePage.jobtitleSelectLastOption();
    await employeeUpdatePage.save();
    expect(await employeeUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
