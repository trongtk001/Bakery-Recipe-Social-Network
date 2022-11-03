import "./App.css";
import { BrowserRouter, Switch } from "react-router-dom";
import {
  adminRouter,
  signRouter,
  mainRouter,
  errRouter,
} from "./router/router";
import RouterMainTemplate from "./templates/Main";
import RouterAdminTemplate from "./templates/Admin";
import RouterSignTemplate from "./templates/Sign";
import RouterErrTemplate from "./templates/Err";

function MainRouter() {
  const renderMainRouter = () => {
    return mainRouter.map(({ path, exact, Component }, i) => {
      return (
        <RouterMainTemplate
          key={i}
          path={path}
          exact={exact}
          Component={Component}
        ></RouterMainTemplate>
      );
    });
  };
  const renderAdminRouter = () => {
    return adminRouter.map(({ path, exact, Component }, i) => {
      return (
        <RouterAdminTemplate
          key={i}
          path={path}
          exact={exact}
          Component={Component}
        ></RouterAdminTemplate>
      );
    });
  };
  const renderSignRouter = () => {
    return signRouter.map(({ path, exact, Component }, i) => {
      return (
        <RouterSignTemplate
          key={i}
          path={path}
          exact={exact}
          Component={Component}
        ></RouterSignTemplate>
      );
    });
  };
  const renderErrRouter = () => {
    return errRouter.map(({ Component }, i) => {
      return <RouterErrTemplate key={i} Component={Component}></RouterErrTemplate>;
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
