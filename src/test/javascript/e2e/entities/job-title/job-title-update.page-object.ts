import { element, by, ElementFinder } from 'protractor';

export default class JobTitleUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.jobTitle.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  titleNameInput: ElementFinder = element(by.css('input#job-title-titleName'));
  salaryFactorInput: ElementFinder = element(by.css('input#job-title-salaryFactor'));

  getPageTitle() {
    return this.pageTitle;
  }

  setTitleNameInput(titleName) {
    this.titleNameInput.sendKeys(titleName);
  }

  getTitleNameInput() {
    return this.titleNameInput.getAttribute('value');
  }

  setSalaryFactorInput(salaryFactor) {
    this.salaryFactorInput.sendKeys(salaryFactor);
  }

  getSalaryFactorInput() {
    return this.salaryFactorInput.getAttribute('value');
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
