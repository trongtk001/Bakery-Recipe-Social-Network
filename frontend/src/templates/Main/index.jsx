import React from "react";
import Sidebar from "../../core/Sidebar";
import Menu from "../../core/Menu";
import { Route } from "react-router-dom";
import { Redirect } from "react-router-dom";
import { useIsLogin } from "../../hooks/useIsLogin";

function MainTemplate(props) {
  return (
    <div id="wrapper">
      <Sidebar />
      <div className="main_content">
        <Menu />
        {props.children}
      </div>
    </div>
  );
}

const RouterMainTemplate = ({ path, exact, Component }) => {
  const { isLogin } = useIsLogin();
  return (
    <Route
      path={path}
      exact={exact}
      render={() =>
        isLogin ? (
          <MainTemplate>
            <Component />
          </MainTemplate>
        ) : (
          <Redirect
            to={{
              pathname: "/landing",
            }}
          />
        )
      }
    />
  );
};

export default RouterMainTemplate;
