import React from "react";
import { Redirect, Route } from "react-router-dom";
import { isAuthenticated } from "../../auth";

function AdminTemplate(props) {
  return (
    <>
        <section>{props.children}</section>
    </>
  );
}

const RouterAdminTemplate = ({ path, exact, Component }) => {
  return (
    <Route
      path={path}
      exact={exact}
      render={(props) =>
        isAuthenticated() ? (
          <AdminTemplate>
            <Component {...props} />
          </AdminTemplate>
        ) : (
          <Redirect
            to={{
              pathname: "/signIn",
              state: { from: props.location },
            }}
          />
        )
      }
    />
  );
};

export default RouterAdminTemplate;
