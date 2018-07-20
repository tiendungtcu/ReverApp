import { element, by, ElementFinder } from 'protractor';

export default class BlogPostUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.blogPost.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  postTitleInput: ElementFinder = element(by.css('input#blog-post-postTitle'));
  postStatusSelect: ElementFinder = element(by.css('select#blog-post-postStatus'));
  postCreatedDateInput: ElementFinder = element(by.css('input#blog-post-postCreatedDate'));
  postPublishDateInput: ElementFinder = element(by.css('input#blog-post-postPublishDate'));
  postUpdateDateInput: ElementFinder = element(by.css('input#blog-post-postUpdateDate'));
  postSeenCountInput: ElementFinder = element(by.css('input#blog-post-postSeenCount'));
  postContentInput: ElementFinder = element(by.css('input#blog-post-postContent'));
  categorySelect: ElementFinder = element(by.css('select#blog-post-category'));
  userSelect: ElementFinder = element(by.css('select#blog-post-user'));
  projectSelect: ElementFinder = element(by.css('select#blog-post-project'));

  getPageTitle() {
    return this.pageTitle;
  }

  setPostTitleInput(postTitle) {
    this.postTitleInput.sendKeys(postTitle);
  }

  getPostTitleInput() {
    return this.postTitleInput.getAttribute('value');
  }

  setPostStatusSelect(postStatus) {
    this.postStatusSelect.sendKeys(postStatus);
  }

  getPostStatusSelect() {
    return this.postStatusSelect.element(by.css('option:checked')).getText();
  }

  postStatusSelectLastOption() {
    this.postStatusSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setPostCreatedDateInput(postCreatedDate) {
    this.postCreatedDateInput.sendKeys(postCreatedDate);
  }

  getPostCreatedDateInput() {
    return this.postCreatedDateInput.getAttribute('value');
  }

  setPostPublishDateInput(postPublishDate) {
    this.postPublishDateInput.sendKeys(postPublishDate);
  }

  getPostPublishDateInput() {
    return this.postPublishDateInput.getAttribute('value');
  }

  setPostUpdateDateInput(postUpdateDate) {
    this.postUpdateDateInput.sendKeys(postUpdateDate);
  }

  getPostUpdateDateInput() {
    return this.postUpdateDateInput.getAttribute('value');
  }

  setPostSeenCountInput(postSeenCount) {
    this.postSeenCountInput.sendKeys(postSeenCount);
  }

  getPostSeenCountInput() {
    return this.postSeenCountInput.getAttribute('value');
  }

  setPostContentInput(postContent) {
    this.postContentInput.sendKeys(postContent);
  }

  getPostContentInput() {
    return this.postContentInput.getAttribute('value');
  }

  categorySelectLastOption() {
    this.categorySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  categorySelectOption(option) {
    this.categorySelect.sendKeys(option);
  }

  getCategorySelect() {
    return this.categorySelect;
  }

  getCategorySelectedOption() {
    return this.categorySelect.element(by.css('option:checked')).getText();
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

  projectSelectLastOption() {
    this.projectSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  projectSelectOption(option) {
    this.projectSelect.sendKeys(option);
  }

  getProjectSelect() {
    return this.projectSelect;
  }

  getProjectSelectedOption() {
    return this.projectSelect.element(by.css('option:checked')).getText();
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
