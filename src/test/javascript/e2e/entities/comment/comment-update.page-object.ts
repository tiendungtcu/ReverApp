import { element, by, ElementFinder } from 'protractor';

export default class CommentUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.comment.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  commentTitleInput: ElementFinder = element(by.css('input#comment-commentTitle'));
  commentContentInput: ElementFinder = element(by.css('input#comment-commentContent'));
  commentCreatedDateInput: ElementFinder = element(by.css('input#comment-commentCreatedDate'));
  commentUpdateDateInput: ElementFinder = element(by.css('input#comment-commentUpdateDate'));
  userSelect: ElementFinder = element(by.css('select#comment-user'));
  postSelect: ElementFinder = element(by.css('select#comment-post'));

  getPageTitle() {
    return this.pageTitle;
  }

  setCommentTitleInput(commentTitle) {
    this.commentTitleInput.sendKeys(commentTitle);
  }

  getCommentTitleInput() {
    return this.commentTitleInput.getAttribute('value');
  }

  setCommentContentInput(commentContent) {
    this.commentContentInput.sendKeys(commentContent);
  }

  getCommentContentInput() {
    return this.commentContentInput.getAttribute('value');
  }

  setCommentCreatedDateInput(commentCreatedDate) {
    this.commentCreatedDateInput.sendKeys(commentCreatedDate);
  }

  getCommentCreatedDateInput() {
    return this.commentCreatedDateInput.getAttribute('value');
  }

  setCommentUpdateDateInput(commentUpdateDate) {
    this.commentUpdateDateInput.sendKeys(commentUpdateDate);
  }

  getCommentUpdateDateInput() {
    return this.commentUpdateDateInput.getAttribute('value');
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
