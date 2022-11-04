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

const postSlice = createSlice({
  name: 'posts',
  initialState,
  reducers: {
    setIsLoading: (state, action) => {
      state.isLoading = action.payload;
    },
    setState: (state, action) => {
      const { list, page, totalPage, size } = action.payload;
      state.data = list;
      state.filter = {
        page,
        limit: size,
        totalPage,
      };
    },
    setPage: (state, action) => {
      state.filter.page = action.payload;
    },
  },
});

export const postActions = postSlice.actions;

const postReducer = postSlice.reducer;
export default postReducer;
