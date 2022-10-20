import React, { useEffect } from "react";
import SectionContainer from "../SectionContainer";
import SectionHeader from "../SectionHeader";
import FeatureItem from "./FeatureItem";
import styles from "./Features.module.css";
import Aos from "aos";
import "aos/dist/aos.css";
import "aos/dist/aos.js";

const Features = () => {
  useEffect(() => {
    Aos.init({ duration: 2000 });
    Aos.refresh();
  }, []);
  return (
    <div data-aos={"fade-down"}>
      <SectionContainer>
        <SectionHeader>Features that you really love</SectionHeader>
        <div className={styles.features}>
          <FeatureItem
            icon={<ion-icon name="beer"></ion-icon>}
            title="Extended color palette"
            description="A beautiful color palette that can be easily modified with our nicely coded Sass files."
          />
          <FeatureItem
            icon={<ion-icon name="fast-food"></ion-icon>}
            title="Everything is modular"
            description="Nicely customized components that can be reused anytime and anywhere in your project."
          />
          <FeatureItem
            icon={<ion-icon name="pizza"></ion-icon>}
            title="700+ Components"
            description="Nicely customized components that can be reused anytime and anywhere in your project"
          />
        </div>
      </SectionContainer>
    </div>
  );
};

export default Features;
