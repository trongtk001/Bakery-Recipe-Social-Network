import React, { useState } from "react";
import { Link, useHistory } from "react-router-dom";
import { useIsLogin } from "../hooks/useIsLogin";
import { useDispatch } from "react-redux";
import { actLogout } from "../store/actions/user.action";

const Menu = () => {
  const dispatch = useDispatch();
  const { isLogin, avatar } = useIsLogin();
  const history = useHistory();
  const [text, setText] = useState("");
  function handleLogout(e) {
    e.preventDefault();
    dispatch(actLogout());
  }
  const onSearch = (e) => {
    e.preventDefault();
    if (!text || text.length === 0) {
      return;
    }
    setText("");
    history.push(`/users?text=${text}`);
  };
  return (
    <header>
      <div className="header_inner">
        <div className="left-side">
          <div id="logo" className=" uk-hidden@s">
            BAKERY
          </div>
          <div
            className="triger"
            uk-toggle="target: #wrapper ; cls: sidebar-active"
          >
            <i className="uil-bars" />
          </div>
          <form onSubmit={onSearch}>
            <div className="header_search">
              <input
                value={text}
                onChange={(e) => setText(e.target.value)}
                type="text"
                placeholder="Search.."
              />
              <button type="submit" className="icon-search">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    strokeWidth={2}
                    d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"
                  />
                </svg>
              </button>
            </div>
          </form>
        </div>
        <div className="right-side lg:pr-4">
          <Link
            to={`/post/create`}
            className="bg-pink-500 font-bold hover:bg-pink-600 hover:text-white lg:block max-h-10 mr-4 px-4 py-2 rounded text-white"
          >
            <ion-icon
              name="add-circle"
              class="-mb-1 mr-1 opacity-90 text-xl uilus-circle"
            ></ion-icon>{" "}
            Create new post
          </Link>
          <a href="/" class="header-links-item">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
            >
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
                d="M15 17h5l-1.405-1.405A2.032 2.032 0 0118 14.158V11a6.002 6.002 0 00-4-5.659V5a2 2 0 10-4 0v.341C7.67 6.165 6 8.388 6 11v3.159c0 .538-.214 1.055-.595 1.436L4 17h5m6 0v1a3 3 0 11-6 0v-1m6 0H9"
              />
            </svg>
          </a>
          <div uk-drop="mode: click;offset: 4" class="header_dropdown">
            <h4 class="-mt-5 -mx-5 bg-gradient-to-t from-gray-100 to-gray-50 border-b font-bold px-6 py-3">
              Notification{" "}
            </h4>
          </div>
          {/* Messages */}
          <a href="/" className="header-links-item">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth={2}
                d="M7 8h10M7 12h4m1 8l-4-4H5a2 2 0 01-2-2V6a2 2 0 012-2h14a2 2 0 012 2v8a2 2 0 01-2 2h-3l-4 4z"
              />
            </svg>
          </a>
          <div uk-drop="mode: click;offset: 4" className="header_dropdown">
            <h4 className="-mt-5 -mx-5 bg-gradient-to-t from-gray-100 to-gray-50 border-b font-bold px-6 py-3">
              Messages
            </h4>
          </div>
          <div>
            <Link href="#">
              <div className="bg-gradient-to-tr from-yellow-600 to-pink-600 p-0.5 rounded-full">
                <img
                  src={
                    avatar
                      ? avatar
                      : `https://source.unsplash.com/random/?bakery,bake,${isLogin.id}`
                  }
                  class="bg-gray-200 border border-white rounded-full w-8 h-8"
                  alt="avatar"
                />
              </div>
            </Link>
            <div
              uk-drop="mode: click;offset:9"
              className="header_dropdown profile_dropdown border-t"
            >
              <ul>
                <li>
                  <Link href="#"> Account setting </Link>
                </li>
                <li>
                  <Link href="#"> Payments </Link>
                </li>
                <li>
                  <Link href="#"> Help </Link>
                </li>
                <li>
                  <Link to="/signIn" onClick={handleLogout}>
                    Log Out
                  </Link>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </header>
  );
};

export default Menu;
