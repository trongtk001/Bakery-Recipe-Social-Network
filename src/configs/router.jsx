import Home from "./../core/Home";
import Admin from "./../admin/Admin";
import ResetPassword from "./../user/ResetPassword";
import ForgotPassword from "./../user/ForgotPassword";
import Users from "./../user/Users";
import NewPost from "./../post/NewPost";
import FindPeople from "./../user/FindPeople";
import ContentSignIn from "../page/sign/ContentSignIn";
import ContentSignUp from "../page/sign/ContentSignUp";
import { ErrPage } from "../page/err";

export const mainRouter = [
  {
    path: "/",
    exact: true,
    Component: Home,
  },
  {
    path: "/users",
    exact: true,
    Component: Users,
  },
  {
    path: "/post/create",
    exact: true,
    Component: NewPost,
  },
  {
    path: "/findPeople",
    exact: true,
    Component: FindPeople,
  },
];

export const adminRouter = [
  {
    path: "/admin",
    exact: true,
    Component: Admin,
  },
];

export const signRouter = [
  {
    path: "/signIn",
    exact: false,
    Component: ContentSignIn,
  },
  {
    path: "/signUp",
    exact: false,
    Component: ContentSignUp,
  },
  {
    path: "/forgot-password",
    exact: false,
    Component: ForgotPassword,
  },
  {
    path: "/reset-password/:resetPasswordToken",
    exact: false,
    Component: ResetPassword,
  },
];
export const errRouter = [
  {
    Component: ErrPage,
  },
];
