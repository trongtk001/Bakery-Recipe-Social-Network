import React from "react";
import { useDispatch } from "react-redux";
import { Link } from "react-router-dom";
import { NotificationContainer } from "react-notifications";
import "react-notifications/lib/notifications.css";
import Load from "../../components/load";
import Validation from "../../components/validation";
import { validateLogin } from "../../components/validation/validateInput";
import useForm from "../../hooks/useForm";
import { useIsLogin } from "../../hooks/useIsLogin";
import { postLogin } from "../../store/actions/user.action";

function ContentSignIn() {
  const dispatch = useDispatch();
  const { loading } = useIsLogin();

  function clickSubmit() {
    dispatch(postLogin(values.email, values.password));
  }

  const { values, errors, handleChange, handleSubmit } = useForm(
    clickSubmit,
    validateLogin
  );
  return (
    <div className="lg:p-12 max-w-md max-w-xl lg:my-0 my-12 mx-auto p-6 space-y-">
      <h1 className="lg:text-3xl text-xl font-semibold  mb-6"> Log in</h1>
      <p className="mb-2 text-black text-lg"> userName </p>
      <form onSubmit={handleSubmit} noValidate>
        <div>
          <input
            onChange={handleChange}
            type="email"
            name="email"
            value={values.email || ""}
            placeholder="userName"
            className="bg-gray-200 mb-2 shadow-none dark:bg-gray-800"
            style={{ border: "1px solid #d3d5d8 !important" }}
          />
          <Validation errors={errors.email} />
        </div>
        <div>
          <input
            onChange={handleChange}
            type="password"
            name="password"
            value={values.password || ""}
            placeholder="***********"
            className="bg-gray-200 mb-2 shadow-none dark:bg-gray-800"
            style={{ border: "1px solid #d3d5d8 !important" }}
          />
          <Validation errors={errors.password} />
        </div>
        <div className="flex justify-between my-4">
          <div className="checkbox">
            <input type="checkbox" id="chekcbox1" defaultChecked />
            <label htmlFor="chekcbox1">
              <span className="checkbox-icon" />
              Remember Me
            </label>
          </div>
          <Link to="/forgot-password">Forgot Your Password?</Link>
        </div>
        {loading ? (
          <button
            className="bg-gradient-to-br from-pink-500 items-center justify-center rounded-md text-white text-xl to-red-400 w-full"
            disabled
            style={{ opacity: ".4", display: "inline-flex" }}
          >
            Login
            <Load isSmall={true} />
          </button>
        ) : (
          <button className="bg-gradient-to-br from-pink-500 py-3 rounded-md text-white text-xl to-red-400 w-full">
            Login
          </button>
        )}
        <div className="text-center mt-5 space-x-2">
          <p className="text-base">
            Not registered?
            <Link to="/SignUp">Create a account</Link>
          </p>
        </div>
      </form>
      <NotificationContainer />
    </div>
  );
}

export default ContentSignIn;
