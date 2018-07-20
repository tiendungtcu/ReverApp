import { element, by, ElementFinder } from 'protractor';

export default class ArticleUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.article.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  articleTitleInput: ElementFinder = element(by.css('input#article-articleTitle'));
  articleStatusSelect: ElementFinder = element(by.css('select#article-articleStatus'));
  articleDateInput: ElementFinder = element(by.css('input#article-articleDate'));
  articleSeenCountInput: ElementFinder = element(by.css('input#article-articleSeenCount'));
  articleContentInput: ElementFinder = element(by.css('input#article-articleContent'));
  categorySelect: ElementFinder = element(by.css('select#article-category'));
  userSelect: ElementFinder = element(by.css('select#article-user'));

  getPageTitle() {
    return this.pageTitle;
  }

  setArticleTitleInput(articleTitle) {
    this.articleTitleInput.sendKeys(articleTitle);
  }

  getArticleTitleInput() {
    return this.articleTitleInput.getAttribute('value');
  }

  setArticleStatusSelect(articleStatus) {
    this.articleStatusSelect.sendKeys(articleStatus);
  }

  getArticleStatusSelect() {
    return this.articleStatusSelect.element(by.css('option:checked')).getText();
  }

  articleStatusSelectLastOption() {
    this.articleStatusSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setArticleDateInput(articleDate) {
    this.articleDateInput.sendKeys(articleDate);
  }

  getArticleDateInput() {
    return this.articleDateInput.getAttribute('value');
  }

  setArticleSeenCountInput(articleSeenCount) {
    this.articleSeenCountInput.sendKeys(articleSeenCount);
  }

  getArticleSeenCountInput() {
    return this.articleSeenCountInput.getAttribute('value');
  }

  setArticleContentInput(articleContent) {
    this.articleContentInput.sendKeys(articleContent);
  }

  getArticleContentInput() {
    return this.articleContentInput.getAttribute('value');
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
