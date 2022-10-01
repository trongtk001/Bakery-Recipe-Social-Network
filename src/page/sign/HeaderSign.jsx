import React from "react";
import { withRouter } from "react-router-dom";
import { Link } from "react-router-dom";

const HeaderSign = ({ history }) => (
  <div className="bg-white py-4 shadow dark:bg-gray-800">
    <div className="max-w-6xl mx-auto">
      <div className="flex items-center lg:justify-between justify-around">
        <Link
          to="/"
          className="bg-gradient-to-bl font-semibold from-pink-400 px-6 py-3 rounded text-sm text-white to-pink-600"
        >
          BAKERY
        </Link>
        <div className="capitalize flex font-semibold hidden lg:block my-2 space-x-3 text-center text-sm">
          <Link to="/SignIn" className="py-3 px-4">
            Login
          </Link>
          <Link
            to="/SignUp"
            className="bg-pink-500 pink-500 px-6 py-3 rounded-md shadow text-white"
          >
            Register
          </Link>
        </div>
      </div>
    </div>
  </div>
);

export default withRouter(HeaderSign);
