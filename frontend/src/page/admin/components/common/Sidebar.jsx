import React from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import Button from 'react-bootstrap/Button';

const SidebarStyled = styled.nav`
  border-right: 1px solid #e2e8f0;
  display: flex;
  flex-direction: column;
  padding: 12px 24px;
  gap: 12px;

  a {
    text-align: left;
  }
`;

const Sidebar = () => {
  return (
    <SidebarStyled>
      <Button as={Link} variant='light' to='/users'>
        Users
      </Button>
      <Button as={Link} variant='light' to='/posts'>
        Posts
      </Button>
      <Button as={Link} variant='light' to='/ingredients'>
        Ingredients
      </Button>
    </SidebarStyled>
  );
};

export default Sidebar;
