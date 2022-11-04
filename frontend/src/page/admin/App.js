import React from 'react';
import { BrowserRouter as Router, Switch, Route, Redirect } from 'react-router-dom';
import MainLayout from './components/layout/MainLayout';
import AccountEditPage from './pages/AccountEditPage';
import AccountPage from './pages/AccountPage';
import IngredientPage from './pages/IngredientPage';
import PostEditPage from './pages/PostEditPage';
import PostPage from './pages/PostPage';
import ProfilePage from './pages/ProfilePage';

function App() {
  return (
    <>
      <Router>
        <Switch>
          <Route path='/profile'>
            <MainLayout>
              <ProfilePage />
            </MainLayout>
          </Route>
          <Route path='/users/add'>
            <MainLayout>
              <AccountEditPage />
            </MainLayout>
          </Route>
          <Route path='/users/edit/:userId'>
            <MainLayout>
              <AccountEditPage />
            </MainLayout>
          </Route>
          <Route path='/users'>
            <MainLayout>
              <AccountPage />
            </MainLayout>
          </Route>
          <Route path='/posts/add'>
            <MainLayout>
              <PostEditPage />
            </MainLayout>
          </Route>
          <Route path='/posts/edit/:postId'>
            <MainLayout>
              <PostEditPage />
            </MainLayout>
          </Route>
          <Route path='/posts'>
            <MainLayout>
              <PostPage />
            </MainLayout>
          </Route>
          <Route path='/posts/edit/:postId'>
            <MainLayout>
              <PostEditPage />
            </MainLayout>
          </Route>
          <Route path='/ingredients'>
            <MainLayout>
              <IngredientPage />
            </MainLayout>
          </Route>
          <Route path='/'>
            <Redirect to='/users' />
          </Route>
        </Switch>
      </Router>
    </>
  );
}

export default App;
