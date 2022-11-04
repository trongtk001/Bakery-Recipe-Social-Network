import { createSlice } from '@reduxjs/toolkit';

const userSlice = createSlice({
  name: 'users',
  initialState: {
    data: [],
    filter: {
      page: 1,
      limit: 10,
      totalPage: 0,
    },
  },
  reducers: {
    setState: (state, action) => {
      state.data = action.payload.list;
      state.filter.page = action.payload.page;
      state.filter.limit = action.payload.size;
      state.filter.totalPage = action.payload.totalPage;
    },
    setListUser: (state, action) => {
      state.data = action.payload;
    },
    clearListUser: (state, action) => {
      state.data = [];
    },
    setPage: (state, action) => {
      state.filter.page = action.payload;
    },
  },
});

export const userActions = userSlice.actions;

const userReducer = userSlice.reducer;

export default userReducer;
