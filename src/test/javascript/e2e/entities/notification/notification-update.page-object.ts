import { element, by, ElementFinder } from 'protractor';

export default class NotificationUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.notification.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  notificationTitleInput: ElementFinder = element(by.css('input#notification-notificationTitle'));
  notificationContentInput: ElementFinder = element(by.css('input#notification-notificationContent'));
  notificationSeenInput: ElementFinder = element(by.css('input#notification-notificationSeen'));
  notificationDateInput: ElementFinder = element(by.css('input#notification-notificationDate'));
  notificationTypeSelect: ElementFinder = element(by.css('select#notification-notificationType'));
  notificationSenderInput: ElementFinder = element(by.css('input#notification-notificationSender'));

  getPageTitle() {
    return this.pageTitle;
  }

  setNotificationTitleInput(notificationTitle) {
    this.notificationTitleInput.sendKeys(notificationTitle);
  }

  getNotificationTitleInput() {
    return this.notificationTitleInput.getAttribute('value');
  }

  setNotificationContentInput(notificationContent) {
    this.notificationContentInput.sendKeys(notificationContent);
  }

  getNotificationContentInput() {
    return this.notificationContentInput.getAttribute('value');
  }

  getNotificationSeenInput() {
    return this.notificationSeenInput;
  }
  setNotificationDateInput(notificationDate) {
    this.notificationDateInput.sendKeys(notificationDate);
  }

  getNotificationDateInput() {
    return this.notificationDateInput.getAttribute('value');
  }

  setNotificationTypeSelect(notificationType) {
    this.notificationTypeSelect.sendKeys(notificationType);
  }

  getNotificationTypeSelect() {
    return this.notificationTypeSelect.element(by.css('option:checked')).getText();
  }

  notificationTypeSelectLastOption() {
    this.notificationTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setNotificationSenderInput(notificationSender) {
    this.notificationSenderInput.sendKeys(notificationSender);
  }

  getNotificationSenderInput() {
    return this.notificationSenderInput.getAttribute('value');
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
