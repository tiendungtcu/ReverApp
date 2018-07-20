/* tslint:disable no-unused-expression */
import { browser, protractor } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import RecruitmentInfoComponentsPage from './recruitment-info.page-object';
import RecruitmentInfoUpdatePage from './recruitment-info-update.page-object';
import path from 'path';

const expect = chai.expect;

describe('RecruitmentInfo e2e test', () => {
  let navBarPage: NavBarPage;
  let recruitmentInfoUpdatePage: RecruitmentInfoUpdatePage;
  let recruitmentInfoComponentsPage: RecruitmentInfoComponentsPage;
  const fileToUpload = '../../../../../main/webapp/static/images/logo-jhipster.png';
  const absolutePath = path.resolve(__dirname, fileToUpload);

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load RecruitmentInfos', async () => {
    navBarPage.getEntityPage('recruitment-info');
    recruitmentInfoComponentsPage = new RecruitmentInfoComponentsPage();
    expect(await recruitmentInfoComponentsPage.getTitle().getText()).to.match(/Recruitment Infos/);
  });

  it('should load create RecruitmentInfo page', async () => {
    recruitmentInfoComponentsPage.clickOnCreateButton();
    recruitmentInfoUpdatePage = new RecruitmentInfoUpdatePage();
    expect(await recruitmentInfoUpdatePage.getPageTitle().getAttribute('id')).to.match(/riverApp.recruitmentInfo.home.createOrEditLabel/);
  });

  it('should create and save RecruitmentInfos', async () => {
    recruitmentInfoUpdatePage.setRecruitmentTitleInput('recruitmentTitle');
    expect(await recruitmentInfoUpdatePage.getRecruitmentTitleInput()).to.match(/recruitmentTitle/);
    recruitmentInfoUpdatePage.setRecruitmentImageInput(absolutePath);
    recruitmentInfoUpdatePage.setRecruitmentContentInput('recruitmentContent');
    expect(await recruitmentInfoUpdatePage.getRecruitmentContentInput()).to.match(/recruitmentContent/);
    recruitmentInfoUpdatePage.setRecruitmentNotesInput('recruitmentNotes');
    expect(await recruitmentInfoUpdatePage.getRecruitmentNotesInput()).to.match(/recruitmentNotes/);
    recruitmentInfoUpdatePage.setRecruitmentDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await recruitmentInfoUpdatePage.getRecruitmentDateInput()).to.contain('2001-01-01T02:30');
    recruitmentInfoUpdatePage.setRecruitmentSeenCountInput('5');
    expect(await recruitmentInfoUpdatePage.getRecruitmentSeenCountInput()).to.eq('5');
    const selectedRecruitmentStatus = await recruitmentInfoUpdatePage.getRecruitmentStatusInput().isSelected();
    if (selectedRecruitmentStatus) {
      recruitmentInfoUpdatePage.getRecruitmentStatusInput().click();
      expect(await recruitmentInfoUpdatePage.getRecruitmentStatusInput().isSelected()).to.be.false;
    } else {
      recruitmentInfoUpdatePage.getRecruitmentStatusInput().click();
      expect(await recruitmentInfoUpdatePage.getRecruitmentStatusInput().isSelected()).to.be.true;
    }
    recruitmentInfoUpdatePage.photoSelectLastOption();
    recruitmentInfoUpdatePage.jobtitleSelectLastOption();
    await recruitmentInfoUpdatePage.save();
    expect(await recruitmentInfoUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
