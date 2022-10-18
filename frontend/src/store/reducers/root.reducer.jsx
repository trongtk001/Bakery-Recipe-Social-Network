import { combineReducers } from "redux";
import userReducer from "./user.reducer";
import commonReducer from "./common.reducer";
import postReducer from "./post.reducer";
const rootReducer = combineReducers({
  user: userReducer,
  post: postReducer,
  common: commonReducer,
});

export default rootReducer;
