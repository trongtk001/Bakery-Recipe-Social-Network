import axios from "axios";
import { NotificationManager } from "react-notifications";
import { LIST_MESS, LOGIN_FAILED, LIST_MESS_NULL, LOGIN_SUCCESS, ACT_LOGOUT, USER_SUCCESS, USER_FAILED, POST_BY_USER_SUCCESS, POST_BY_USER_FAILED } from "../constants/user.const";
import { startLoading, stopLoading } from "../actions/common.action";
import callApi from "../../utils/api/callApi";

const API_URL = process.env.REACT_APP_API_URL;

export const postLogin = (email, password) => {
    return (dispatch) => {
        dispatch(startLoading());
        axios({
            method: "POST",
            url: `${API_URL}/authentication/login`,
            headers: {
                "Content-Type": "application/json",
            },
            data: {
                username: email,
                password,
            },
        })
            .then((res) => {
                dispatch(stopLoading());
                dispatch(postLoginSuccess(res.data));
                console.log(res.data);
                localStorage.setItem("userLogin", JSON.stringify(res.data));
            })
            .catch((err) => {
                dispatch(stopLoading());
                dispatch(postLoginFailed(err));
                console.log(err);
                NotificationManager.error(err.response.data.message);
            });
    };
};

export const postLoginSuccess = (user) => {
    return {
        type: LOGIN_SUCCESS,
        payload: user,
    };
};
export const setListMess = (mess) => {
    return {
        type: LIST_MESS,
        payload: mess,
    };
};
export const setListMessNULL = () => {
    return {
        type: LIST_MESS_NULL,
        payload: null,
    };
};

const postLoginFailed = (err) => {
    return {
        type: LOGIN_FAILED,
        payload: err,
    };
};

export const postRegister = (name, email, username, password, history) => {
    return (dispatch) => {
        dispatch(startLoading());
        axios({
            method: "POST",
            url: `${API_URL}/authentication/register`,
            headers: {
                "Content-Type": "application/json",
            },
            data: {
                name,
                email,
                dob: "2001-10-17",
                username,
                password,
                avatar: null,
                roles: ["USER"],
            },
        })
            .then((res) => {
                dispatch(stopLoading());
                history.push("/SignIn");
                NotificationManager.success("Registration is successful, please login", "", 3000);
            })
            .catch((err) => {
                dispatch(stopLoading());
                NotificationManager.error(err.response.data.message);
            });
    };
};

export function actLogout() {
    return {
        type: ACT_LOGOUT,
    };
}
export const getUser = (userId, isOwner) => {
    return (dispatch) => {
        dispatch(startLoading());
        axios({
            method: "GET",
            url: `${API_URL}/member/${userId}`,
            headers: {
                "Content-Type": "application/json",
            },
            data: null,
        })
            .then((res) => {
                dispatch(getUserSuccess(res.data, isOwner));
                // setFollowing(checkFollow(res.data));
                dispatch(stopLoading());
            })
            .catch((err) => {
                dispatch(getUserFailed(err));
                dispatch(stopLoading());
            });
    };
};

export const getUserSuccess = (users, isOwner) => {
    if (isOwner) {
        return ({
            type: LOGIN_SUCCESS,
            payload: users
        })
    } else {
        return ({
            type: USER_SUCCESS,
            payload: users
        })
    }
};

const getUserFailed = (err) => {
    return {
        type: USER_FAILED,
        payload: err,
    };
};

export const getPostByUser = (userId) => {
    const userLogin = localStorage.getItem("userLogin");
    const token = userLogin ? JSON.parse(userLogin).token : "";
    return (dispatch) => {
        dispatch(startLoading());
        axios({
            method: "GET",
            url: `${API_URL}/post/user/${userId}?page=1&size=8`,
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`,
            },
            data: null,
        })
            .then((res) => {
                dispatch(getPostByUserSuccess(res.data));
                dispatch(stopLoading());
            })
            .catch((err) => {
                dispatch(getPostByUserFailed(err));
                dispatch(stopLoading());
            });
    };
};

export const getPostByUserSuccess = (postByUser) => {
    return {
        type: POST_BY_USER_SUCCESS,
        payload: postByUser,
    };
};

const getPostByUserFailed = (err) => {
    return {
        type: POST_BY_USER_FAILED,
        payload: err,
    };
};

export const putProfile = (name, email, dob, avatar, history) => {
    const userLogin = localStorage.getItem("userLogin");
    const id = userLogin ? JSON.parse(userLogin).id : "";
    const token = userLogin ? JSON.parse(userLogin).token : "";
    return (dispatch) => {
        dispatch(startLoading());
        axios({
            method: "PUT",
            url: `${API_URL}/member/${id}`,
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`,
            },
            data: {
                name,
                email,
                dob,
                avatar,
            },
        })
            .then((res) => {
                dispatch(stopLoading());
                history.goBack("/");
                NotificationManager.success("Update Profile is successful");
            })
            .catch((err) => {
                dispatch(stopLoading());
                NotificationManager.error("Update Profile is fail");
            });
    };
};

export const getFollowers = (userId, setFollowers) => {
    return (dispatch) => {
        dispatch(startLoading());
        callApi(`follow/followers/${userId}`, "GET", {params: {page: 1, size: 100}})
            .then((res) => {
                dispatch(stopLoading());
                setFollowers(res.data.list);
                dispatch({ type: "GET_FOLLOWER", payload: res.data.list });
            })
            .catch((err) => {
                console.log(err);
                dispatch(stopLoading());
            });
    };
};

export const getFriends = (userId, setFriends) => {
    return (dispatch) => {
        dispatch(startLoading());
        callApi(`follow/friends/${userId}`, "GET", {params: {page: 1, size: 100}})
            .then((res) => {
                dispatch(stopLoading());
                setFriends(res.data.list);
                dispatch({ type: "GET_FRIEND", payload: res.data.list });
            })
            .catch((err) => {
                console.log(err);
                dispatch(stopLoading());
            });
    };
};
