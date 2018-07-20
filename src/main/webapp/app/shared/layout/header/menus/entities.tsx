import React from 'react';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { translate } from 'react-jhipster';
import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from '../header-components';

export const EntitiesMenu = props => (
  // tslint:disable-next-line:jsx-self-close
  <NavDropdown icon="th-list" name={translate('global.menu.entities.main')} id="entity-menu">
    <DropdownItem tag={Link} to="/entity/company">
      <FontAwesomeIcon icon="asterisk" />&nbsp; Company
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/contact">
      <FontAwesomeIcon icon="asterisk" />&nbsp; Contact
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/job-title">
      <FontAwesomeIcon icon="asterisk" />&nbsp; Job Title
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/exchanger">
      <FontAwesomeIcon icon="asterisk" />&nbsp; Exchanger
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/department">
      <FontAwesomeIcon icon="asterisk" />&nbsp; Department
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/investor">
      <FontAwesomeIcon icon="asterisk" />&nbsp; Investor
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/project-builder">
      <FontAwesomeIcon icon="asterisk" />&nbsp; Project Builder
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/document">
      <FontAwesomeIcon icon="asterisk" />&nbsp; Document
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/photo">
      <FontAwesomeIcon icon="asterisk" />&nbsp; Photo
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/location">
      <FontAwesomeIcon icon="asterisk" />&nbsp; Location
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/building-type">
      <FontAwesomeIcon icon="asterisk" />&nbsp; Building Type
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/category">
      <FontAwesomeIcon icon="asterisk" />&nbsp; Category
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/tag">
      <FontAwesomeIcon icon="asterisk" />&nbsp; Tag
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/notification">
      <FontAwesomeIcon icon="asterisk" />&nbsp; Notification
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/support-category">
      <FontAwesomeIcon icon="asterisk" />&nbsp; Support Category
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/article">
      <FontAwesomeIcon icon="asterisk" />&nbsp; Article
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/employee">
      <FontAwesomeIcon icon="asterisk" />&nbsp; Employee
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/residential-area">
      <FontAwesomeIcon icon="asterisk" />&nbsp; Residential Area
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/project">
      <FontAwesomeIcon icon="asterisk" />&nbsp; Project
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/property">
      <FontAwesomeIcon icon="asterisk" />&nbsp; Property
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/blog-post">
      <FontAwesomeIcon icon="asterisk" />&nbsp; Blog Post
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/recruitment-info">
      <FontAwesomeIcon icon="asterisk" />&nbsp; Recruitment Info
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/request">
      <FontAwesomeIcon icon="asterisk" />&nbsp; Request
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/comment">
      <FontAwesomeIcon icon="asterisk" />&nbsp; Comment
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/project-user">
      <FontAwesomeIcon icon="asterisk" />&nbsp; Project User
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/property-user">
      <FontAwesomeIcon icon="asterisk" />&nbsp; Property User
    </DropdownItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
