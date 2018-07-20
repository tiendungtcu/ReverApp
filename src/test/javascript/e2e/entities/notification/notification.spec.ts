/* tslint:disable no-unused-expression */
import { browser, protractor } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import NotificationComponentsPage from './notification.page-object';
import NotificationUpdatePage from './notification-update.page-object';

const expect = chai.expect;

describe('Notification e2e test', () => {
  let navBarPage: NavBarPage;
  let notificationUpdatePage: NotificationUpdatePage;
  let notificationComponentsPage: NotificationComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Notifications', async () => {
    navBarPage.getEntityPage('notification');
    notificationComponentsPage = new NotificationComponentsPage();
    expect(await notificationComponentsPage.getTitle().getText()).to.match(/Notifications/);
  });

  it('should load create Notification page', async () => {
    notificationComponentsPage.clickOnCreateButton();
    notificationUpdatePage = new NotificationUpdatePage();
    expect(await notificationUpdatePage.getPageTitle().getAttribute('id')).to.match(/riverApp.notification.home.createOrEditLabel/);
  });

  it('should create and save Notifications', async () => {
    notificationUpdatePage.setNotificationTitleInput('notificationTitle');
    expect(await notificationUpdatePage.getNotificationTitleInput()).to.match(/notificationTitle/);
    notificationUpdatePage.setNotificationContentInput('notificationContent');
    expect(await notificationUpdatePage.getNotificationContentInput()).to.match(/notificationContent/);
    const selectedNotificationSeen = await notificationUpdatePage.getNotificationSeenInput().isSelected();
    if (selectedNotificationSeen) {
      notificationUpdatePage.getNotificationSeenInput().click();
      expect(await notificationUpdatePage.getNotificationSeenInput().isSelected()).to.be.false;
    } else {
      notificationUpdatePage.getNotificationSeenInput().click();
      expect(await notificationUpdatePage.getNotificationSeenInput().isSelected()).to.be.true;
    }
    notificationUpdatePage.setNotificationDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await notificationUpdatePage.getNotificationDateInput()).to.contain('2001-01-01T02:30');
    notificationUpdatePage.notificationTypeSelectLastOption();
    notificationUpdatePage.setNotificationReferenceInput('notificationReference');
    expect(await notificationUpdatePage.getNotificationReferenceInput()).to.match(/notificationReference/);
    notificationUpdatePage.userSelectLastOption();
    await notificationUpdatePage.save();
    expect(await notificationUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
