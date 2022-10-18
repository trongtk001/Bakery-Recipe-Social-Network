export function validateLogin(values) {
  let errors = {};
  if (!values.email) {
    errors.email = "Email address is required";
  }
  if (!values.password) {
    errors.password = "Password is required";
  }
  return errors;
}
export function validateRegister(values) {
  let errors = {};
  if (!values.email) {
    errors.email = "Email address is required";
  } else if (!/\S+@\S+\.\S+/.test(values.email)) {
    errors.email = "Email address is invalid";
  }
  if (!values.username) {
    errors.username = "Username is required";
  } else if (values.username.length < 5) {
    errors.username = "Username must be 5 or more characters";
  }
  if (!values.name) {
    errors.name = "name is required";
  } else if (values.name.length < 5) {
    errors.name = "name must be 5 or more characters";
  }
  if (!values.password) {
    errors.password = "Password is required";
  } else if (values.password.length < 8) {
    errors.password = "Password must be 8 or more characters";
  } else if (
    !/\d/.test(values.password) ||
    !/[!@#$%&?.]/g.test(values.password) ||
    !/[A-Z]/g.test(values.password)
  ) {
    errors.password =
      "Password must contains at least 1 number, at least 1 capital character, 1 special character";
  }
  if (!values.confirmPassword) {
    errors.confirmPassword = "Confirm password is required";
  } else if (values.confirmPassword !== values.password) {
    errors.confirmPassword = "Password and Confirm Password do not match";
  }
  return errors;
}
