import React from 'react';
import { BrowserRouter as Switch, Route, Redirect, useRouteMatch } from 'react-router-dom';
import MainLayout from './components/layout/MainLayout';
import AccountEditPage from './pages/AccountEditPage';
import AccountPage from './pages/AccountPage';
import IngredientPage from './pages/IngredientPage';
import PostEditPage from './pages/PostEditPage';
import PostPage from './pages/PostPage';
import ProfilePage from './pages/ProfilePage';

function App() {

  let { path } = useRouteMatch();

  return (
    <>
      <Switch>
        <Route path={`${path}/profile`}>
          <MainLayout>
            <ProfilePage />
          </MainLayout>
        </Route>
        <Route path={`${path}/users/add`}>
          <MainLayout>
            <AccountEditPage />
          </MainLayout>
        </Route>
        <Route path={`${path}/users/edit/:userId`}>
          <MainLayout>
            <AccountEditPage />
          </MainLayout>
        </Route>
        <Route path={`${path}/users`}>
          <MainLayout>
            <AccountPage />
          </MainLayout>
        </Route>
        <Route path={`${path}/posts/add`}>
          <MainLayout>
            <PostEditPage />
          </MainLayout>
        </Route>
        <Route path={`${path}/posts/edit/:postId`}>
          <MainLayout>
            <PostEditPage />
          </MainLayout>
        </Route>
        <Route path={`${path}/posts`}>
          <MainLayout>
            <PostPage />
          </MainLayout>
        </Route>
        <Route path={`${path}/posts/edit/:postId`}>
          <MainLayout>
            <PostEditPage />
          </MainLayout>
        </Route>
        <Route path={`${path}/ingredients`}>
          <MainLayout>
            <IngredientPage />
          </MainLayout>
        </Route>
        <Route path={`${path}`}>
          <Redirect to={`${path}/users`} />
        </Route>
      </Switch>
    </>
  );
}

export default App;
