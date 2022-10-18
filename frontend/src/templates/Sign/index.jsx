import React from "react";
import { Route, Redirect } from "react-router-dom";
import { useIsLogin } from "../../hooks/useIsLogin";
import FooterSign from "../../page/sign/FooterSign";
import HeaderSign from "../../page/sign/HeaderSign";

function SignTemplate(props) {
  return (
    <div id="wrapper" className="flex flex-col justify-between h-screen">
      <HeaderSign />
      <main>{props.children}</main>
      <FooterSign />
    </div>
  );
}

const RouterSignTemplate = ({ path, exact, Component }) => {
  const { isLogin } = useIsLogin();
  return (
    <Route
      path={path}
      exact={exact}
      render={() =>
        !isLogin ? (
          <SignTemplate>
            <Component />
          </SignTemplate>
        ) : (
          <Redirect
            to={{
              pathname: "/",
            }}
          />
        )
      }
    />
  );
};

export default RouterSignTemplate;
