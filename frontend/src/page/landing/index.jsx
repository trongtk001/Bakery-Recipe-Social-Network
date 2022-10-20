import React from "react";
import CallToAction from "../../components/landing/CallToAction";
import Features from "../../components/landing/Features";
import Header from "../../components/landing/Header";
import Popular from "../../components/landing/Popular";
import { NotificationContainer } from "react-notifications";
const LandingPage = () => {
  return (
    <>
      <Header />
      <Popular />
      <Features />
      <CallToAction />
      <NotificationContainer />
    </>
  );
};

export default LandingPage;
