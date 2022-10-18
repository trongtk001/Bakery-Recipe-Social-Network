import styles from "./validate.module.css";
function Validation(props) {
  const errors = props.errors;
  return (
    <>
      {errors === undefined ? (
        <></>
      ) : (
        <div className={styles.MuiTooltipPopper}>
          <div className={styles.validation}>
            {errors}
            <span className={styles.MuiTooltipArrow} />
          </div>
        </div>
      )}
    </>
  );
}
export default Validation;
