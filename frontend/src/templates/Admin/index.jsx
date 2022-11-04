import React from "react";
import { Redirect, Route } from "react-router-dom";
import { isAuthenticated } from "../../auth";
import Admin from "../../page/admin";


const RouterAdminTemplate = ({ path, exact, Component }) => {
  return (
    <Route
      path={path}
      exact={exact}
      render={(props) =>
        isAuthenticated() ? (
          <Admin/>
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
