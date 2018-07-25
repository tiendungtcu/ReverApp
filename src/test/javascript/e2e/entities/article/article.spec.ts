/* tslint:disable no-unused-expression */
import { browser, protractor } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import ArticleComponentsPage from './article.page-object';
import ArticleUpdatePage from './article-update.page-object';

const expect = chai.expect;

describe('Article e2e test', () => {
  let navBarPage: NavBarPage;
  let articleUpdatePage: ArticleUpdatePage;
  let articleComponentsPage: ArticleComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Articles', async () => {
    navBarPage.getEntityPage('article');
    articleComponentsPage = new ArticleComponentsPage();
    expect(await articleComponentsPage.getTitle().getText()).to.match(/Articles/);
  });

  it('should load create Article page', async () => {
    articleComponentsPage.clickOnCreateButton();
    articleUpdatePage = new ArticleUpdatePage();
    expect(await articleUpdatePage.getPageTitle().getAttribute('id')).to.match(/riverApp.article.home.createOrEditLabel/);
  });

  it('should create and save Articles', async () => {
    articleUpdatePage.setArticleTitleInput('articleTitle');
    expect(await articleUpdatePage.getArticleTitleInput()).to.match(/articleTitle/);
    articleUpdatePage.articleStatusSelectLastOption();
    articleUpdatePage.setArticleDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await articleUpdatePage.getArticleDateInput()).to.contain('2001-01-01T02:30');
    articleUpdatePage.setArticleSeenCountInput('5');
    expect(await articleUpdatePage.getArticleSeenCountInput()).to.eq('5');
    articleUpdatePage.setArticleContentInput('articleContent');
    expect(await articleUpdatePage.getArticleContentInput()).to.match(/articleContent/);
    articleUpdatePage.authorSelectLastOption();
    articleUpdatePage.categorySelectLastOption();
    await articleUpdatePage.save();
    expect(await articleUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
