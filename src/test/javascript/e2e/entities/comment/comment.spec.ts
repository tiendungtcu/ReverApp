/* tslint:disable no-unused-expression */
import { browser, protractor } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import CommentComponentsPage from './comment.page-object';
import CommentUpdatePage from './comment-update.page-object';

const expect = chai.expect;

describe('Comment e2e test', () => {
  let navBarPage: NavBarPage;
  let commentUpdatePage: CommentUpdatePage;
  let commentComponentsPage: CommentComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Comments', async () => {
    navBarPage.getEntityPage('comment');
    commentComponentsPage = new CommentComponentsPage();
    expect(await commentComponentsPage.getTitle().getText()).to.match(/Comments/);
  });

  it('should load create Comment page', async () => {
    commentComponentsPage.clickOnCreateButton();
    commentUpdatePage = new CommentUpdatePage();
    expect(await commentUpdatePage.getPageTitle().getAttribute('id')).to.match(/riverApp.comment.home.createOrEditLabel/);
  });

  it('should create and save Comments', async () => {
    commentUpdatePage.setCommentContentInput('commentContent');
    expect(await commentUpdatePage.getCommentContentInput()).to.match(/commentContent/);
    commentUpdatePage.setCommentTimeStampInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await commentUpdatePage.getCommentTimeStampInput()).to.contain('2001-01-01T02:30');
    commentUpdatePage.userSelectLastOption();
    commentUpdatePage.postSelectLastOption();
    await commentUpdatePage.save();
    expect(await commentUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
