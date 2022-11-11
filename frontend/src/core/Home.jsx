import React from "react";
import { NotificationContainer } from "react-notifications";
import "react-notifications/lib/notifications.css";
import Posts from "../post/Posts";
import FindPeople from "../user/FindPeople";

const Home = () => {
  return (
    <div className="container m-auto " >

      <div className="lg:flex justify-center lg:space-x-10 lg:space-y-0 space-y-5">
        <Posts />
        <FindPeople />
      </div>
      <NotificationContainer />
    </div>
  );
};

export default Home;
