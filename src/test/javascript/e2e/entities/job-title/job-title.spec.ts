/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import JobTitleComponentsPage from './job-title.page-object';
import JobTitleUpdatePage from './job-title-update.page-object';

const expect = chai.expect;

describe('JobTitle e2e test', () => {
  let navBarPage: NavBarPage;
  let jobTitleUpdatePage: JobTitleUpdatePage;
  let jobTitleComponentsPage: JobTitleComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load JobTitles', async () => {
    navBarPage.getEntityPage('job-title');
    jobTitleComponentsPage = new JobTitleComponentsPage();
    expect(await jobTitleComponentsPage.getTitle().getText()).to.match(/Job Titles/);
  });

  it('should load create JobTitle page', async () => {
    jobTitleComponentsPage.clickOnCreateButton();
    jobTitleUpdatePage = new JobTitleUpdatePage();
    expect(await jobTitleUpdatePage.getPageTitle().getAttribute('id')).to.match(/riverApp.jobTitle.home.createOrEditLabel/);
  });

  it('should create and save JobTitles', async () => {
    jobTitleUpdatePage.setTitleNameInput('titleName');
    expect(await jobTitleUpdatePage.getTitleNameInput()).to.match(/titleName/);
    jobTitleUpdatePage.setSalaryFactorInput('5');
    expect(await jobTitleUpdatePage.getSalaryFactorInput()).to.eq('5');
    await jobTitleUpdatePage.save();
    expect(await jobTitleUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
