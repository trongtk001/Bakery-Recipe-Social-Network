import axios from "axios";
import { NotificationManager } from "react-notifications";
import { startLoading, stopLoading } from "../actions/common.action";

const API_URL = process.env.REACT_APP_API_URL;

export const Post = ({ postBody, recipe, history }) => {
    const userLogin = localStorage.getItem("userLogin");
    const token = userLogin ? JSON.parse(userLogin).token : "";

    return (dispatch) => {
        dispatch(startLoading());
        axios({
            method: "POST",
            url: `${API_URL}/post`,
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`,
            },

            data: {
                postBody,
                recipe,
            },
        })
            .then((res) => {
                dispatch(stopLoading());
                history.push("/");
                NotificationManager.success("post was add successfully");
            })
            .catch((err) => {
                dispatch(stopLoading());
            });
    };
};

export const getListPost = (page, posts, setPosts, setIsNothing,url) => {
    return (dispatch) => {
        dispatch(startLoading());
        axios({
            method: "GET",
            url: `${API_URL}/${url}?page=${page}&size=3`,
            data: null,
        })
            .then((res) => {
                dispatch(stopLoading());
                if (!res.data.list.length) {
                    setIsNothing(true);
                } else {
                    setPosts(pre => [...pre, ...res.data.list]);
                }
            })
            .catch((err) => {
                dispatch(stopLoading());
            });
    };
};

export const getComment = (id, setListComment) => {
    return (dispatch) => {
        dispatch(startLoading());
        axios({
            method: "GET",
            url: `${API_URL}/comment?id=${id}&page=1&size=5`,
            headers: {
                "Content-Type": "application/json",
            },
            data: null,
        })
            .then((res) => {
                setListComment(res.data.list);
            })
            .catch((err) => {});
    };
};
export const delComment = (id,setListComment) => {
    const userLogin = localStorage.getItem("userLogin");
    const token = userLogin ? JSON.parse(userLogin).token : "";
    return (dispatch) => {
        dispatch(startLoading());
        axios({
            method: "DELETE",
            url: `${API_URL}/comment/${id}`,
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`,
            },
            data: null,
        })
            .then((res) => {
                setListComment(pre=>pre.filter(item=>item.id != id));
            })
            .catch((err) => {});
    };
};

export const postComment = (commentDetail, image, memberID, id, setListComment, listComment) => {
    return (dispatch) => {
        dispatch(startLoading());
        axios({
            method: "POST",
            url: `${API_URL}/comment`,
            headers: {
                "Content-Type": "application/json",
            },
            data: {
                commentDetail,
                image,
                member: {
                    id: memberID,
                },
                post: {
                    id: id,
                },
            },
        })
            .then((res) => {
                setListComment([...listComment, res.data]);
            })
            .catch((err) => {
                console.log(err);
            });
    };
};
