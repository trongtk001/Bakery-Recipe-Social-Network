import { combineReducers, configureStore } from '@reduxjs/toolkit';
import ingredientReducer from '../features/ingredients/ingredientSlice';
import postReducer from '../features/posts/postSlice';
import userReducer from '../features/users/userSlice';

const rootReducer = combineReducers({
  users: userReducer,
  posts: postReducer,
  ingredients: ingredientReducer,
});

export const store = configureStore({
  reducer: rootReducer,
});
