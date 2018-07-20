/* tslint:disable no-unused-expression */
import { browser, protractor } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import BlogPostComponentsPage from './blog-post.page-object';
import BlogPostUpdatePage from './blog-post-update.page-object';

const expect = chai.expect;

describe('BlogPost e2e test', () => {
  let navBarPage: NavBarPage;
  let blogPostUpdatePage: BlogPostUpdatePage;
  let blogPostComponentsPage: BlogPostComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load BlogPosts', async () => {
    navBarPage.getEntityPage('blog-post');
    blogPostComponentsPage = new BlogPostComponentsPage();
    expect(await blogPostComponentsPage.getTitle().getText()).to.match(/Blog Posts/);
  });

  it('should load create BlogPost page', async () => {
    blogPostComponentsPage.clickOnCreateButton();
    blogPostUpdatePage = new BlogPostUpdatePage();
    expect(await blogPostUpdatePage.getPageTitle().getAttribute('id')).to.match(/riverApp.blogPost.home.createOrEditLabel/);
  });

  it('should create and save BlogPosts', async () => {
    blogPostUpdatePage.setPostTitleInput('postTitle');
    expect(await blogPostUpdatePage.getPostTitleInput()).to.match(/postTitle/);
    blogPostUpdatePage.postStatusSelectLastOption();
    blogPostUpdatePage.setPostCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await blogPostUpdatePage.getPostCreatedDateInput()).to.contain('2001-01-01T02:30');
    blogPostUpdatePage.setPostPublishDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await blogPostUpdatePage.getPostPublishDateInput()).to.contain('2001-01-01T02:30');
    blogPostUpdatePage.setPostUpdateDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await blogPostUpdatePage.getPostUpdateDateInput()).to.contain('2001-01-01T02:30');
    blogPostUpdatePage.setPostSeenCountInput('5');
    expect(await blogPostUpdatePage.getPostSeenCountInput()).to.eq('5');
    blogPostUpdatePage.setPostContentInput('postContent');
    expect(await blogPostUpdatePage.getPostContentInput()).to.match(/postContent/);
    blogPostUpdatePage.categorySelectLastOption();
    blogPostUpdatePage.userSelectLastOption();
    blogPostUpdatePage.projectSelectLastOption();
    await blogPostUpdatePage.save();
    expect(await blogPostUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
