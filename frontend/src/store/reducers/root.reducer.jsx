import { combineReducers } from "redux";
import userReducer from "./user.reducer";
import commonReducer from "./common.reducer";
import postReducer from "./post.reducer";
import messageReducer from "./message.reducer";
const rootReducer = combineReducers({
  user: userReducer,
  post: postReducer,
  common: commonReducer,
  message: messageReducer
});

export default rootReducer;
