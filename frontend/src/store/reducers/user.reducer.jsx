import { LIST_MESS, LIST_MESS_NULL, LOGIN_FAILED, LOGIN_SUCCESS, ACT_LOGOUT, USER_SUCCESS, USER_FAILED, POST_BY_USER_SUCCESS, POST_BY_USER_FAILED } from "../constants/user.const";

const initialState = {
    user:
        JSON.parse(localStorage.getItem("jwtToken")) || JSON.parse(localStorage.getItem("userLogin"))
            ? JSON.parse(localStorage.getItem("jwtToken")) || JSON.parse(localStorage.getItem("userLogin"))
            : null,
    errors: {},
    users: null,
    other: null,
    followers: [],
    friends: [],
    postByUser: null
};

const userReducer = (state = initialState, action) => {
    const { type, payload } = action;
    switch (type) {
        case LOGIN_SUCCESS: {
            return { ...state, user: payload };
        }
        case LIST_MESS: {
            return { ...state, listMess: [...state.listMess, payload] };
        }
        case LIST_MESS_NULL: {
            return { ...state, listMess: [] };
        }
        case LOGIN_FAILED: {
            return { ...state, errors: payload };
        }
        case USER_SUCCESS: {
            return { ...state, users: payload };
        }
        case USER_FAILED: {
            return { ...state, errors: payload };
        }
        case POST_BY_USER_SUCCESS: {
            return { ...state, postByUser: payload };
        }
        case POST_BY_USER_FAILED: {
            return { ...state, errors: payload };
        }
        case "GET_OTHER": {
            return { ...state, other: payload };
        }
        case "GET_FOLLOWER": {
            return { ...state, followers: payload };
        }
        case "GET_FRIEND": {
            return { ...state, friends: payload };
        }
        case ACT_LOGOUT:
            localStorage.removeItem("userLogin");
            return {
                ...state,
                user: null,
            };
        default:
            return state;
    }
};

export default userReducer;
