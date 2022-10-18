import styles from "./HeroSection.module.css";
import React from "react";
import { Link } from "react-router-dom";

const HeroSection = () => {
  return (
    <div className={styles["hero-section"]}>
      <div>
        <h1 className={styles.title}>
          Social Networking Platform <br /> To Share Your Cake Recipe
        </h1>
        <div className={styles.subtitle}>
          Cake Recipe - Specific Instructions - Baking Tools
        </div>
        <Link
          to="/signUp"
          className="bg-pink-500 pink-500 px-6 py-3 rounded-md shadow font-semibold text-white"
        >
          Join us now
        </Link>
      </div>
    </div>
  );
};

export default HeroSection;
