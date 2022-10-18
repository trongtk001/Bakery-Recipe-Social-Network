import React from "react";
import styles from "./SectionHeader.module.css";

const SectionHeader = ({ children }) => {
  return <div className={styles.header}>{children}</div>;
};

export default SectionHeader;
