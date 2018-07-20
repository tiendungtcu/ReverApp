import { element, by, ElementFinder } from 'protractor';

export default class ArticleComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  title: ElementFinder = element(by.id('article-heading'));

  clickOnCreateButton() {
    return this.createButton.click();
  }

  getTitle() {
    return this.title;
  }
}
