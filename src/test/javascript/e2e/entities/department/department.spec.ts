/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import DepartmentComponentsPage from './department.page-object';
import DepartmentUpdatePage from './department-update.page-object';
import path from 'path';

const expect = chai.expect;

describe('Department e2e test', () => {
  let navBarPage: NavBarPage;
  let departmentUpdatePage: DepartmentUpdatePage;
  let departmentComponentsPage: DepartmentComponentsPage;
  const fileToUpload = '../../../../../main/webapp/static/images/logo-jhipster.png';
  const absolutePath = path.resolve(__dirname, fileToUpload);

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Departments', async () => {
    navBarPage.getEntityPage('department');
    departmentComponentsPage = new DepartmentComponentsPage();
    expect(await departmentComponentsPage.getTitle().getText()).to.match(/Departments/);
  });

  it('should load create Department page', async () => {
    departmentComponentsPage.clickOnCreateButton();
    departmentUpdatePage = new DepartmentUpdatePage();
    expect(await departmentUpdatePage.getPageTitle().getAttribute('id')).to.match(/riverApp.department.home.createOrEditLabel/);
  });

  it('should create and save Departments', async () => {
    departmentUpdatePage.setDepartmentNameInput('departmentName');
    expect(await departmentUpdatePage.getDepartmentNameInput()).to.match(/departmentName/);
    departmentUpdatePage.setDepartmentPhotoInput(absolutePath);
    departmentUpdatePage.setDepartmentPhoneInput('departmentPhone');
    expect(await departmentUpdatePage.getDepartmentPhoneInput()).to.match(/departmentPhone/);
    await departmentUpdatePage.save();
    expect(await departmentUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
