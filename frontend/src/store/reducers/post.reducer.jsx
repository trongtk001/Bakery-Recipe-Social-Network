import {
  GET_COMMENT_FAILED,
  GET_COMMENT_SUCCESS,
} from "../constants/post.const";

const initialState = {
  errors: {},
  postByUser: [],
  getListComment: null,
};

const postReducer = (state = initialState, action) => {
  const { type, payload } = action;
  switch (type) {
    case GET_COMMENT_SUCCESS: {
      return { ...state, getListComment: payload };
    }
    case GET_COMMENT_FAILED: {
      return { ...state, errors: payload };
    }
    default:
      return state;
  }
};

export default postReducer;
