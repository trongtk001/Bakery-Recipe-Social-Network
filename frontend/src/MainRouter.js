import "./App.css";
import { BrowserRouter, Switch } from "react-router-dom";
import {
  adminRouter,
  signRouter,
  mainRouter,
  errRouter,
} from "./configs/router";
import RouterMainTemplate from "./templates/Main";
import RouterAdminTemplate from "./templates/Admin";
import RouterSignTemplate from "./templates/Sign";
import RouterErrTemplate from "./templates/Err";

function MainRouter() {
  const renderMainRouter = () => {
    return mainRouter.map(({ path, exact, Component }) => {
      return (
        <RouterMainTemplate
          path={path}
          exact={exact}
          Component={Component}
        ></RouterMainTemplate>
      );
    });
  };
  const renderAdminRouter = () => {
    return adminRouter.map(({ path, exact, Component }) => {
      return (
        <RouterAdminTemplate
          path={path}
          exact={exact}
          Component={Component}
        ></RouterAdminTemplate>
      );
    });
  };
  const renderSignRouter = () => {
    return signRouter.map(({ path, exact, Component }) => {
      return (
        <RouterSignTemplate
          path={path}
          exact={exact}
          Component={Component}
        ></RouterSignTemplate>
      );
    });
  };
  const renderErrRouter = () => {
    return errRouter.map(({ Component }) => {
      return <RouterErrTemplate Component={Component}></RouterErrTemplate>;
    });
  };
  return (
    <BrowserRouter>
      <Switch>
        {renderMainRouter()}
        {renderAdminRouter()}
        {renderSignRouter()}
        {renderErrRouter()}
      </Switch>
    </BrowserRouter>
  );
}

export default MainRouter;
