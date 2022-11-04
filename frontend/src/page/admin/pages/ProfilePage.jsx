import React from 'react';
import Container from 'react-bootstrap/Container';
import styled from 'styled-components';
import Avatar from '../components/common/Avatar';

const ProfilePageContainer = styled(Container)`
  display: flex;
  justify-content: center;
  align-items: center;
`;

const ProfileWrapperStyled = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  h2 {
    margin-top: 24px;
    margin-bottom: 18px;
  }
`;

const ProfilePage = () => {
  return (
    <ProfilePageContainer fluid='sm'>
      <ProfileWrapperStyled>
        <Avatar largest />
        <h2>Admin Name</h2>
        <p>
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Magnam, ducimus nemo vel numquam
          consequuntur maxime accusantium laboriosam aliquam placeat eius unde corporis accusamus
          omnis at incidunt ab neque dolor. Repudiandae.
        </p>
      </ProfileWrapperStyled>
    </ProfilePageContainer>
  );
};

export default ProfilePage;
