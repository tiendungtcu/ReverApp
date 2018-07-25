import { element, by, ElementFinder } from 'protractor';

export default class CommentUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.comment.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  commentContentInput: ElementFinder = element(by.css('input#comment-commentContent'));
  commentTimeStampInput: ElementFinder = element(by.css('input#comment-commentTimeStamp'));
  userSelect: ElementFinder = element(by.css('select#comment-user'));
  postSelect: ElementFinder = element(by.css('select#comment-post'));

  getPageTitle() {
    return this.pageTitle;
  }

  setCommentContentInput(commentContent) {
    this.commentContentInput.sendKeys(commentContent);
  }

  getCommentContentInput() {
    return this.commentContentInput.getAttribute('value');
  }

  setCommentTimeStampInput(commentTimeStamp) {
    this.commentTimeStampInput.sendKeys(commentTimeStamp);
  }

  getCommentTimeStampInput() {
    return this.commentTimeStampInput.getAttribute('value');
  }

  userSelectLastOption() {
    this.userSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  userSelectOption(option) {
    this.userSelect.sendKeys(option);
  }

  getUserSelect() {
    return this.userSelect;
  }

  getUserSelectedOption() {
    return this.userSelect.element(by.css('option:checked')).getText();
  }

  postSelectLastOption() {
    this.postSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  postSelectOption(option) {
    this.postSelect.sendKeys(option);
  }

  getPostSelect() {
    return this.postSelect;
  }

  getPostSelectedOption() {
    return this.postSelect.element(by.css('option:checked')).getText();
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
