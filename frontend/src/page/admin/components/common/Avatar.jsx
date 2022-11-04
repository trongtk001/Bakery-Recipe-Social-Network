import React from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const AvatarStyled = styled.div`
  width: ${(props) => (props.largest ? '200px' : '50px')};
  height: ${(props) => (props.largest ? '200px' : '50px')};
  border-radius: 100%;
  flex-shrink: 0;
  overflow: hidden;
  img {
    width: 100%;
    height: 100%;
  }
`;

const Avatar = ({ href, largest }) => {
  return (
    <AvatarStyled largest={largest}>
      <Link to='profile'>
        <img src={href || 'https://source.unsplash.com/random'} alt='avatar' />
      </Link>
    </AvatarStyled>
  );
};

export default Avatar;
