import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  isLoading: false,
  data: [],
  filter: {
    limit: 10,
    page: 1,
    totalPage: 0,
  },
};

const ingredientSlice = createSlice({
  name: 'ingredients',
  initialState,
  reducers: {
    setIsLoading: (state, action) => {
      state.isLoading = action.payload;
    },
    setState: (state, action) => {
      state.isLoading = false;
      state.data = action.payload;
    },
    setPage: (state, action) => {
      state.filter.page = action.payload;
    },
  },
});

export const ingredientActions = ingredientSlice.actions;

const ingredientReducer = ingredientSlice.reducer;
export default ingredientReducer;
