import React, { useEffect } from "react";
import HeroSection from "../HeroSection";
import SectionContainer from "../SectionContainer";
import styles from "./Header.module.css";
import Aos from "aos";
import "aos/dist/aos.css";
import "aos/dist/aos.js";

const Header = () => {
  useEffect(() => {
    Aos.init({ duration: 2000 });
    Aos.refresh();
  }, []);
  return (
    <div className={styles.header} data-aos={"fade-down"}>
      <SectionContainer>
        <HeroSection />
      </SectionContainer>
    </div>
  );
};

export default Header;
