import * as React from 'react';
import Container from 'react-bootstrap/Container';
import styled from 'styled-components';
import axiosClient from '../../api/axiosClient';
import Header from '../common/Header';
import Sidebar from '../common/Sidebar';
const MainLayoutStyled = styled.div`
  display: grid;
  grid-template-rows: 70px 1fr;
  height: 100vh;
`;

const ContentLayoutStyled = styled.div`
  display: grid;
  grid-template-columns: 300px 1fr;
  & > * {
    height: 100%;
  }
`;

export default function MainLayout({ children }) {
  React.useEffect(() => {
    (async () => {
      const localToken = localStorage.getItem('token');
      if (!localToken) {
        const response = await axiosClient.post('/authentication/login', {
          username: 'trong',
          password: '123456',
        });

        localStorage.setItem('token', response.token);
      }
    })();
  }, []);

  return (
    <MainLayoutStyled>
      <Header />
      <ContentLayoutStyled>
        <Sidebar />
        <Container style={{ marginTop: '24px' }} fluid>
          {children}
        </Container>
      </ContentLayoutStyled>
    </MainLayoutStyled>
  );
}
