import { element, by, ElementFinder } from 'protractor';

export default class RecruitmentInfoUpdatePage {
  pageTitle: ElementFinder = element(by.id('riverApp.recruitmentInfo.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  recruitmentTitleInput: ElementFinder = element(by.css('input#recruitment-info-recruitmentTitle'));
  recruitmentImageInput: ElementFinder = element(by.css('input#file_recruitmentImage'));
  recruitmentContentInput: ElementFinder = element(by.css('input#recruitment-info-recruitmentContent'));
  recruitmentNotesInput: ElementFinder = element(by.css('input#recruitment-info-recruitmentNotes'));
  recruitmentDateInput: ElementFinder = element(by.css('input#recruitment-info-recruitmentDate'));
  recruitmentSeenCountInput: ElementFinder = element(by.css('input#recruitment-info-recruitmentSeenCount'));
  recruitmentStatusInput: ElementFinder = element(by.css('input#recruitment-info-recruitmentStatus'));
  photoSelect: ElementFinder = element(by.css('select#recruitment-info-photo'));
  jobtitleSelect: ElementFinder = element(by.css('select#recruitment-info-jobtitle'));

  getPageTitle() {
    return this.pageTitle;
  }

  setRecruitmentTitleInput(recruitmentTitle) {
    this.recruitmentTitleInput.sendKeys(recruitmentTitle);
  }

  getRecruitmentTitleInput() {
    return this.recruitmentTitleInput.getAttribute('value');
  }

  setRecruitmentImageInput(recruitmentImage) {
    this.recruitmentImageInput.sendKeys(recruitmentImage);
  }

  getRecruitmentImageInput() {
    return this.recruitmentImageInput.getAttribute('value');
  }

  setRecruitmentContentInput(recruitmentContent) {
    this.recruitmentContentInput.sendKeys(recruitmentContent);
  }

  getRecruitmentContentInput() {
    return this.recruitmentContentInput.getAttribute('value');
  }

  setRecruitmentNotesInput(recruitmentNotes) {
    this.recruitmentNotesInput.sendKeys(recruitmentNotes);
  }

  getRecruitmentNotesInput() {
    return this.recruitmentNotesInput.getAttribute('value');
  }

  setRecruitmentDateInput(recruitmentDate) {
    this.recruitmentDateInput.sendKeys(recruitmentDate);
  }

  getRecruitmentDateInput() {
    return this.recruitmentDateInput.getAttribute('value');
  }

  setRecruitmentSeenCountInput(recruitmentSeenCount) {
    this.recruitmentSeenCountInput.sendKeys(recruitmentSeenCount);
  }

  getRecruitmentSeenCountInput() {
    return this.recruitmentSeenCountInput.getAttribute('value');
  }

  getRecruitmentStatusInput() {
    return this.recruitmentStatusInput;
  }
  photoSelectLastOption() {
    this.photoSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  photoSelectOption(option) {
    this.photoSelect.sendKeys(option);
  }

  getPhotoSelect() {
    return this.photoSelect;
  }

  getPhotoSelectedOption() {
    return this.photoSelect.element(by.css('option:checked')).getText();
  }

  jobtitleSelectLastOption() {
    this.jobtitleSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  jobtitleSelectOption(option) {
    this.jobtitleSelect.sendKeys(option);
  }

  getJobtitleSelect() {
    return this.jobtitleSelect;
  }

  getJobtitleSelectedOption() {
    return this.jobtitleSelect.element(by.css('option:checked')).getText();
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
