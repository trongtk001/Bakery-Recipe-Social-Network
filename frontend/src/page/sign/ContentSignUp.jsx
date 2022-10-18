import React from "react";
import { Link, useHistory } from "react-router-dom";
import { NotificationContainer } from "react-notifications";
import "react-notifications/lib/notifications.css";
import useForm from "../../hooks/useForm";
import { validateRegister } from "../../components/validation/validateInput";
import { postRegister } from "../../store/actions/user.action";
import { useDispatch } from "react-redux";
import Validation from "../../components/validation";
import { useIsLogin } from "../../hooks/useIsLogin";
import Load from "../../components/load";

function ContentSignUp() {
  const dispatch = useDispatch();
  const history = useHistory();
  const { loading } = useIsLogin();
  function clickSubmit() {
    dispatch(
      postRegister(
        values.name,
        values.email,
        values.username,
        values.password,
        history
      )
    );
  }
  const { values, errors, handleChange, handleSubmit } = useForm(
    clickSubmit,
    validateRegister
  );

  return (
    <div>
      <div className="lg:p-12 max-w-md max-w-xl lg:my-0 my-12 mx-auto p-6 space-y-">
        <h1 className="lg:text-3xl text-xl font-semibold mb-6"> Sign in</h1>
        <p className="mb-2 text-black text-lg">
          Register to manage your account
        </p>
        <form onSubmit={handleSubmit} noValidate>
          <div>
            <input
              onChange={handleChange}
              type="text"
              name="username"
              value={values.username || ""}
              placeholder="username"
              className="bg-gray-200 mb-2 shadow-none  dark:bg-gray-800"
              style={{ border: "1px solid #d3d5d8 !important" }}
            />
            <Validation errors={errors.username} />
          </div>
          <div>
            <input
              onChange={handleChange}
              type="text"
              name="name"
              value={values.name || ""}
              placeholder="Name"
              className="bg-gray-200 mb-2 shadow-none  dark:bg-gray-800"
              style={{ border: "1px solid #d3d5d8 !important" }}
            />
            <Validation errors={errors.name} />
          </div>
          <div>
            <input
              onChange={handleChange}
              type="email"
              name="email"
              value={values.email || ""}
              placeholder="Email"
              className="bg-gray-200 mb-2 shadow-none  dark:bg-gray-800"
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
              placeholder="Password"
              className="bg-gray-200 mb-2 shadow-none  dark:bg-gray-800"
              style={{ border: "1px solid #d3d5d8 !important" }}
            />
            <Validation errors={errors.password} />
          </div>
          <div>
            <input
              onChange={handleChange}
              type="password"
              name="confirmPassword"
              value={values.confirmPassword || ""}
              placeholder="Confirm Password"
              className="bg-gray-200 mb-2 shadow-none  dark:bg-gray-800"
              style={{ border: "1px solid #d3d5d8 !important" }}
            />
            <Validation errors={errors.confirmPassword} />
          </div>
          <div className="flex justify-start my-4 space-x-1">
            <div className="checkbox">
              <input type="checkbox" id="chekcbox1" defaultChecked />
              <label htmlFor="chekcbox1">
                <span className="checkbox-icon" /> I Agree
              </label>
            </div>
            <Link to="#"> Terms and Conditions</Link>
          </div>
          {loading ? (
            <button
              className="bg-gradient-to-br from-pink-500 items-center justify-center rounded-md text-white text-xl to-red-400 w-full"
              disabled
              style={{ opacity: ".4", display: "inline-flex" }}
            >
              Register
              <Load isSmall={true} />
            </button>
          ) : (
            <button className="bg-gradient-to-br from-pink-500 py-3 rounded-md text-white text-xl to-red-400 w-full">
              Register
            </button>
          )}
          <div className="text-center mt-5 space-x-2">
            <p className="text-base">
              Do you have an account? <Link to="/signIn"> Login </Link>
            </p>
          </div>
        </form>
      </div>
      <NotificationContainer />
    </div>
  );
}

export default ContentSignUp;
