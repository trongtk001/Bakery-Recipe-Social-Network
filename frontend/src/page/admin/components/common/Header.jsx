import React from 'react';
import Container from 'react-bootstrap/Container';
import styled from 'styled-components';
import Avatar from './Avatar';
import { Link } from 'react-router-dom';

const HeaderStyled = styled.header`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%auto;
  padding: 12px;
  border-bottom: 1px solid #e2e8f0;
  h2 {
    font-size: 18px;
    padding: 0;
    margin: 0;
  }
`;

const Header = () => {
  return (
    <HeaderStyled>
      <Container
        style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}
        fluid
      >
        <Link to="/"><h2>Food Recipe</h2></Link>
        <Avatar />
      </Container>
    </HeaderStyled>
  );
};

export default Header;
